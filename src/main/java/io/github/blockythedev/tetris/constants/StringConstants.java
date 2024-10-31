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

/**
 * A final class containing all game related text constants.
 */
public final class StringConstants {
    /** Text: {@literal WINDOW_TITLE} */
    public static final String WINDOW_TITLE = "Tetris - {0}";
    /** Text: {@literal WINDOW_TITLE_EXTENSION_READY} */
    public static final String WINDOW_TITLE_EXTENSION_READY = "Ready";
    /** Text: {@literal WINDOW_TITLE_EXTENSION_RUNNING} */
    public static final String WINDOW_TITLE_EXTENSION_RUNNING = "Running";
    /** Text: {@literal WINDOW_TITLE_EXTENSION_PAUSED} */
    public static final String WINDOW_TITLE_EXTENSION_PAUSED = "Paused";
    /** Text: {@literal WINDOW_TITLE_EXTENSION_GAME_OVER} */
    public static final String WINDOW_TITLE_EXTENSION_GAME_OVER = "Game Over";
    /** Text: {@literal DIALOG_TITLE_CONFIRM_EXIT} */
    public static final String DIALOG_TITLE_CONFIRM_EXIT = "Exit Game";
    /** Text: {@literal DIALOG_TITLE_GAME_INSTRUCTIONS} */
    public static final String DIALOG_TITLE_GAME_INSTRUCTIONS = "Game Instructions";
    /** Text: {@literal DIALOG_TITLE_GAME_START} */
    public static final String DIALOG_TITLE_GAME_START = "Start Game";
    /** Text: {@literal DIALOG_MESSAGE_CONFIRM_EXIT} */
    public static final String DIALOG_MESSAGE_CONFIRM_EXIT = "Are you sure you want to exit the game?";
    /** Text: {@literal DIALOG_MESSAGE_GAME_START} */
    public static final String DIALOG_MESSAGE_GAME_START = "Press to start the game!";
    /** Text: {@literal DIALOG_MESSAGE_GAME_OVER} */
    public static final String DIALOG_MESSAGE_GAME_OVER = "Game Over!\nScore: {0}\nDou you want to play again?";
    /** Text: {@literal DIALOG_MESSAGE_GAME_INSTRUCTION} */
    public static final String DIALOG_MESSAGE_GAME_INSTRUCTION = """
            This project is published for educational purposes only.
            'Tetris' is a registered trademark of The Tetris Holding, LLC, licensed to The Tetris Company, Inc.
            This project is not affiliated with nor endorsed or supported by the Tetris Company, Inc and Tetris Holding, LLC.


            Welcome to our self-coded Tetris game!

            The first version was developed by myself and two other classmates as a 10th grade school project.

            Here are the instructions to get started:

            1. Game Controls:
               - Use the left and right arrow keys to move the falling shape horizontally.
               - Press the up arrow key to rotate the shape clockwise.
               - Press the down arrow key to rotate the shape anti-clockwise.
               - Press the space bar to instantly drop the shape to the bottom.

            2. Objective:
               - The goal of the game is to complete horizontal lines by filling them with shapes.
               - Each completed line will be cleared, and you will score a point.

            3. Gameplay:
               - The game starts with an empty grid.
               - Shapes will fall from the top, one row at a time.
               - Move and rotate the falling shape to position it where you want it to land.
               - Once the shape reaches the bottom or lands on another block, it becomes fixed, and a new shape falls.

            4. Scoring:
               - You will earn a point for each completed line.

            5. Game Over:
               - If the stack of fixed blocks reaches the top of the screen, the game is over.
               - Your final score will be displayed, and you will have the option to play again.

            Remember, the key to success is to clear as many lines as possible and aim for a high score!

            Enjoy playing our self-coded Tetris game!""";
    /** Text: {@literal LABEL_TEXT_SCORE} */
    public static final String LABEL_TEXT_SCORE = "Score: {0}";

    /**
     * Prevents the constructor from being called.
     *
     * @throws UnsupportedOperationException Thrown, when the constructor gets called.
     */
    private StringConstants() {
        throw new UnsupportedOperationException("Cannot instantiate class");
    }
}
