/*
 * School Project - Tetris Game
 * Copyright (C) 2023 BlockyTheDev <https://github.com/BlockyTheDev>
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

import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b>A class for timer actions.</b>
 */
public class TetrisTimerListener implements ActionListener {
    private final GameManager gameManager;

    /**
     * <b>Create an instance of the {@link TetrisTimerListener} class.</b>
     *
     * @param gameManager A {@link GameManager} instance
     */
    public TetrisTimerListener(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * <b>Invoked when an action occurs.</b>
     * <p>
     *     Note: Do NOT call this by hand.
     *     This is automatically called, after every timer cycle.
     * </p>
     *
     * @param e The {@link ActionListener}
     */
    @Override
    public void actionPerformed(@NotNull final ActionEvent e) {
        gameManager.runGameCycle();
    }
}
