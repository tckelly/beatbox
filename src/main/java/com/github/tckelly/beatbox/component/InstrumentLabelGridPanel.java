package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;

public class InstrumentLabelGridPanel extends JPanel {
    private final transient BeatBoxController controller;
    public InstrumentLabelGridPanel(BeatBoxController controller) {
        this.controller = controller;
        GridLayout gridLayout = new GridLayout(controller.getInstruments().size(), 1);
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        setLayout(gridLayout);
        buildGrid();
    }

    public void buildGrid() {
        controller.getInstruments().forEach(instrument -> add(new JLabel(instrument.getDescription())));
    }
}
