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

public class TranslateTransition extends Transition {

  private static final String TRANSLATE_X = "android:translate:translateX";
  private static final String TRANSLATE_Y = "android:translate:translateY";

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

    float startTranslateX = (Float) startValues.values.get(TRANSLATE_X);
    float endTranslateX = (Float) endValues.values.get(TRANSLATE_X);
    ObjectAnimator translateXAnimator = null;
    if (startTranslateX != endTranslateX) {
      view.setTranslationX(startTranslateX);
      translateXAnimator = ObjectAnimator.ofFloat(view, TRANSLATE_X_PROPERTY, startTranslateX, endTranslateX);
    }

    float startTranslateY = (Float) startValues.values.get(TRANSLATE_Y);
    float endTranslateY = (Float) endValues.values.get(TRANSLATE_Y);
    ObjectAnimator translateYAnimator = null;
    if (startTranslateY != endTranslateY) {
      view.setTranslationY(startTranslateY);
      translateYAnimator = ObjectAnimator.ofFloat(view, TRANSLATE_Y_PROPERTY, startTranslateY, endTranslateY);
    }
    return AnimatorCompat.merge(translateXAnimator, translateYAnimator);
  }

  private void captureValues(TransitionValues transitionValues) {
    transitionValues.values.put(TRANSLATE_X, transitionValues.view.getTranslationX());
    transitionValues.values.put(TRANSLATE_Y, transitionValues.view.getTranslationY());
  }

  public static final Property<View, Float> TRANSLATE_X_PROPERTY = new FloatProperty<View>() {
    @Override public void set(View view, Float translateX) {
      view.setTranslationX(translateX);
    }

    @Override public Float get(View view) {
      return view.getTranslationX();
    }
  };

  public static final Property<View, Float> TRANSLATE_Y_PROPERTY = new FloatProperty<View>() {
    @Override public void set(View view, Float translateY) {
      view.setTranslationY(translateY);
    }

    @Override public Float get(View view) {
      return view.getTranslationY();
    }
  };
}