package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.BeatBoxModel;

import javax.swing.*;
import java.awt.*;

public class BeatBoxGridPanel extends JPanel {

    private final BeatBoxModel model;

    public BeatBoxGridPanel(GridLayout gridLayout, BeatBoxModel model) {
        super(gridLayout);
        this.model = model;
        buildGrid();
    }

    private void buildGrid() {
        boolean isCheckboxesEmpty = model.getCheckboxes().isEmpty();
        int checkboxNum = 0;

        for (int i = 0; i < model.getInstruments().size(); i++) {
            String instrumentDescription = model.getInstruments().get(i).getDescription();

            add(new JLabel(instrumentDescription));

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
