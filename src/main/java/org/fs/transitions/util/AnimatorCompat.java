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
package org.fs.transitions.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.Interpolator;

public final class AnimatorCompat  {

  public static Animator merge(Animator p, Animator q) {
    if (q == null) {
      return p;
    } else if (p == null) {
      return q;
    } else {
      AnimatorSet animator = new AnimatorSet();
      animator.playTogether(p, q);
      return animator;
    }
  }

  public static Interpolator FANCY_INTERPOLATOR = new Interpolator() {
    @Override public float getInterpolation(float t) {
      t -= 1.0f;
      return t * t * t * t * t + 1.0f;
    }
  };

  private AnimatorCompat() {
    throw new RuntimeException("You can not have instance of this type");
  }
}