package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.action.ChangeNumBeatsAction;
import com.github.tckelly.beatbox.action.SetTempoAction;
import com.github.tckelly.beatbox.action.StartMidiAction;
import com.github.tckelly.beatbox.action.StopMidiAction;
import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

public class BeatBoxPanel extends JPanel {

    private final transient BeatBoxController controller;

    public BeatBoxPanel(BeatBoxController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        construct();
    }

    private void construct() {
        GridBagConstraints constraints = new GridBagConstraints();

        int padding = 5;
        constraints.insets = new Insets(padding, padding, padding, padding);
        constraints.fill = NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = REMAINDER;
        constraints.anchor = NORTHWEST;

        JPanel buttonPanel = new JPanel(new GridBagLayout());

        JButton startButton = new JButton(new StartMidiAction(controller));
        buttonPanel.add(startButton, constraints);

        JButton stopButton = new JButton(new StopMidiAction(controller));
        buttonPanel.add(stopButton, constraints);

        constraints.gridheight = RELATIVE;

        JButton setTempoButton = new JButton(new SetTempoAction(controller));
        buttonPanel.add(setTempoButton, constraints);

        constraints.gridheight = REMAINDER;

        JButton changeNumBeatsButton = new JButton(new ChangeNumBeatsAction(controller));
        buttonPanel.add(changeNumBeatsButton, constraints);

        // wrap beatbox to keep from resizing.
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.add(new BeatBoxEditorPanel(controller));

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        constraints.fill = BOTH;
        constraints.gridwidth = RELATIVE;
        constraints.gridheight = REMAINDER;
        constraints.weightx = 1;
        constraints.weighty = 1;

        add(scrollPane, constraints);

        constraints.fill = NONE;
        constraints.gridwidth = REMAINDER;
        constraints.weightx = 0.0;

        add(buttonPanel, constraints);
    }

    public void refreshWithModel() {
        SwingUtilities.invokeLater(() -> {
            this.removeAll();
            construct();
            controller.getMidiController().getSequencer().stop();
            controller.getMidiController().getSequencer().setTempoInBPM(controller.getModel().getTempo());
            this.revalidate();
            this.repaint();
        });
    }
}