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
import io.github.blockythedev.tetris.constants.StringConstants;
import io.github.blockythedev.tetris.logic.GameManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;

/**
 * <b>This class contains the main screen.</b>
 */
public class MainScreen extends JFrame {
    private final GameManager gameManager;
    private final BlockContainerScreen blockContainerScreen;
    private final MainOverlayScreen mainOverlayScreen;

    /**
     * <b>The constructor for the {@link MainScreen} UI.</b>
     *
     * @param gameManager An {@link GameManager} instance
     */
    public MainScreen(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
        blockContainerScreen = new BlockContainerScreen(gameManager);
        mainOverlayScreen = new MainOverlayScreen(gameManager);
    }

    /**
     * <b>Initialise the UI of {@link MainScreen}.</b>
     */
    public void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameManager.setPaused(true);
                final int confirmed = JOptionPane.showConfirmDialog(null, StringConstants.DIALOG_MESSAGE_CONFIRM_EXIT, StringConstants.DIALOG_TITLE_CONFIRM_EXIT, JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    gameManager.setPaused(false);
                }
            }
        });

        setLocationRelativeTo(null);
        setResizable(false);
        updateTitle(StringConstants.WINDOW_TITLE_EXTENSION_READY);
        setLayout(new BorderLayout());
        blockContainerScreen.initUI();
        add(blockContainerScreen);
        mainOverlayScreen.initUI();
        setGlassPane(mainOverlayScreen);
        mainOverlayScreen.setVisible(true);
        pack();
        setVisible(true);
        startGame();
    }

    /**
     * <b>Start the game.</b>
     */
    public void startGame() {
        final JTextArea textArea = GameConstants.SCREEN_SIZE_HEIGHT >= 1080 ? new JTextArea(StringConstants.DIALOG_MESSAGE_GAME_INSTRUCTION) : new JTextArea(StringConstants.DIALOG_MESSAGE_GAME_INSTRUCTION, 20, 110);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), StringConstants.DIALOG_TITLE_GAME_INSTRUCTIONS, JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this, StringConstants.DIALOG_MESSAGE_GAME_START, StringConstants.DIALOG_TITLE_GAME_START, JOptionPane.INFORMATION_MESSAGE);
        updateTitle(StringConstants.WINDOW_TITLE_EXTENSION_RUNNING);
        gameManager.getTimer().start();
    }

    /**
     * <b>Updates the window title with the specific extension.</b>
     *
     * @param extension The title extension replacing the placeholder in {@link StringConstants#WINDOW_TITLE}
     */
    public void updateTitle(@NotNull final String extension) {
        setTitle(MessageFormat.format(StringConstants.WINDOW_TITLE, extension));
    }

    /**
     * <b>Get the instance of the {@link MainOverlayScreen}.</b>
     *
     * @return The instance of {@link MainOverlayScreen}
     */
    @NotNull
    public MainOverlayScreen getMainOverlayScreen() {
        return mainOverlayScreen;
    }

    /**
     * <b>Get the instance of the {@link BlockContainerScreen}.</b>
     *
     * @return The instance of {@link BlockContainerScreen}
     */
    @NotNull
    public BlockContainerScreen getBlockContainerScreen() {
        return blockContainerScreen;
    }
}
