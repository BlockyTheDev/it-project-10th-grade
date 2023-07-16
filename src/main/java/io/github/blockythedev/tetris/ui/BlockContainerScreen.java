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
package io.github.blockythedev.tetris.ui;


import io.github.blockythedev.tetris.constants.GameConstants;
import io.github.blockythedev.tetris.logic.Board;
import io.github.blockythedev.tetris.logic.GameManager;
import io.github.blockythedev.tetris.logic.KeyManager;
import io.github.blockythedev.tetris.shapes.Shape;
import io.github.blockythedev.tetris.utils.Block;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * <b>This class contains the screen containing the drawn shapes.</b>
 */
public class BlockContainerScreen extends JPanel {
    private final GameManager gameManager;

    /**
     * <b>Create an instance of the {@link BlockContainerScreen} class.</b>
     *
     * @param gameManager A {@link GameManager} instance
     */
    public BlockContainerScreen(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * <b>Initialise the UI of {@link BlockContainerScreen}.</b>
     */
    public void initUI() {
        final Dimension minimumWindowDimension = new Dimension(GameConstants.COLUMNS * GameConstants.SCREEN_FACTOR, GameConstants.LINES * GameConstants.SCREEN_FACTOR);
        setPreferredSize(minimumWindowDimension);
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(new KeyManager(gameManager));
    }

    /**
     * <b>Draw a block with shadow.</b>
     *
     * @param graphics The {@link Graphics} context in which to paint
     * @param block The {@link Block} to draw
     * @param x The left side x-coordinate
     * @param y The upper side y-coordinate
     * @param width The block width
     * @param height The block height
     */
    private void drawBlock(@NotNull final Graphics graphics, @NotNull final Block block, final int x, final int y, final int width, final int height) {
        graphics.setColor(block.getColor());
        graphics.fillRect(x, y, width, height);
        graphics.setColor(block.getColor().brighter());
        // Line (Left): Bottom-Left -> Top-Left
        graphics.drawLine(x, y + height - 1, x, y);
        // Line (Top): Top-Left -> Top-Right
        graphics.drawLine(x, y, x + width - 1, y);
        graphics.setColor(block.getColor().darker());
        // Line (Bottom): Bottom-Left -> Bottom-Right
        graphics.drawLine(x + 1, y + height - 1, x + width - 1, y + height - 1);
        // Line (Right): Bottom-Right -> Top-Right
        graphics.drawLine(x + width - 1, y + height - 1, x + width - 1, y + 1);
    }

    /**
     * <b>Paint the board.</b>
     * <p>
     *     Note: Do NOT call this by hand, use {@link #repaint()} instead.
     *     This is automatically called on things like window dimension change.
     * </p>
     *
     * @param graphics The {@link Graphics} context in which to paint
     */
    @Override
    public void paint(@NotNull final Graphics graphics) {
        super.paint(graphics);
        final Board boardInstance = gameManager.getBoardInstance();
        final Block[][] board = boardInstance.getBoard();
        final int width = getWidth() / GameConstants.COLUMNS;
        final int height = getHeight() / GameConstants.LINES;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                final Block block = board[row][column];
                if (block == null) continue;
                drawBlock(graphics, block, column * width, row * height, width, height);
            }
        }

        final Shape currentShape = boardInstance.getCurrentShape();
        if (currentShape == null) return;
        final Block[][] shape = currentShape.getShape(boardInstance.getRotation());
        for (int row = 0; row < shape.length; row++) {
            for (int column = 0; column < shape[row].length; column++) {
                final Block block = shape[row][column];
                if (block == null) continue;
                drawBlock(graphics, block, (column + boardInstance.getPosX()) * width, (row + boardInstance.getPosY()) * height, width, height);
            }
        }
    }
}
