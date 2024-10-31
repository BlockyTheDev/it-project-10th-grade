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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.jetbrains.annotations.NotNull;

/**
 * A class for listening for key inputs.
 */
public class KeyManager implements KeyListener {
    private final GameManager gameManager;

    /**
     * Constructs a {@link KeyManager} for key press listening.
     *
     * @param gameManager The {@link GameManager} instance.
     */
    public KeyManager(final @NotNull GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void keyPressed(final @NotNull KeyEvent event) {
        if (gameManager.isGameOver()) {
            return;
        }

        final Board board = gameManager.getBoard();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_KP_LEFT, KeyEvent.VK_A -> executeBoardShapeAction(() -> board.moveXAxis(false));
            case KeyEvent.VK_RIGHT, KeyEvent.VK_KP_RIGHT, KeyEvent.VK_D -> executeBoardShapeAction(() -> board.moveXAxis(true));
            case KeyEvent.VK_UP, KeyEvent.VK_KP_UP, KeyEvent.VK_W -> executeBoardShapeAction(() -> board.rotateShape(true));
            case KeyEvent.VK_DOWN, KeyEvent.VK_KP_DOWN, KeyEvent.VK_S -> executeBoardShapeAction(() -> board.rotateShape(false));
            case KeyEvent.VK_SPACE -> executeBoardShapeAction(board::dropShapeDown);
            case KeyEvent.VK_PAUSE, KeyEvent.VK_P -> gameManager.togglePaused();
        }
    }

    @Override
    public void keyReleased(@NotNull final KeyEvent e) {
        // not needed
    }

    @Override
    public void keyTyped(@NotNull final KeyEvent e) {
        // not needed
    }

    /**
     * Executes the given board action if the game is not paused and the current shape is not null.
     *
     * @param action The action to execute if needed.
     */
    private void executeBoardShapeAction(final @NotNull Runnable action) {
        final Board board = gameManager.getBoard();
        if (!gameManager.isPaused() && board.getCurrentShape() != null) {
            action.run();
        }
    }
}
