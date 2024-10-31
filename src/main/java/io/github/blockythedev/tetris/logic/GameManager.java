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
 * This class contains all general logic for running the game.
 */
public class GameManager {
    private final Stats stats;
    private final Board board;
    private final MainScreen mainScreen;
    private boolean paused;
    private boolean gameOver;
    private Timer timer;

    /**
     * Constructs a {@link GameManager} for managing all central components.
     */
    public GameManager() {
        stats = Stats.getInstance();
        board = new Board(this);
        initTimer();
        mainScreen = new MainScreen(this);
        mainScreen.initUI();
    }

    /**
     * Initializes the game timer.
     */
    private void initTimer() {
        timer = new Timer(GameConstants.INTERVAL_FALLING_BLOCK_MS, new TetrisTimerListener(this));
    }

    /**
     * Represents a game cycle.
     */
    public void runGameCycle() {
        board.update();
        mainScreen.repaint();
    }

    /**
     * Resets the game for a new run.
     */
    public void resetGame() {
        paused = false;
        gameOver = false;
        stats.reset();
        initTimer();
        board.reset();
        mainScreen.repaint();
        mainScreen.getMainOverlayScreen().updateStatsText();
        mainScreen.updateTitle(StringConstants.WINDOW_TITLE_EXTENSION_READY);
        mainScreen.startGame();
    }

    /**
     * Gets the games paused state.
     *
     * @return Returns {@code true} if the game is in the paused state, else {@code false}.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets the paused state of the game.
     *
     * @param paused Whether the paused state should be set.
     */
    public void setPaused(final boolean paused) {
        this.paused = paused;
        updatePausedTitle();
    }

    /**
     * Toggles the games paused state.
     */
    public void togglePaused() {
        paused = !paused;
        updatePausedTitle();
    }

    /**
     * Updates the main screen title to show whether the game is in paused state.
     */
    private void updatePausedTitle() {
        getMainScreen().updateTitle(paused ? StringConstants.WINDOW_TITLE_EXTENSION_PAUSED : StringConstants.WINDOW_TITLE_EXTENSION_RUNNING);
    }

    /**
     * Gets the game-over state.
     *
     * @return Returns the game-over state.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets the game-over state.
     *
     * @param gameOver Whether the game-over state should be set.
     */
    public void setGameOver(final boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Gets the current stats.
     *
     * @return Returns the current stats.
     */
    public @NotNull Stats getStats() {
        return stats;
    }

    /**
     * Gets the current board.
     *
     * @return Returns the current board.
     */
    public @NotNull Board getBoard() {
        return board;
    }

    /**
     * Gets the current timer.
     *
     * @return Returns the current timer.
     */
    public @NotNull Timer getTimer() {
        return timer;
    }

    /**
     * Gets the main screen.
     *
     * @return Returns the main screen.
     */
    public @NotNull MainScreen getMainScreen() {
        return mainScreen;
    }
}
