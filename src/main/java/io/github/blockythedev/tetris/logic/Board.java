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
import io.github.blockythedev.tetris.shapes.Shape;
import io.github.blockythedev.tetris.utils.Block;
import io.github.blockythedev.tetris.utils.Rotation;
import io.github.blockythedev.tetris.utils.Stats;
import io.github.blockythedev.tetris.utils.Utils;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A Tetris board.
 */
public class Board {
    private final GameManager gameManager;
    private final Block[][] gameBoard;
    private Shape currentShape;
    private int posX;
    private int posY;
    private Rotation rotation;

    /**
     * Create a Tetris board.
     *
     * @param gameManager The game manager.
     */
    public Board(final @NotNull GameManager gameManager) {
        this.gameManager = gameManager;
        gameBoard = new Block[GameConstants.LINES][GameConstants.COLUMNS];
    }

    /**
     * Removes full lines of the game board.
     */
    private void removeFullLines() {
        final Stats stats = gameManager.getStats();
        final List<Block[]> rows = new ArrayList<>();

        for (final Block[] row : gameBoard) {
            if (Utils.isFull(row)) {
                stats.incrementRemovedLines();
                continue;
            } else if (Utils.isEmpty(row)) {
                continue;
            }
            rows.addFirst(row.clone());
        }

        clearBoard();

        for (int i = 0; i < rows.size(); i++) {
            gameBoard[gameBoard.length - 1 - i] = rows.get(i);
        }
    }

    /**
     * Sets a new shape and checks for game-over state.
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
     * Inserts the current shape into the board if falling is finished.
     */
    private void finishFalling() {
        final Block[][] rotatedShape = currentShape.getShape(rotation);
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
     * Moves the current shape down by one line.
     */
    public void fallOneLineDown() {
        if (isPlaceable(currentShape, rotation, posX, posY + 1)) {
            posY++;
        } else {
            finishFalling();
        }
    }

    /**
     * Rotate the current shape.
     *
     * @param clockwise {@code true} if the rotation direction is clockwise, else {@code false}
     */
    public void rotateShape(final boolean clockwise) {
        final Rotation newRotation = clockwise ? rotation.next() : rotation.previous();
        if (isPlaceable(currentShape, newRotation, posX, posY)) rotation = newRotation;
    }

    /**
     * Moves the current shape on the X-axis.
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
     * Drops the shape down.
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
     * Checks if the shape is placeable at this location.
     *
     * @param shape The shape instance.
     * @param newRotation The rotation to use for checking.
     * @param newPosX The X-position to check.
     * @param newPosY The Y-position to check.
     * @return Returns {@code true} if the shape is placeable at this location, else {@code false}.
     */
    private boolean isPlaceable(final @NotNull Shape shape, final @NotNull Rotation newRotation, final int newPosX, final int newPosY) {
        final Block[][] rotatedShape = shape.getShape(newRotation);

        if (gameBoard[0].length < newPosX + rotatedShape[0].length || gameBoard.length < newPosY + rotatedShape.length || newPosX < 0) {
            return false;
        }

        for (int row = 0; row < rotatedShape.length; row++) {
            for (int column = 0; column < rotatedShape[row].length; column++) {
                if (rotatedShape[row][column] != null && gameBoard[newPosY + row][newPosX + column] != null)
                    return false;
            }
        }
        return true;
    }

    /**
     * Represents a bord update.
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
     * Clears the board.
     */
    private void clearBoard() {
        for (Block[] blocks : gameBoard) {
            Arrays.fill(blocks, null);
        }
    }

    /**
     * Represents the logic, when the game enters the game-over state.
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
     * Resets the board.
     */
    public void reset() {
        currentShape = null;
        posX = 0;
        posY = 0;
        rotation = Rotation.NORMAL;
        clearBoard();
    }

    /**
     * Gets the game board.
     *
     * @return Returns the game board.
     */
    public @NotNull Block[][] getBoard() {
        return gameBoard;
    }

    /**
     * Gets the current shape.
     *
     * @return Returns the current shape.
     */
    public @Nullable Shape getCurrentShape() {
        return currentShape;
    }

    /**
     * Gets the X-position of the current shape.
     *
     * @return Returns x-position of the current shape.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Gets the Y-position of the current shape.
     *
     * @return Returns the Y-position of the current shape
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Gets the current rotation.
     *
     * @return Returns the current rotation.
     */
    public @NotNull Rotation getRotation() {
        return rotation;
    }
}
