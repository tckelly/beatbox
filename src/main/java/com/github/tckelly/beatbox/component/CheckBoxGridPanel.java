package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;

public class CheckBoxGridPanel extends JPanel {

    private final transient BeatBoxController controller;

    public CheckBoxGridPanel(BeatBoxController controller) {
        super();
        this.controller = controller;
        GridLayout gridLayout = new GridLayout(controller.getInstruments().size(), controller.getNumBeats());
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        setLayout(gridLayout);
        buildGrid();
    }

    public void buildGrid() {
        for (int row = 0; row < controller.getInstruments().size(); row++) {
            for (int col = 0; col < controller.getNumBeats(); col++) {
                boolean isSelected = controller.getBeat(row, col);
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(isSelected);

                int r = row;  // effectively final for lambda
                int c = col;

                checkBox.addActionListener(e -> controller.handleToggleCell(r, c));

                add(checkBox);
            }
        }
    }
}
