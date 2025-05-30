package com.github.tckelly.beatbox.midi;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.component.BeatBoxPanel;

import javax.sound.midi.*;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MidiController {
    private static final Logger LOGGER = Logger.getLogger(MidiController.class.getName());

    private Sequencer sequencer;

    public MidiController() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Couldn't set up MIDI!", e);
        }
    }

    public void buildTrackAndStart(BeatBoxPanel beatBoxPanel) {
        BeatBoxModel model = beatBoxPanel.getModel();
        Sequence sequence = buildNewSequence();
        if (sequence == null) {
            return;
        }
        buildTrack(sequence, model);
        setSequenceAndStart(sequence, model);
    }

    private void setSequenceAndStart(Sequence sequence, BeatBoxModel model) {
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(model.getTempo());
            sequencer.start();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void buildTrack(Sequence sequence, BeatBoxModel model) {
        Track track = sequence.createTrack();

        for (int i = 0; i < model.getInstruments().size(); i++) {
            int[] trackList = new int[model.getNumBeats()];
            int midiKey = model.getInstruments().get(i).getMidiNum();

            for (int j = 0; j < model.getNumBeats(); j++) {
                JCheckBox cb = model.getCheckboxes().get(j + model.getNumBeats() * i);
                trackList[j] = cb.isSelected() ? midiKey : 0;
            }

            makeTracks(trackList, model.getNumBeats(), track);
            track.add(makeEvent(176, 1, 127, 0, model.getNumBeats()));
        }

        track.add(makeEvent(192, 9, 1, 0, model.getNumBeats() - 1));
    }

    /**
     * It's easier to build a new Sequence each time we start the track
     * instead of removing all unrelated MIDI events from the Sequence.
     * @return the sequence
     */
    private static Sequence buildNewSequence() {
        Sequence sequence = null;
        try {
            sequence = new Sequence(Sequence.PPQ, 4);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        return sequence;
    }

    private void makeTracks(int[] list, int numBeats, Track track) {
        for (int i = 0; i < numBeats; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    private MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {
            LOGGER.log(Level.FINE, e.getMessage(), e);
        }
        return event;
    }

    public Sequencer getSequencer() {
        return sequencer;
    }
}
