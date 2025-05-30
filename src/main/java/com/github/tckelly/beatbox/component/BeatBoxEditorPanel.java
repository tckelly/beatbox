package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.BeatBoxModel;

import javax.swing.*;
import java.awt.*;

public class BeatBoxEditorPanel extends JPanel {
    private final BeatBoxModel model;

    public BeatBoxEditorPanel(BeatBoxModel model) {
        super();
        this.model = model;
        build();
    }

    private void build() {
        InstrumentLabelGridPanel instrumentLabelGridPanel = new InstrumentLabelGridPanel(model);
        CheckBoxGridPanel checkBoxGridPanel = new CheckBoxGridPanel(model);
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
