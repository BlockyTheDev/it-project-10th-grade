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
package io.github.blockythedev.tetris;

import io.github.blockythedev.tetris.logic.GameManager;
import io.github.blockythedev.tetris.utils.Utils;
import java.awt.GraphicsEnvironment;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 * <b>The main class of this project.</b>
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * <b>The main class run, when the java application is started.</b>
     * <p>
     * Note: Do NOT call this by hand.
     * This is automatically called, when the jar application is started..
     * </p>
     *
     * @param args The startup arguments
     */
    public static void main(final String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            logger.warning("Headless environments aren't supported! Please use a environment supporting a display, keyboard and mouse.");
            System.exit(-1);
        }

        if (Utils.getOSName().startsWith("Windows") && Utils.getJavaMajorVersion() < 11) {
            logger.warning("Please use a LTS Java JDK Version '>= 11' for a better user interface experience: https://adoptium.net/de/temurin/releases/");
        }

        SwingUtilities.invokeLater(GameManager::new);
    }
}
