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

import io.github.blockythedev.tetris.shapes.FourLine;
import io.github.blockythedev.tetris.shapes.LLeft;
import io.github.blockythedev.tetris.shapes.LRight;
import io.github.blockythedev.tetris.shapes.Shape;
import io.github.blockythedev.tetris.shapes.Square;
import io.github.blockythedev.tetris.shapes.TTurned;
import io.github.blockythedev.tetris.shapes.ZLeft;
import io.github.blockythedev.tetris.shapes.ZRight;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * A class containing many utility methods.
 */
public class Utils {
    private static final Random random = new Random();
    private static final Set<Class<? extends Shape>> shapes = Set.of(
            FourLine.class,
            LRight.class,
            LLeft.class,
            TTurned.class,
            Square.class,
            ZLeft.class,
            ZRight.class
    );

    /**
     * Prevents the constructor from being called.
     *
     * @throws UnsupportedOperationException Thrown, when the constructor gets called.
     */
    private Utils() {
        throw new UnsupportedOperationException("Cannot instantiate class");
    }

    /**
     * Selects a random {@link Shape} and creates an instance of it.
     *
     * @return Returns a random {@link Shape} instance.
     */
    public static @NotNull Shape selectRandomShape() {
        final Class<? extends Shape> chosenShape = shapes.stream()
                .toList()
                .get(random.nextInt(shapes.size()));

        try {
            return chosenShape.getDeclaredConstructor().newInstance();
        } catch (final InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the row is completely full.
     *
     * @param row The row to check.
     * @return Returns {@code true} if the array is completely full, else {@code false}.
     */
    public static boolean isFull(final Block @NotNull[] row) {
        for (final Block block : row) {
            if (block == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the row is completely empty.
     *
     * @param row The row to check.
     * @return Returns {@code true} if the array is completely empty, else {@code false}.
     */
    public static boolean isEmpty(final Block @NotNull[] row) {
        for (final Block block : row) {
            if (block != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a random {@code float}.
     *
     * @return Returns a random float generated with {@link Random#nextFloat()}.
     */
    public static float randomFloat() {
        return random.nextFloat();
    }
}
