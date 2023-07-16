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
package io.github.blockythedev.tetris.constants;

import java.awt.*;

/**
 * <b>A final class to store some game constants.</b>
 */
public final class GameConstants {
    /**
     * <b>The number of block columns of the game board.</b>
     */
    public static final int COLUMNS = 10;
    /**
     * <b>The number of block lines of the game board.</b>
     */
    public static final int LINES = 20;
    /**
     * <b>The screen size height.</b>
     */
    public static final double SCREEN_SIZE_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * <b>The percentage, which shows how much of the screen height is allowed to be filled with the content pane height.</b>
     */
    public static final double MAX_CONTENT_HEIGHT_PERCENTAGE = SCREEN_SIZE_HEIGHT >= 1080 ? 0.40 : 0.75;
    /**
     * <b>The factor, which shows the dimension of the game board size to the window size.</b>
     */
    public static final int SCREEN_FACTOR = (int) (SCREEN_SIZE_HEIGHT * MAX_CONTENT_HEIGHT_PERCENTAGE) / LINES;
    /**
     * <b>The interval, in which the game blocks will fall down.</b>
     */
    public static final int INTERVAL_FALLING_BLOCK_MS = 1000;
    /**
     * <b>The factor, which shows the dimension of the normal text size and the text size in the window.</b>
     */
    public static final float FONT_FACTOR = SCREEN_FACTOR * 0.05F;
}
