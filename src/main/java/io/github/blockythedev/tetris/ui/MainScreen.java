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
import io.github.blockythedev.tetris.constants.StringConstants;
import io.github.blockythedev.tetris.logic.GameManager;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.MessageFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jetbrains.annotations.NotNull;

/**
 * This class contains the main screen.
 */
public class MainScreen extends JFrame {
    /** The game manager. */
    private final GameManager gameManager;
    /** The screen with the container, where the shapes are drawn. */
    private final BlockContainerScreen blockContainerScreen;
    /** The main overlay screen. */
    private final MainOverlayScreen mainOverlayScreen;

    /**
     * The constructor for the {@link MainScreen} UI.
     *
     * @param gameManager An {@link GameManager} instance
     */
    public MainScreen(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
        blockContainerScreen = new BlockContainerScreen(gameManager);
        mainOverlayScreen = new MainOverlayScreen(gameManager);
    }

    /**
     * Initialise the UI of {@link MainScreen}.
     */
    public void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
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
     * Start the game.
     */
    public void startGame() {
        final JTextArea textArea = GameConstants.SCREEN_SIZE_HEIGHT >= 1080 ? new JTextArea(StringConstants.DIALOG_MESSAGE_GAME_INSTRUCTION) : new JTextArea(StringConstants.DIALOG_MESSAGE_GAME_INSTRUCTION, 20, 70);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), StringConstants.DIALOG_TITLE_GAME_INSTRUCTIONS, JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(this, StringConstants.DIALOG_MESSAGE_GAME_START, StringConstants.DIALOG_TITLE_GAME_START, JOptionPane.INFORMATION_MESSAGE);
        updateTitle(StringConstants.WINDOW_TITLE_EXTENSION_RUNNING);
        gameManager.getTimer().start();
    }

    /**
     * Updates the window title with the specific extension.
     *
     * @param extension The title extension replacing the placeholder in {@link StringConstants#WINDOW_TITLE}
     */
    public void updateTitle(@NotNull final String extension) {
        setTitle(MessageFormat.format(StringConstants.WINDOW_TITLE, extension));
    }

    /**
     * Get the instance of the {@link MainOverlayScreen}.
     *
     * @return The instance of {@link MainOverlayScreen}
     */
    @NotNull
    public MainOverlayScreen getMainOverlayScreen() {
        return mainOverlayScreen;
    }
}
