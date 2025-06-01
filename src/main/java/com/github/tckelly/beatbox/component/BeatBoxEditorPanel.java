package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;

public class BeatBoxEditorPanel extends JPanel {
    private final transient BeatBoxController controller;

    public BeatBoxEditorPanel(BeatBoxController controller) {
        super();
        this.controller = controller;
        build();
    }

    private void build() {
        InstrumentLabelGridPanel instrumentLabelGridPanel = new InstrumentLabelGridPanel(controller);
        CheckBoxGridPanel checkBoxGridPanel = new CheckBoxGridPanel(controller);
        setLabelsHeightToMatchCheckboxHeight(checkBoxGridPanel, instrumentLabelGridPanel);
        add(instrumentLabelGridPanel);
        add(checkBoxGridPanel);
    }

    private static void setLabelsHeightToMatchCheckboxHeight(CheckBoxGridPanel checkBoxGridPanel, InstrumentLabelGridPanel instrumentLabelGridPanel) {
        Dimension checkBoxPreferredSize = checkBoxGridPanel.getPreferredSize();
        Dimension instrumentLabelPreferredSize = instrumentLabelGridPanel.getPreferredSize();
        instrumentLabelGridPanel.setPreferredSize(new Dimension(instrumentLabelPreferredSize.width, checkBoxPreferredSize.height));
    }
}
