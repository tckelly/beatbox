package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.midi.MidiController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StopMidiAction extends AbstractAction {
    private final transient MidiController midiController;

    public StopMidiAction(MidiController midiController) {
        super("stop");
        this.midiController = midiController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        midiController.getSequencer().stop();
    }
}