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
import org.jetbrains.annotations.Nullable;

/**
 * Represents the {@literal  L} shape opened to the right.
 */
public class LRight extends Shape {
    private static final Map<Rotation, Block[][]> shapeList;

    static {
        final Block block = Shape.generateColoredBlock();
        final Block[][] normalShape = new Block[3][2];
        normalShape[0][0] = block;
        normalShape[1][0] = block;
        normalShape[2][0] = block;
        normalShape[2][1] = block;
        shapeList = Shape.generateShapeList(normalShape, isNotRotatable());
    }

    /**
     * Constructs the class {@link LRight}.
     */
    public LRight() {
        // default constructor
    }

    @Override
    public @Nullable Block @NotNull[] @NotNull[] getShape(@NotNull final Rotation rotation) {
        return shapeList.get(rotation);
    }
}
