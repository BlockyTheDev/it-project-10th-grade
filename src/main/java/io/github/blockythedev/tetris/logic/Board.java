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

import io.github.blockythedev.tetris.constants.GameConstants;
import io.github.blockythedev.tetris.constants.StringConstants;
import io.github.blockythedev.tetris.shapes.Shape;
import io.github.blockythedev.tetris.utils.Block;
import io.github.blockythedev.tetris.utils.Rotation;
import io.github.blockythedev.tetris.utils.Stats;
import io.github.blockythedev.tetris.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>A Tetris board.</b>
 */
public class Board {
    private final GameManager gameManager;
    private final Block[][] gameBoard;
    private Shape currentShape;
    private int posX;
    private int posY;
    private Rotation rotation;

    /**
     * <b>Create a Tetris board.</b>
     */
    public Board(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
        gameBoard = new Block[GameConstants.LINES][GameConstants.COLUMNS];
    }

    /**
     * <b>Removes full lines of the game board.</b>
     */
    private void removeFullLines() {
        final Stats stats = gameManager.getStats();
        final List<Block[]> rows = new ArrayList<>();

        for (Block[] row : gameBoard) {
            if (Utils.isFull(row)) {
                stats.incrementRemovedLines();
                continue;
            } else if (Utils.isEmpty(row)) {
                continue;
            }
            rows.add(0, row.clone());
        }

        clearBoard();

        for (int i = 0; i < rows.size(); i++) {
            gameBoard[gameBoard.length - 1 - i] = rows.get(i);
        }
    }

    /**
     * <b>Sets a new shape and checks for game-over state.</b>
     */
    private void chooseNewShape() {
        currentShape = Utils.selectRandomShape();
        rotation = Rotation.NORMAL;
        posX = (gameBoard[0].length / 2) - (currentShape.getShape(rotation)[0].length / 2);
        posY = 0;

        if (!isPlaceable(currentShape, rotation, posX, posY)) {
            doGamerOverLogic();
        }
    }

    /**
     * <b>Inserts the current shape into the board if falling is finished.</b>
     */
    private void finishFalling() {
        Block[][] rotatedShape = currentShape.getShape(rotation);

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int column = 0; column < rotatedShape[row].length; column++) {
                if (rotatedShape[row][column] != null) {
                    gameBoard[posY + row][posX + column] = rotatedShape[row][column];
                }
            }
        }
        currentShape = null;
    }

    /**
     * <b>Moves the current shape down by one line.</b>
     */
    public void fallOneLineDown() {
        if (isPlaceable(currentShape, rotation, posX, posY + 1)) {
            posY++;
        } else {
            finishFalling();
        }
    }

    /**
     * <b>Rotate the current shape.</b>
     *
     * @param clockwise {@code true} if the rotation direction is clockwise, else {@code false}
     */
    public void rotateShape(final boolean clockwise) {
        final Rotation newRotation = clockwise ? rotation.next() : rotation.previous();
        if (isPlaceable(currentShape, newRotation, posX, posY)) rotation = newRotation;
    }

    /**
     * <b>Move the current shape on the X-axis.</b>
     *
     * @param right {@code true} if the direction is right, else {@code false}
     */
    public void moveXAxis(final boolean right) {
        int newXPos = right ? (posX + 1) : (posX - 1);

        if (isPlaceable(currentShape, rotation, newXPos, posY)) {
            posX = newXPos;
            gameManager.getMainScreen().repaint();
        }
    }

    /**
     * <b>Drops the shape down.</b>
     */
    public void dropShapeDown() {
        final Block[][] rotatedShape = currentShape.getShape(rotation);
        int newPosY = posY;

        while (newPosY < gameBoard.length - rotatedShape.length) {
            if (!isPlaceable(currentShape, rotation, posX, newPosY + 1)) {
                break;
            }
            newPosY++;
        }
        posY = newPosY;
        finishFalling();
    }

    /**
     * <b>Check if the shape is placeable at this location</b>
     *
     * @param shape       The {@link Shape} instance
     * @param newRotation The {@link Rotation} to use for checking
     * @param newPosX     The X-position to check
     * @param newPosY     The Y-position to check
     * @return {@code true] if the shape is placeable at this location, else {@code false}
     */
    private boolean isPlaceable(@NotNull final Shape shape, @NotNull final Rotation newRotation, final int newPosX, final int newPosY) {
        Block[][] rotatedShape = shape.getShape(newRotation);

        if (gameBoard[0].length < newPosX + rotatedShape[0].length || gameBoard.length < newPosY + rotatedShape.length || newPosX < 0)
            return false;

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int column = 0; column < rotatedShape[row].length; column++) {
                if (rotatedShape[row][column] != null && gameBoard[newPosY + row][newPosX + column] != null)
                    return false;
            }
        }
        return true;
    }

    /**
     * <b>Make a bord update.</b>
     */
    public void update() {
        if (gameManager.isPaused() || gameManager.isGameOver()) {
            return;
        }

        if (currentShape == null) {
            removeFullLines();
            gameManager.getMainScreen().getMainOverlayScreen().updateStatsText();
            chooseNewShape();
        } else {
            fallOneLineDown();
        }

    }

    /**
     * <b>Clears the board.</b>
     */
    private void clearBoard() {
        for (Block[] blocks : gameBoard) {
            Arrays.fill(blocks, null);
        }
    }

    /**
     * <b>Logic to run, when the game enters a game over state.</b>
     */
    private void doGamerOverLogic() {
        gameManager.getTimer().stop();
        currentShape = null;
        gameManager.setGameOver(true);
        gameManager.getMainScreen().updateTitle(StringConstants.WINDOW_TITLE_EXTENSION_GAME_OVER);

        final int option = JOptionPane.showOptionDialog(
                gameManager.getMainScreen(),
                MessageFormat.format(StringConstants.DIALOG_MESSAGE_GAME_OVER, gameManager.getStats().getRemovedLines()),
                StringConstants.WINDOW_TITLE_EXTENSION_GAME_OVER,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Yes", "No"},
                "No");

        if (option == JOptionPane.YES_OPTION) {
            gameManager.resetGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * <b>Reset the board.</b>
     */
    public void reset() {
        currentShape = null;
        posX = 0;
        posY = 0;
        rotation = Rotation.NORMAL;
        clearBoard();
    }

    /**
     * <b>Get the game board.</b>
     *
     * @return The game board
     */
    @NotNull
    public Block[][] getBoard() {
        return gameBoard;
    }

    /**
     * <b>Get the current shape.</b>
     *
     * @return The shape
     */
    @Nullable
    public Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * <b>Get the X-position of the current shape.</b>
     *
     * @return The x-position
     */
    public int getPosX() {
        return posX;
    }

    /**
     * <b>Get the Y-position of the current shape.</b>
     *
     * @return The Y-position
     */
    public int getPosY() {
        return posY;
    }

    /**
     * <b>Get the {@link Rotation}} of the current shape.</b>
     *
     * @return The rotation
     */
    @NotNull
    public Rotation getRotation() {
        return rotation;
    }
}
