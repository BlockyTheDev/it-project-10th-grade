/*
 * School Project - Tetris Game
 * Copyright (C) 2023 - present BlockyTheDev <https://github.com/BlockyTheDev>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
