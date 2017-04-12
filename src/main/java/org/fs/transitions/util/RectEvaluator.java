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

import android.animation.TypeEvaluator;
import android.graphics.Rect;

public class RectEvaluator implements TypeEvaluator<Rect> {

  private final Rect rect = new Rect();

  public void identity() {
    rect.setEmpty();
  }

  @Override public Rect evaluate(float fraction, Rect start, Rect end) {
    int left = start.left + (int) ((end.left - start.left) * fraction);
    int top = start.top + (int) ((end.top - start.top) * fraction);
    int right = start.right + (int) ((end.right - start.right) * fraction);
    int bottom = start.bottom + (int) ((end.bottom - start.bottom) * fraction);
    // reuse is optimized
    rect.set(left, top, right, bottom);
    return rect;
  }
}