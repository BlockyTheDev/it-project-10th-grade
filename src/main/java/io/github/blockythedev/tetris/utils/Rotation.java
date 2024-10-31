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
 * This enum represents a shape rotation.
 */
public enum Rotation {
    /**
     * Normal rotation.
     */
    NORMAL,
    /**
     * The rotation to right side.
     */
    RIGHT,
    /**
     * The mirrored rotation.
     */
    MIRRORED,
    /**
     * The rotation to left side.
     */
    LEFT;

    /**
     * Gets the previous rotation in clockwise direction.
     *
     * @return Returns the previous rotation in clockwise direction.
     */
    public @NotNull Rotation previous() {
        final Rotation[] values = Rotation.values();
        return values[(ordinal() - 1 + values.length) % values.length];
    }

    /**
     * Gets the next rotation in clockwise direction.
     *
     * @return Returns the next rotation in clockwise direction.
     */
    public @NotNull Rotation next() {
        final Rotation[] values = Rotation.values();
        return values[(ordinal() + 1) % values.length];
    }
}
