package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartMidiAction extends AbstractAction {
    private final transient BeatBoxController controller;

    public StartMidiAction(BeatBoxController controller) {
        super("start");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.buildTrackAndStartPlayback();
    }
}
