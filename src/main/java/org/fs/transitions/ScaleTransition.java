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
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.fs.transitions.util.AnimatorCompat;
import org.fs.transitions.util.FloatProperty;

public class ScaleTransition extends Transition {

  private final static String SCALE_X = "android:scale:scaleX";
  private final static String SCALE_Y = "android:scale:scaleY";

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

    float startScaleX = (Float) startValues.values.get(SCALE_X);
    float endScaleX = (Float) endValues.values.get(SCALE_X);
    ObjectAnimator scaleXAnimator = null;
    if (startScaleX != endScaleX) {
      view.setScaleX(startScaleX);
      scaleXAnimator = ObjectAnimator.ofFloat(view, SCALE_X_PROPERTY, startScaleX, endScaleX);
    }

    float startScaleY = (Float) startValues.values.get(SCALE_Y);
    float endScaleY = (Float) endValues.values.get(SCALE_Y);
    ObjectAnimator scaleYAnimator = null;
    if (startScaleY != endScaleY) {
      view.setScaleY(startScaleY);
      scaleYAnimator = ObjectAnimator.ofFloat(view, SCALE_Y_PROPERTY, startScaleY, endScaleY);
    }
    return AnimatorCompat.merge(scaleXAnimator, scaleYAnimator);
  }

  private void captureValues(TransitionValues transitionValues) {
    transitionValues.values.put(SCALE_X, transitionValues.view.getScaleX());
    transitionValues.values.put(SCALE_Y, transitionValues.view.getScaleY());
  }

  public static final Property<View, Float> SCALE_X_PROPERTY = new FloatProperty<View>() {
    @Override public void set(View view, Float scaleX) {
      view.setScaleX(scaleX);
    }

    @Override public Float get(View view) {
      return view.getScaleX();
    }
  };

  public static final Property<View, Float> SCALE_Y_PROPERTY = new FloatProperty<View>() {
    @Override public void set(View view, Float scaleY) {
      view.setScaleY(scaleY);
    }

    @Override public Float get(View view) {
      return view.getScaleY();
    }
  };
}