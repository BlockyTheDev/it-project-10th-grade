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
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * <b>Representing the {@code Z} shape opened to right.</b>
 */
public class ZRight extends Shape {
    private static final Map<Rotation, Block[][]> shapeList;

    static {
        final Block block = Shape.generateColoredBlock();
        final Block[][] normalShape = new Block[2][3];
        normalShape[0][0] = block;
        normalShape[0][1] = block;
        normalShape[1][1] = block;
        normalShape[1][2] = block;
        shapeList = Shape.generateShapeList(normalShape, isNotRotatable());
    }

    /**
     * <b>Get the rotated shape.</b>
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
