package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.component.BeatBoxPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartMidiAction extends AbstractAction {
    private final BeatBoxPanel beatBoxPanel;

    public StartMidiAction(BeatBoxPanel beatBoxPanel) {
        super("start");
        this.beatBoxPanel = beatBoxPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        beatBoxPanel.getMidiController().buildTrackAndStart(beatBoxPanel);
    }
}
