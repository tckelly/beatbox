package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.component.BeatBoxPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangeNumBeatsAction extends AbstractAction {
    private final BeatBoxPanel beatBoxPanel;

    public ChangeNumBeatsAction(BeatBoxPanel beatBoxPanel) {
        super("change number of beats");
        this.beatBoxPanel = beatBoxPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window parentComponent = null;

        if (e.getSource() instanceof Component) {
            parentComponent = SwingUtilities.getWindowAncestor((Component) e.getSource());
        }

        Integer numBeats = promptUserForNumBeats(parentComponent);
        if (numBeats == null) {
            return;
        }

        beatBoxPanel.getMidiController().getSequencer().stop();
        beatBoxPanel.refreshWithModel(new BeatBoxModel.Builder().numBeats(numBeats).build());
    }

    private Integer promptUserForNumBeats(Window parentComponent) {
        String input = JOptionPane.showInputDialog(parentComponent, "Change number of beats?", Integer.toString(beatBoxPanel.getModel().getNumBeats()));

        if (input == null) {
            return null;
        }

        int numBeats = 0;

        try {
            numBeats = Integer.parseInt(input.trim());
        } catch (NumberFormatException ignored) {
            // we handle this below
        }

        if (numBeats < 1) {
            JOptionPane.showMessageDialog(parentComponent, "Please enter a positive integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return numBeats;
    }
}
