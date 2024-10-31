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
     * @param blockShape The raw 2d-array shape.
     * @param rotation The rotation.
     * @param isNotRotatable {@code true} if the shape is <u>not</u> rotatable, else {@code false}.
     * @return Returns the rotated shape.
     */
    public static @Nullable Block @NotNull[] @NotNull[] rotateShape(final @Nullable Block @NotNull[] @NotNull[] blockShape, final @NotNull Rotation rotation, final boolean isNotRotatable) {
        final Block[][] copiedShape = Arrays.copyOf(blockShape, blockShape.length);
        if (isNotRotatable) {
            return copiedShape;
        }

        switch (rotation) {
            case MIRRORED -> {
                return mirrorShape(copiedShape);
            }
            case RIGHT -> {
                return rotateShapeClockwise(copiedShape);
            }
            case LEFT -> {
                return rotateShapeAntiClockwise(copiedShape);
            }
            default -> {
                return copiedShape;
            }
        }
    }

    /**
     * Mirrors the given shape.
     *
     * @param normalShape The normal shape.
     * @return Returns the mirrored shape.
     */
    private static @Nullable Block @NotNull[] @NotNull[] mirrorShape(final @Nullable Block @NotNull[] @NotNull[] normalShape) {
        final int rows = normalShape.length;
        final int columns = normalShape[0].length;
        final Block[][] mirroredShape = new Block[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                mirroredShape[rows - row - 1][columns - column - 1] = normalShape[row][column];
            }
        }
        return mirroredShape;
    }

    /**
     * Rotates the given shape by 90 degrees clockwise.
     *
     * @param normalShape The normal shape.
     * @return Returns the 90 degrees clockwise rotated shape.
     */
    private static @Nullable Block @NotNull[] @NotNull[] rotateShapeClockwise(final @Nullable Block @NotNull[] @NotNull[] normalShape) {
        final int rows = normalShape.length;
        final int columns = normalShape[0].length;
        final Block[][] rotatedShape = new Block[columns][rows];

        // turn matrix 90 degrees clockwise
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                rotatedShape[column][rows - row - 1] = normalShape[row][column];
            }
        }
        return rotatedShape;
    }

    /**
     * Rotates the given shape by 90 degrees anti-clockwise.
     *
     * @param normalShape The normal shape.
     * @return Returns the 90 degrees anti-clockwise rotated shape.
     */
    private static @Nullable Block @NotNull[] @NotNull[] rotateShapeAntiClockwise(final @Nullable Block @NotNull[] @NotNull[] normalShape) {
        final int rows = normalShape.length;
        final int columns = normalShape[0].length;
        final Block[][] rotatedShape = new Block[columns][rows];

        // turn matrix 90 degrees anti-clockwise
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                rotatedShape[columns - column - 1][row] = normalShape[row][column];
            }
        }
        return rotatedShape;
    }

    /**
     * Generates a random colored block instance.
     *
     * @return Returns the colored block instance.
     */
    public static @NotNull Block generateColoredBlock() {
        return new Block(new Color(Utils.randomFloat(), Utils.randomFloat(), Utils.randomFloat()));
    }

    /**
     * Generates a random colored block instance.
     *
     * @param normalRotatedShape The normal rotated shape.
     * @param isNotRotatable Whether the shape is a not rotatable.
     * @return Returns a map with the shape rotated in all directions.
     */
    public static @NotNull Map<Rotation, Block[][]> generateShapeList(final @Nullable Block @NotNull[] @NotNull[] normalRotatedShape, final boolean isNotRotatable) {
        Map<Rotation, Block[][]> shapeList = new HashMap<>();
        shapeList.put(Rotation.NORMAL, normalRotatedShape);
        shapeList.put(Rotation.RIGHT, Shape.rotateShape(normalRotatedShape, Rotation.RIGHT, isNotRotatable));
        shapeList.put(Rotation.MIRRORED, Shape.rotateShape(normalRotatedShape, Rotation.MIRRORED, isNotRotatable));
        shapeList.put(Rotation.LEFT, Shape.rotateShape(normalRotatedShape, Rotation.LEFT, isNotRotatable));
        return shapeList;
    }

    /**
     * Checks if the shape can be rotated or not.
     *
     * @return Returns whether the shape is a rotatable one.
     */
    public static boolean isNotRotatable() {
        return false;
    }

    /**
     * Gets the shape.
     *
     * @param rotation The rotation.
     * @return Returns the shape-
     */
    public @Nullable Block @NotNull[] @NotNull[] getShape(final @NotNull Rotation rotation) {
        throw new IllegalStateException("Method 'getShape' not overwritten");
    }
}
