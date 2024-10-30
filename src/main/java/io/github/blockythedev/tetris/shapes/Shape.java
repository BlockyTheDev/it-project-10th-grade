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
import io.github.blockythedev.tetris.utils.Utils;
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An abstract class representing the base shape.
 */
public abstract class Shape {
    /**
     * Constructs the class {@link Shape}.
     */
    public Shape() {
        // default constructor
    }

    /**
     * Rotates a shape.
     *
     * @param blockShape The raw 2d-array shape
     * @param rotation The {@link Rotation}
     * @param isNotRotatable {@code true} if the shape is <u>not</u> rotatable, else {@code false}
     * @return The rotated shape
     */
    @Nullable
    public static Block[][] rotateShape(@Nullable final Block[][] blockShape, @NotNull final Rotation rotation, final boolean isNotRotatable) {
        if (blockShape == null) return null;

        Block[][] shape = Arrays.copyOf(blockShape, blockShape.length);

        if (isNotRotatable) return shape;

        switch (rotation) {
            case MIRRORED:
                final Block[][] tempShape = new Block[shape.length][shape[0].length];
                final int rows = shape.length;
                final int columns = shape[0].length;

                for (int row = 0; row < rows; row++) {
                    for (int column = 0; column < columns; column++) {
                        tempShape[rows - row - 1][columns - column - 1] = shape[row][column];
                    }
                }

                shape = tempShape;
                break;
            case RIGHT:
                int rows1 = shape.length;
                int columns1 = shape[0].length;
                final Block[][] tempShape1 = shape;
                shape = new Block[columns1][rows1];

                // turn matrix 90 degrees clockwise
                for (int row = 0; row < rows1; row++) {
                    for (int column = 0; column < columns1; column++) {
                        shape[column][rows1 - row - 1] = tempShape1[row][column];
                    }
                }
                break;
            case LEFT:
                int rows2 = shape.length;
                int columns2 = shape[0].length;
                final Block[][] tempShape2 = shape;
                shape = new Block[columns2][rows2];

                // turn matrix 90 degrees anticlockwise
                for (int row = 0; row < rows2; row++) {
                    for (int column = 0; column < columns2; column++) {
                        shape[columns2 - column - 1][row] = tempShape2[row][column];
                    }
                }
                break;
        }
        return shape;
    }

    /**
     * Generate a random colored {@link Block} instance.
     *
     * @return The {@link Block} instance
     */
    @NotNull
    public static Block generateColoredBlock() {
        return new Block(new Color(Utils.randomFloat(), Utils.randomFloat(), Utils.randomFloat()));
    }

    /**
     * Generate a random colored {@link Block} instance.
     *
     * @param normalRotatedShape The normal rotated shape
     * @param isNotRotatable Whether the shape is a not rotatable one
     * @return The {@link Block} instance
     */
    @NotNull
    public static Map<Rotation, Block[][]> generateShapeList(@NotNull final Block[][] normalRotatedShape, final boolean isNotRotatable) {
        Map<Rotation, Block[][]> shapeList = new HashMap<>();
        shapeList.put(Rotation.NORMAL, normalRotatedShape);
        shapeList.put(Rotation.RIGHT, Shape.rotateShape(normalRotatedShape, Rotation.RIGHT, isNotRotatable));
        shapeList.put(Rotation.MIRRORED, Shape.rotateShape(normalRotatedShape, Rotation.MIRRORED, isNotRotatable));
        shapeList.put(Rotation.LEFT, Shape.rotateShape(normalRotatedShape, Rotation.LEFT, isNotRotatable));
        return shapeList;
    }

    /**
     * Check if the shape can be rotated or not.
     *
     * @return {@code false} because in general all shapes not overwriting this are rotatable
     */
    public static boolean isNotRotatable() {
        return false;
    }

    /**
     * Get the shape.
     *
     * @param rotation The rotation
     * @return The shape
     */
    @NotNull
    public Block[][] getShape(@NotNull final Rotation rotation) {
        throw new IllegalStateException("Method 'getShape' not overwritten");
    }
}
