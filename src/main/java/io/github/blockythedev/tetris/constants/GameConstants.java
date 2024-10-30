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
package io.github.blockythedev.tetris.constants;

import java.awt.Toolkit;

/**
 * A final class to store some game constants.
 */
public final class GameConstants {
    /**
     * The number of block columns of the game board.
     */
    public static final int COLUMNS = 10;
    /**
     * The number of block lines of the game board.
     */
    public static final int LINES = 20;
    /**
     * The screen size height.
     */
    public static final double SCREEN_SIZE_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * The percentage, which shows how much of the screen height is allowed to be filled with the content pane height.
     */
    public static final double MAX_CONTENT_HEIGHT_PERCENTAGE = SCREEN_SIZE_HEIGHT >= 1080 ? 0.40 : 0.75;
    /**
     * The factor, which shows the dimension of the game board size to the window size.
     */
    public static final int SCREEN_FACTOR = (int) (SCREEN_SIZE_HEIGHT * MAX_CONTENT_HEIGHT_PERCENTAGE) / LINES;
    /**
     * The factor, which shows the dimension of the normal text size and the text size in the window.
     */
    public static final float FONT_FACTOR = SCREEN_FACTOR * 0.05F;
    /**
     * The interval, in which the game blocks will fall down.
     */
    public static final int INTERVAL_FALLING_BLOCK_MS = 1000;

    /**
     * Prevents the constructor from being called.
     *
     * @throws UnsupportedOperationException Thrown, when the constructor gets called.
     */
    private GameConstants() {
        throw new UnsupportedOperationException("Cannot instantiate class");
    }
}
