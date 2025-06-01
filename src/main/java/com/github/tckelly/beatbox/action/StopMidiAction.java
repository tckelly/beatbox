package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StopMidiAction extends AbstractAction {
    private final transient BeatBoxController controller;

    public StopMidiAction(BeatBoxController controller) {
        super("stop");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.getMidiController().getSequencer().stop();
    }
}