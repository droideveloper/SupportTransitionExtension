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
import org.fs.transitions.util.FloatProperty;

public final class RotateTransition extends Transition {

  private final static String ROTATION = "android:rotate:rotation";

  @Override public void captureEndValues(@NonNull TransitionValues transitionValues) {
    transitionValues.values.put(ROTATION, transitionValues.view.getRotation());
  }

  @Override public void captureStartValues(@NonNull TransitionValues transitionValues) {
    transitionValues.values.put(ROTATION, transitionValues.view.getRotation());
  }

  @Nullable @Override public Animator createAnimator(@NonNull ViewGroup sceneRoot,
      @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
    if (startValues == null || endValues == null) {
      return null;
    }
    final View view = endValues.view;
    float startRotation = (Float) startValues.values.get(ROTATION);
    float endRotation = (Float) endValues.values.get(ROTATION);
    if (startRotation != endRotation) {
      view.setRotation(startRotation);
      return ObjectAnimator.ofFloat(view, ROTATION_PROPERTY, startRotation, endRotation);
    }
    return null;
  }

  public final static Property<View, Float> ROTATION_PROPERTY = new FloatProperty<View>() {
    @Override public void set(View view, Float rotate) {
      view.setRotation(rotate);
    }

    @Override public Float get(View view) {
      return view.getRotation();
    }
  };
}