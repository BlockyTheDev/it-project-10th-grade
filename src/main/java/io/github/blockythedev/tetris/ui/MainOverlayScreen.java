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
import java.text.MessageFormat;

/**
 * <b>This class contains the overlay for the main screen.</b>
 */
public class MainOverlayScreen extends JPanel {
    private final GameManager gameManager;
    private final JLabel statsText;

    /**
     * <b>The constructor for the {@link MainOverlayScreen} UI.</b>
     *
     * @param gameManager An {@link GameManager} instance
     */
    public MainOverlayScreen(@NotNull final GameManager gameManager) {
        this.gameManager = gameManager;
        statsText = new JLabel(MessageFormat.format(StringConstants.LABEL_TEXT_SCORE, 0));
    }

    /**
     * <b>Initialise the UI of {@link MainOverlayScreen}.</b>
     */
    public void initUI() {
        setLayout(new BorderLayout());
        setOpaque(false);
        statsText.setHorizontalAlignment(SwingConstants.LEFT);
        statsText.setVerticalAlignment(SwingConstants.TOP);
        final Font oldFont = statsText.getFont();
        statsText.setFont(oldFont.deriveFont(oldFont.getSize() * GameConstants.FONT_FACTOR));
        add(statsText, BorderLayout.NORTH);
    }

    /**
     * <b>Refreshes the stats text.</b>
     */
    public void updateStatsText() {
        statsText.setText(MessageFormat.format(StringConstants.LABEL_TEXT_SCORE, gameManager.getStats().getRemovedLines()));
    }
}
