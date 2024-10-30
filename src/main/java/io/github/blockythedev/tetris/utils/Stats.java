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

/**
 * A class for storing the game stats.
 */
public class Stats {
    private int removedLines;

    /**
     * Constructs the class {@link Stats}.
     */
    public Stats() {
        this.removedLines = 0;
    }

    /**
     * Reset the stats.
     */
    public void reset() {
        this.removedLines = 0;
    }

    /**
     * Increments the removed lines stats by {@code 1}.
     */
    public void incrementRemovedLines() {
        removedLines++;
    }

    /**
     * Get the removed line stats.
     *
     * @return The removed lines stats value
     */
    public int getRemovedLines() {
        return removedLines;
    }
}
