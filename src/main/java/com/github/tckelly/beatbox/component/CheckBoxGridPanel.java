package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.BeatBoxModel;

import javax.swing.*;
import java.awt.*;

public class CheckBoxGridPanel extends JPanel {

    private final BeatBoxModel model;

    public CheckBoxGridPanel(BeatBoxModel model) {
        super();
        this.model = model;
        GridLayout gridLayout = new GridLayout(model.getInstruments().size(), model.getNumBeats());
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        setLayout(gridLayout);
        buildGrid();
    }

    public void buildGrid() {
        for (int row = 0; row < model.getInstruments().size(); row++) {
            for (int col = 0; col < model.getNumBeats(); col++) {
                boolean isSelected = model.getBeat(row, col);
                JCheckBox checkBox = new JCheckBox();
                checkBox.setSelected(isSelected);

                int r = row;  // effectively final for lambda
                int c = col;

                checkBox.addActionListener(e -> model.setBeat(r, c, checkBox.isSelected()));

                add(checkBox);
            }
        }
    }
}
