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
