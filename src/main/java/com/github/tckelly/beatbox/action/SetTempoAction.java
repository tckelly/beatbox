package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SetTempoAction extends AbstractAction {
    private final transient BeatBoxController controller;

    public SetTempoAction(BeatBoxController controller) {
        super("set tempo");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window parentComponent = null;

        if (e.getSource() instanceof Component) {
            parentComponent = SwingUtilities.getWindowAncestor((Component) e.getSource());
        }

        String input = JOptionPane.showInputDialog(parentComponent, "Change tempo?", Float.toString(controller.getTempo()));

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

        controller.handleTempoChange(tempo);
        controller.buildTrackAndStartPlayback();
    }
}
