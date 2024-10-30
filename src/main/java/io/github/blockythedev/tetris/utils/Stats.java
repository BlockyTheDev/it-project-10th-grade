/*
 * School Project - Tetris Game
 * Copyright (C) 2024 BlockyTheDev <https://github.com/BlockyTheDev>
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
