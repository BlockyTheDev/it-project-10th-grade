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
package io.github.blockythedev.tetris;

import io.github.blockythedev.tetris.logic.GameManager;
import io.github.blockythedev.tetris.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * <b>The main class of this project.</b>
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * <b>The main class run, when the java application is started.</b>
     * <p>
     *     Note: Do NOT call this by hand.
     *     This is automatically called, when the jar application is started..
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
