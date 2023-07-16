/*
 * Copyright 2023 BlockyTheDev <https://github.com/BlockyTheDev>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.blockythedev.tetris.utils;

import org.jetbrains.annotations.NotNull;

/**
 * <b>Representing a rotation.</b>
 */
public enum Rotation {
    /**
     * Normal rotation.
     */
    NORMAL,
    /**
     * Rotation to right side.
     */
    RIGHT,
    /**
     * Mirrored rotation.
     */
    MIRRORED,
    /**
     * Rotation to left side.
     */
    LEFT;

    /**
     * <b>Get the previous {@link Rotation} in clockwise direction.</b>
     *
     * @return The previous {@link Rotation}
     */
    @NotNull
    public Rotation previous() {
        final Rotation[] values = Rotation.values();
        return values[(this.ordinal() - 1 + values.length) % values.length];
    }

    /**
     * <b>Get the next {@link Rotation} in clockwise direction.</b>
     *
     * @return The next {@link Rotation}
     */
    @NotNull
    public Rotation next() {
        final Rotation[] values = Rotation.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
