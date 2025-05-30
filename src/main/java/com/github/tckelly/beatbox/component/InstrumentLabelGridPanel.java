package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.BeatBoxModel;

import javax.swing.*;
import java.awt.*;

public class InstrumentLabelGridPanel extends JPanel {
    private final BeatBoxModel model;
    public InstrumentLabelGridPanel(BeatBoxModel model) {
        this.model = model;
        GridLayout gridLayout = new GridLayout(model.getInstruments().size(), 1);
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        setLayout(gridLayout);
        buildGrid();
    }

    public void buildGrid() {
        model.getInstruments().forEach(instrument -> add(new JLabel(instrument.getDescription())));
    }
}
