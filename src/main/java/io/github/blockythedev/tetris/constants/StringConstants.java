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
package io.github.blockythedev.tetris.constants;

/**
 * <b>A final class containing all game related Strings.</b>
 */
public final class StringConstants {
    public static final String WINDOW_TITLE = "Tetris - {0}";
    public static final String WINDOW_TITLE_EXTENSION_READY = "Ready";
    public static final String WINDOW_TITLE_EXTENSION_RUNNING = "Running";
    public static final String WINDOW_TITLE_EXTENSION_PAUSED = "Paused";
    public static final String WINDOW_TITLE_EXTENSION_GAME_OVER = "Game Over";
    public static final String DIALOG_TITLE_CONFIRM_EXIT = "Exit Program Message Box";
    public static final String DIALOG_TITLE_GAME_INSTRUCTIONS = "Game Instructions";
    public static final String DIALOG_TITLE_GAME_START = "Start Game";
    public static final String DIALOG_MESSAGE_CONFIRM_EXIT = "Are you sure you want to exit the program?";
    public static final String DIALOG_MESSAGE_GAME_START = "Press to start the game!";
    public static final String DIALOG_MESSAGE_GAME_OVER = "Game Over!\nScore: {0}\nDou you want to play again?";
    public static final String DIALOG_MESSAGE_GAME_INSTRUCTION = "This project is provided for educational purposes only.\n" +
            "'Tetris' is the registered trademark of The Tetris Holding, LLC, licensed to The Tetris Company, Inc.\n" +
            "This project is not affiliated with nor endorsed by the Tetris Company, Inc and Tetris Holding, LLC.\n\n\n" +
            "Welcome to out self-coded Tetris game!\n\n" +
            "Developed by BlockyTheDev and two other classmates as 10th grade school project.\n\n" +
            "Here are the instructions to get started:\n\n" +
            "1. Game Controls:\n" +
            "   - Use the left and right arrow keys to move the falling shape horizontally.\n" +
            "   - Press the up arrow key to rotate the shape clockwise.\n" +
            "   - Press the down arrow key to rotate the shape anti-clockwise.\n" +
            "   - Press the space bar to instantly drop the shape to the bottom.\n\n" +
            "2. Objective:\n" +
            "   - The goal of the game is to complete horizontal lines by filling them with shapes.\n" +
            "   - Each completed line will be cleared, and you will score a point.\n\n" +
            "3. Gameplay:\n" +
            "   - The game starts with an empty grid at the top of the screen.\n" +
            "   - Shapes will fall from the top, one at a time.\n" +
            "   - Move and rotate the falling shape to position it where you want it to land.\n" +
            "   - Once the shape reaches the bottom or lands on another block, it becomes fixed, and a new shape falls.\n\n" +
            "4. Scoring:\n" +
            "   - You will earn a point for each completed line.\n\n" +
            "5. Game Over:\n" +
            "   - If the stack of fixed blocks reaches the top of the screen, the game is over.\n" +
            "   - Your final score will be displayed, and you will have the option to play again.\n\n" +
            "Remember, the key to success is to clear as many lines as possible and aim for a high score!\n\n" +
            "Enjoy playing your self-coded Tetris game!";
    public static final String LABEL_TEXT_SCORE = "Score: {0}";
}
