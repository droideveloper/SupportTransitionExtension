/*
 * Transitions Android Copyright (C) 2017 Fatih.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fs.transitions;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.fs.transitions.util.AnimatorCompat;
import org.fs.transitions.util.IntProperty;

public final class ColorTransition extends Transition {

  private final static String BACKGROUND = "android:color:background";
  private final static String TEXT_COLOR = "android:color:textColor";

  @Override public void captureEndValues(@NonNull TransitionValues transitionValues) {
    captureValues(transitionValues);
  }

  @Override public void captureStartValues(@NonNull TransitionValues transitionValues) {
    captureValues(transitionValues);
  }

  @Nullable @Override public Animator createAnimator(@NonNull ViewGroup sceneRoot,
      @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
    if (startValues == null || endValues == null) { return null; }
    final View view = endValues.view;

    Drawable startBackgroundDrawable = (Drawable) startValues.values.get(BACKGROUND);
    Drawable endBackgroundDrawable = (Drawable) endValues.values.get(BACKGROUND);
    ObjectAnimator backgroundAnimator = null;
    if (startBackgroundDrawable instanceof ColorDrawable && endBackgroundDrawable instanceof ColorDrawable) {
      ColorDrawable start = (ColorDrawable) startBackgroundDrawable;
      ColorDrawable end = (ColorDrawable) endBackgroundDrawable;
      if (start.getColor() != end.getColor()) {
        final int endColor = end.getColor();
        end.setColor(start.getColor());
        backgroundAnimator = ObjectAnimator.ofInt(end, BACKGROUND_PROPERTY, start.getColor(), endColor);
        backgroundAnimator.setEvaluator(new ArgbEvaluator());
      }
    }

    ObjectAnimator textColorAnimator = null;
    if (view instanceof TextView) {
      TextView viewText = (TextView) view;
      int start = (Integer) startValues.values.get(TEXT_COLOR);
      int end = (Integer) endValues.values.get(TEXT_COLOR);
      if (start != end) {
        viewText.setTextColor(start);
        textColorAnimator = ObjectAnimator.ofInt(viewText, TEXT_COLOR_PROPERTY, start, end);
        textColorAnimator.setEvaluator(new ArgbEvaluator());
      }
    }

    return AnimatorCompat.merge(backgroundAnimator, textColorAnimator);
  }

  private void captureValues(TransitionValues transitionValues) {
    transitionValues.values.put(BACKGROUND, transitionValues.view.getBackground());
    if (transitionValues.view instanceof TextView) {
      transitionValues.values.put(TEXT_COLOR, ((TextView) transitionValues.view).getCurrentTextColor());
    }
  }

  public final static Property<TextView, Integer> TEXT_COLOR_PROPERTY = new IntProperty<TextView>() {

    @Override public void set(TextView viewText, Integer textColor) {
      viewText.setTextColor(textColor);
    }

    @Override public Integer get(TextView viewText) {
      return viewText.getCurrentTextColor();
    }
  };

  public final static Property<ColorDrawable, Integer> BACKGROUND_PROPERTY = new IntProperty<ColorDrawable>() {

    @Override public void set(ColorDrawable colorDrawable, Integer color) {
      colorDrawable.setColor(color);
    }

    @Override public Integer get(ColorDrawable colorDrawable) {
      return colorDrawable.getColor();
    }
  };
}