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
        boolean isCheckboxesEmpty = model.getCheckboxes().isEmpty();
        int checkboxNum = 0;

        for (int i = 0; i < model.getInstruments().size(); i++) {
            for (int j = 0; j < model.getNumBeats(); j++) {
                JCheckBox cb;
                if (isCheckboxesEmpty) {
                    cb = new JCheckBox();
                    cb.setSelected(false);
                    model.getCheckboxes().add(cb);
                } else {
                    cb = model.getCheckboxes().get(checkboxNum++);
                }
                add(cb);
            }
        }
    }
}
