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
package io.github.blockythedev.tetris.ui;


import io.github.blockythedev.tetris.constants.GameConstants;
import io.github.blockythedev.tetris.logic.Board;
import io.github.blockythedev.tetris.logic.GameManager;
import io.github.blockythedev.tetris.logic.KeyManager;
import io.github.blockythedev.tetris.shapes.Shape;
import io.github.blockythedev.tetris.utils.Block;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

/**
 * This class contains the screen container for the drawn shapes.
 */
public class BlockContainerScreen extends JPanel {
    /**
     * The game manager.
     */
    private final GameManager gameManager;

    /**
     * Constructs an instance of the block container screen.
     *
     * @param gameManager The game manager instance.
     */
    public BlockContainerScreen(final @NotNull GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Initialises the block container screen.
     */
    public void initUI() {
        setPreferredSize(new Dimension(GameConstants.COLUMNS * GameConstants.SCREEN_FACTOR, GameConstants.LINES * GameConstants.SCREEN_FACTOR));
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(new KeyManager(gameManager));
    }

    /**
     * Draws a block with shadow.
     *
     * @param graphics The {@link Graphics} context to use for drawing.
     * @param block The block to draw.
     * @param x The left side x-coordinate.
     * @param y The upper side y-coordinate.
     * @param width The block width.
     * @param height The block height.
     */
    private void drawBlock(final @NotNull Graphics graphics, final @NotNull Block block, final int x, final int y, final int width, final int height) {
        graphics.setColor(block.color());
        graphics.fillRect(x, y, width, height);
        graphics.setColor(block.color().brighter());
        // Line (Left): Bottom-Left -> Top-Left
        graphics.drawLine(x, y + height - 1, x, y);
        // Line (Top): Top-Left -> Top-Right
        graphics.drawLine(x, y, x + width - 1, y);
        graphics.setColor(block.color().darker());
        // Line (Bottom): Bottom-Left -> Bottom-Right
        graphics.drawLine(x + 1, y + height - 1, x + width - 1, y + height - 1);
        // Line (Right): Bottom-Right -> Top-Right
        graphics.drawLine(x + width - 1, y + height - 1, x + width - 1, y + 1);
    }

    @Override
    public void paint(final @NotNull Graphics graphics) {
        super.paint(graphics);
        final Board board = gameManager.getBoard();
        final Block[][] boardArray = board.getBoard();
        final int screenBlockWidth = getWidth() / GameConstants.COLUMNS;
        final int screenBlockHeight = getHeight() / GameConstants.LINES;

        // draws the current board state onto the screen
        for (int boardRow = 0; boardRow < boardArray.length; boardRow++) {
            for (int boardColumn = 0; boardColumn < boardArray[boardRow].length; boardColumn++) {
                final Block block = boardArray[boardRow][boardColumn];
                if (block == null) {
                    continue;
                }
                drawBlock(graphics, block, boardColumn * screenBlockWidth, boardRow * screenBlockHeight, screenBlockWidth, screenBlockHeight);
            }
        }

        final Shape currentShape = board.getCurrentShape();
        if (currentShape == null) {
            return;
        }

        // draws the current shape onto the screen
        final Block[][] shape = currentShape.getShape(board.getRotation());
        for (int shapeRow = 0; shapeRow < shape.length; shapeRow++) {
            for (int shapeColumn = 0; shapeColumn < shape[shapeRow].length; shapeColumn++) {
                final Block block = shape[shapeRow][shapeColumn];
                if (block == null) {
                    continue;
                }
                drawBlock(graphics, block, (shapeColumn + board.getPosX()) * screenBlockWidth, (shapeRow + board.getPosY()) * screenBlockHeight, screenBlockWidth, screenBlockHeight);
            }
        }
    }
}
