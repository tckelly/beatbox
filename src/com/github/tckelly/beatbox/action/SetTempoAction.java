package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.midi.MidiController;
import com.github.tckelly.beatbox.component.BeatBoxPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SetTempoAction extends AbstractAction {
    private final BeatBoxPanel beatBoxPanel;

    public SetTempoAction(BeatBoxPanel beatBoxPanel) {
        super("set tempo");
        this.beatBoxPanel = beatBoxPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window parentComponent = null;

        if (e.getSource() instanceof Component) {
            parentComponent = SwingUtilities.getWindowAncestor((Component) e.getSource());
        }

        BeatBoxModel model = beatBoxPanel.getModel();
        MidiController midiController = beatBoxPanel.getMidiController();

        String input = JOptionPane.showInputDialog(parentComponent, "Change tempo?", Float.toString(midiController.getSequencer().getTempoInBPM()));

        if (input == null) {
            return;
        }

        float tempo;

        try {
            tempo = Float.parseFloat(input.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Please enter a valid tempo.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setTempo(tempo);
        midiController.getSequencer().stop();
    }
}
