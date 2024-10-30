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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * <b>A class with many utilities needed by the game.</b>
 */
public class Utils {
    private static final Random random = new Random();
    private static final List<Class<? extends Shape>> shapes = new ArrayList<>();

    static {
        shapes.add(FourLine.class);
        shapes.add(LRight.class);
        shapes.add(LLeft.class);
        shapes.add(TTurned.class);
        shapes.add(Square.class);
        shapes.add(ZLeft.class);
        shapes.add(ZRight.class);
    }

    /**
     * <b>Select a random {@link Shape}.</b>
     *
     * @return A random {@link Shape}
     */
    @NotNull
    public static Shape selectRandomShape() {
        try {
            return shapes.get(random.nextInt(shapes.size())).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>Checks if the row is completely filled.</b>
     *
     * @param row The row to check
     * @return {@code true} if completely filled, else {@code false}
     */
    public static boolean isFull(Block[] row) {
        for (Block block : row) {
            if (block == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * <b>Checks if the row is completely empty.</b>
     *
     * @param row The row to check
     * @return {@code true} if completely empty, else {@code false}
     */
    public static boolean isEmpty(Block[] row) {
        for (Block block : row) {
            if (block != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * <b>Generates a random {@code float}.</b>
     *
     * @return A random float generated with {@link Random#nextFloat()}
     */
    public static float randomFloat() {
        return random.nextFloat();
    }

    /**
     * <b>Get the major java version.</b>
     *
     * @return The major java version
     */
    public static int getJavaMajorVersion() {
        return Integer.parseInt(System.getProperty("java.version").split("\\.")[0]);
    }

    /**
     * <b>Get the operating system name.</b>
     *
     * @return The operating system name
     */
    public static String getOSName() {
        return System.getProperty("os.name");
    }
}
