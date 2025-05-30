package com.github.tckelly.beatbox.component;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.action.ChangeNumBeatsAction;
import com.github.tckelly.beatbox.action.SetTempoAction;
import com.github.tckelly.beatbox.action.StartMidiAction;
import com.github.tckelly.beatbox.action.StopMidiAction;
import com.github.tckelly.beatbox.midi.MidiController;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

public class BeatBoxPanel extends JPanel {

    private BeatBoxModel model;
    private final MidiController midiController;

    public BeatBoxPanel(BeatBoxModel model) {
        super(new GridBagLayout());
        this.model = model;
        this.midiController = new MidiController();
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

        JButton startButton = new JButton(new StartMidiAction(BeatBoxPanel.this));
        buttonPanel.add(startButton, constraints);

        JButton stopButton = new JButton(new StopMidiAction(midiController));
        buttonPanel.add(stopButton, constraints);

        constraints.gridheight = RELATIVE;

        JButton setTempoButton = new JButton(new SetTempoAction(BeatBoxPanel.this));
        buttonPanel.add(setTempoButton, constraints);

        constraints.gridheight = REMAINDER;

        JButton changeNumBeatsButton = new JButton(new ChangeNumBeatsAction(BeatBoxPanel.this));
        buttonPanel.add(changeNumBeatsButton, constraints);

        // wrap beatbox to keep from resizing.
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.add(new BeatBoxEditorPanel(model));

        JScrollPane scrollPane = new JScrollPane(wrapperPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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

    public void refreshWithModel(BeatBoxModel model) {
        if (model == null) {
            return;
        }
        SwingUtilities.invokeLater(() -> {
            BeatBoxPanel.this.removeAll();
            this.model = model;
            construct();
            midiController.getSequencer().setTempoInBPM(this.model.getTempo());
            BeatBoxPanel.this.revalidate();
            BeatBoxPanel.this.repaint();
        });
    }

    public BeatBoxModel getModel() {
        return model;
    }

    public MidiController getMidiController() {
        return midiController;
    }
}