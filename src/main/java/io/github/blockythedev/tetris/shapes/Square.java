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
package io.github.blockythedev.tetris.shapes;

import io.github.blockythedev.tetris.utils.Block;
import io.github.blockythedev.tetris.utils.Rotation;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/**
 * Representing the {@code Square} shape.
 */
public class Square extends Shape {
    private static final Map<Rotation, Block[][]> shapeList;

    static {
        final Block block = Shape.generateColoredBlock();
        final Block[][] normalShape = new Block[2][2];
        normalShape[0][0] = block;
        normalShape[0][1] = block;
        normalShape[1][0] = block;
        normalShape[1][1] = block;
        shapeList = Shape.generateShapeList(normalShape, isNotRotatable());
    }

    /**
     * Constructs the class {@link Square}.
     */
    public Square() {
        // default constructor
    }

    /**
     * Check if the shape can be rotated or not.
     *
     * @return {@code true} because the {@link Square} shape is not rotatable
     */
    public static boolean isNotRotatable() {
        return true;
    }

    /**
     * Get the rotated shape.
     *
     * @param rotation The {@link Rotation}
     * @return The rotated Shape
     */
    @NotNull
    @Override
    public Block[][] getShape(@NotNull final Rotation rotation) {
        return shapeList.get(rotation);
    }
}
