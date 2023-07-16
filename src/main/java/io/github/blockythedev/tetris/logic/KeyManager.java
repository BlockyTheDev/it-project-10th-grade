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
package io.github.blockythedev.tetris.logic;

import io.github.blockythedev.tetris.constants.StringConstants;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <b>The class for listening for key inputs.</b>
 */
public class KeyManager implements KeyListener {
    private final GameManager gameManager;

    /**
     * <b>The constructor for the {@link KeyManager} listener.</b>
     *
     * @param gameManager An {@link GameManager} instance
     */
    public KeyManager(final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * <b>Invoked when an key is pressed.</b>
     * <p>
     *     Note: Do NOT call this by hand.
     *     This is automatically called, after every key press.
     * </p>
     *
     * @param e The {@link KeyEvent}
     */
    @Override
    public void keyPressed(@NotNull final KeyEvent e) {
        final Board board = gameManager.getBoardInstance();

        if (gameManager.isGameOver()) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_KP_LEFT:
            case KeyEvent.VK_A:
                if (board.getCurrentShape() == null || gameManager.isPaused()) return;
                board.moveXAxis(false);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_KP_RIGHT:
            case KeyEvent.VK_D:
                if (board.getCurrentShape() == null || gameManager.isPaused()) return;
                board.moveXAxis(true);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
            case KeyEvent.VK_W:
                if (board.getCurrentShape() == null || gameManager.isPaused()) return;
                board.rotateShape(true);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
            case KeyEvent.VK_S:
                if (board.getCurrentShape() == null || gameManager.isPaused()) return;
                board.rotateShape(false);
                break;
            case KeyEvent.VK_SPACE:
                if (board.getCurrentShape() == null || gameManager.isPaused()) return;
                board.dropShapeDown();
                break;
            case KeyEvent.VK_PAUSE:
            case KeyEvent.VK_P:
                gameManager.setPaused(!gameManager.isPaused());
                gameManager.getMainScreen().updateTitle(gameManager.isPaused() ? StringConstants.WINDOW_TITLE_EXTENSION_PAUSED : StringConstants.WINDOW_TITLE_EXTENSION_RUNNING);
                break;
        }
    }

    @Override
    public void keyReleased(@NotNull final KeyEvent e) {
        // Do Nothing...
    }

    @Override
    public void keyTyped(@NotNull final KeyEvent e) {
        // Do Nothing...
    }

}
