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

import io.github.blockythedev.tetris.constants.GameConstants;
import io.github.blockythedev.tetris.constants.StringConstants;
import io.github.blockythedev.tetris.ui.MainScreen;
import io.github.blockythedev.tetris.utils.Stats;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * <b>This class contains all general code needed for running the game.</b>
 */
public class GameManager {
    private boolean paused;
    private boolean gameOver;
    private Timer timer;
    private final Stats stats;
    private final Board boardInstance;
    private final MainScreen mainScreen;

    /**
     * <b>The constructor of the class for managing all central instances.</b>
     */
    public GameManager() {
        stats = new Stats();
        boardInstance = new Board(this);
        initTimer();
        mainScreen = new MainScreen(this);
        mainScreen.initUI();
    }

    /**
     * <b>Init the {@link Timer}.</b>
     */
    private void initTimer() {
        timer = new Timer(GameConstants.INTERVAL_FALLING_BLOCK_MS, new TetrisTimerListener(this));
    }

    /**
     * <b>Run a game cycle.</b>
     */
    public void runGameCycle() {
        boardInstance.update();
        mainScreen.repaint();
    }

    /**
     * <b>Reset the game.</b>
     */
    public void resetGame() {
        paused = false;
        gameOver = false;
        stats.reset();
        initTimer();
        boardInstance.reset();
        mainScreen.repaint();
        mainScreen.getMainOverlayScreen().updateStatsText();
        mainScreen.updateTitle(StringConstants.WINDOW_TITLE_EXTENSION_READY);
        mainScreen.startGame();
    }

    /**
     * <b>Check if the game is in pause state.</b>
     *
     * @return {@code true} if the game is paused, else {@code false}
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * <b>Set the pause state of the game.</b>
     *
     * @param paused {@code true} if the game should be set in paused mode, else {@code false} when it should end the paused mode
     */
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }

    /**
     * <b>Check if the game is in game-over state.</b>
     *
     * @return {@code true} if the game is in game-over state, else {@code false}
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * <b>Set the game-over state of the game.</b>
     *
     * @param gameOver {@code true} if the game should be set in game-over state, else {@code false} if it should end the game-over state
     */
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * <b>Get the {@link Stats} instance of the game.</b>
     *
     * @return The {@link Stats} instance
     */
    @NotNull
    public Stats getStats() {
        return stats;
    }

    /**
     * <b>Get the {@link Board} instance of the game.</b>
     *
     * @return The {@link Board} instance
     */
    @NotNull
    public Board getBoardInstance() {
        return boardInstance;
    }

    /**
     * <b>Get the {@link Timer} of the game.</b>
     *
     * @return The {@link Timer}
     */
    @NotNull
    public Timer getTimer() {
        return timer;
    }

    /**
     * <b>Get the {@link MainScreen} UI.</b>
     *
     * @return The {@link MainScreen}
     */
    @NotNull
    public MainScreen getMainScreen() {
        return mainScreen;
    }
}
