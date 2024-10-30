/*
 * School Project - Tetris Game
 * Copyright (C) 2024 BlockyTheDev <https://github.com/BlockyTheDev>
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

import java.awt.Color;
import org.jetbrains.annotations.NotNull;

/**
 * <b>Represents a block with color.</b>
 */
public class Block {
    private final Color color;

    /**
     * <b>Create a block representing a colored block.</b>
     *
     * @param color The {@link Color}
     */
    public Block(@NotNull final Color color) {
        this.color = color;
    }

    /**
     * <b>Get the color of the block.</b>
     *
     * @return The {@link Color}
     */
    @NotNull
    public Color getColor() {
        return color;
    }
}
