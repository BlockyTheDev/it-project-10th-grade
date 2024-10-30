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

import io.github.blockythedev.tetris.constants.GameConstants;
import io.github.blockythedev.tetris.constants.StringConstants;
import io.github.blockythedev.tetris.ui.MainScreen;
import io.github.blockythedev.tetris.utils.Stats;
import javax.swing.Timer;
import org.jetbrains.annotations.NotNull;

/**
 * This class contains all general code needed for running the game.
 */
public class GameManager {
    private final Stats stats;
    private final Board boardInstance;
    private final MainScreen mainScreen;
    private boolean paused;
    private boolean gameOver;
    private Timer timer;

    /**
     * The constructor of the class for managing all central instances.
     */
    public GameManager() {
        stats = new Stats();
        boardInstance = new Board(this);
        initTimer();
        mainScreen = new MainScreen(this);
        mainScreen.initUI();
    }

    /**
     * Init the {@link Timer}.
     */
    private void initTimer() {
        timer = new Timer(GameConstants.INTERVAL_FALLING_BLOCK_MS, new TetrisTimerListener(this));
    }

    /**
     * Run a game cycle.
     */
    public void runGameCycle() {
        boardInstance.update();
        mainScreen.repaint();
    }

    /**
     * Reset the game.
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
     * Check if the game is in pause state.
     *
     * @return {@code true} if the game is paused, else {@code false}
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Set the pause state of the game.
     *
     * @param paused {@code true} if the game should be set in paused mode, else {@code false} when it should end the paused mode
     */
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }

    /**
     * Check if the game is in game-over state.
     *
     * @return {@code true} if the game is in game-over state, else {@code false}
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Set the game-over state of the game.
     *
     * @param gameOver {@code true} if the game should be set in game-over state, else {@code false} if it should end the game-over state
     */
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Get the {@link Stats} instance of the game.
     *
     * @return The {@link Stats} instance
     */
    @NotNull
    public Stats getStats() {
        return stats;
    }

    /**
     * Get the {@link Board} instance of the game.
     *
     * @return The {@link Board} instance
     */
    @NotNull
    public Board getBoardInstance() {
        return boardInstance;
    }

    /**
     * Get the {@link Timer} of the game.
     *
     * @return The {@link Timer}
     */
    @NotNull
    public Timer getTimer() {
        return timer;
    }

    /**
     * Get the {@link MainScreen} UI.
     *
     * @return The {@link MainScreen}
     */
    @NotNull
    public MainScreen getMainScreen() {
        return mainScreen;
    }
}
