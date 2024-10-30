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
package io.github.blockythedev.tetris.logic;

import io.github.blockythedev.tetris.constants.StringConstants;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.jetbrains.annotations.NotNull;

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
     * Note: Do NOT call this by hand.
     * This is automatically called, after every key press.
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
