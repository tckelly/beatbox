package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangeNumBeatsAction extends AbstractAction {
    private final transient BeatBoxController controller;

    public ChangeNumBeatsAction(BeatBoxController controller) {
        super("change number of beats");
        this.controller = controller;
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

        controller.loadNewModel(new BeatBoxModel.Builder().numBeats(numBeats).build());
    }

    private Integer promptUserForNumBeats(Window parentComponent) {
        String input = JOptionPane.showInputDialog(parentComponent, "Change number of beats?", Integer.toString(controller.getNumBeats()));

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
