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

/**
 * <b>A class for storing the game stats.</b>
 */
public class Stats {
    private int removedLines;

    /**
     * <b>Reset the stats.</b>
     */
    public void reset() {
        this.removedLines = 0;
    }

    /**
     * <b>Increments the removed lines stats by {@code 1}.</b>
     */
    public void incrementRemovedLines() {
        removedLines++;
    }

    /**
     * <b>Get the removed line stats.</b>
     *
     * @return The removed lines stats value
     */
    public int getRemovedLines() {
        return removedLines;
    }
}
