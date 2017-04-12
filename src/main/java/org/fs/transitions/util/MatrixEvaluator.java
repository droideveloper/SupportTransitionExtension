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
import android.graphics.Matrix;

public class MatrixEvaluator implements TypeEvaluator<Matrix> {

  private final static int MATRIX_SIZE = 9;

  private float[] startValues = new float[MATRIX_SIZE];
  private float[] endValues = new float[MATRIX_SIZE];

  private final Matrix matrix = new Matrix();

  public void identity() {
    matrix.reset();
  }

  @Override public Matrix evaluate(float fraction, Matrix startValue, Matrix endValue) {
    startValue.getValues(startValues);
    endValue.getValues(endValues);

    for (int i = 0; i < MATRIX_SIZE; i ++) {
      float diff = endValues[i] - startValues[i];
      endValues[i] = startValues[i] + (fraction * diff);
    }
    matrix.setValues(endValues);
    return matrix;
  }
}