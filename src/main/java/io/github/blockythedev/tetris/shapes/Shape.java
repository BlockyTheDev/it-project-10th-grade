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
package io.github.blockythedev.tetris.shapes;

import io.github.blockythedev.tetris.utils.Block;
import io.github.blockythedev.tetris.utils.Rotation;
import io.github.blockythedev.tetris.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>An abstract class representing the base shape.</b>
 */
public abstract class Shape {
    /**
     * <b>Rotates a shape.</b>
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
     * <b>Generate a random colored {@link Block} instance.</b>
     *
     * @return The {@link Block} instance
     */
    @NotNull
    public static Block generateColoredBlock() {
        return new Block(new Color(Utils.randomFloat(), Utils.randomFloat(), Utils.randomFloat()));
    }

    /**
     * <b>Generate a random colored {@link Block} instance.</b>
     *
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
     * <b>Check if the shape can be rotated or not.</b>
     *
     * @return {@code false} because in general all shapes not overwriting this are rotatable
     */
    public static boolean isNotRotatable() {
        return false;
    }

    /**
     * <b>Get the shape.</b>
     *
     * @return The shape
     */
    @NotNull
    public Block[][] getShape(@NotNull final Rotation rotation) {
        throw new IllegalStateException("Method 'getShape' not overwritten");
    }
}