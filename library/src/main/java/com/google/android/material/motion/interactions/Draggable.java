/*
 * Copyright 2017-present The Material Motion Authors. All Rights Reserved.
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
package com.google.android.material.motion.interactions;

import android.view.View;

import com.google.android.material.motion.ConstraintApplicator;
import com.google.android.material.motion.MotionObservable;
import com.google.android.material.motion.MotionRuntime;
import com.google.android.material.motion.gestures.DragGestureRecognizer;
import com.google.android.material.motion.gestures.GestureInteraction;
import com.google.android.material.motion.properties.ViewProperties;

import static com.google.android.material.motion.operators.GestureOperators.translated;

/**
 * A draggable interaction.
 */
public class Draggable extends GestureInteraction<DragGestureRecognizer, Float[]> {

  public Draggable() {
    this(new DragGestureRecognizer());
  }

  public Draggable(DragGestureRecognizer gestureRecognizer) {
    super(gestureRecognizer);
  }

  @Override
  protected void onApply(
    MotionRuntime runtime,
    MotionObservable<DragGestureRecognizer> stream,
    final View target,
    ConstraintApplicator<Float[]> constraints) {
    MotionObservable<Float[]> translatedStream = stream.compose(translated(target));

    runtime.write(constraints.apply(translatedStream), target, ViewProperties.TRANSLATION);
  }
}