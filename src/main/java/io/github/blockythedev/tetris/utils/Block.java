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
package io.github.blockythedev.tetris.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.Color;

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
