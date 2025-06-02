package com.github.tckelly.beatbox.midi;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.sound.midi.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MidiPlaybackService {
    private static final Logger LOGGER = Logger.getLogger(MidiPlaybackService.class.getName());

    private Sequencer sequencer;

    public MidiPlaybackService() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "Couldn't set up MIDI!", e);
        }
    }

    public void buildTrackAndStart(BeatBoxController controller) {
        Sequence sequence = buildNewSequence();
        if (sequence == null) {
            return;
        }
        buildTrack(sequence, controller);
        setSequenceAndStart(sequence, controller);
    }

    private void setSequenceAndStart(Sequence sequence, BeatBoxController controller) {
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(controller.getTempo());
            sequencer.start();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void buildTrack(Sequence sequence, BeatBoxController controller) {
        Track track = sequence.createTrack();

        for (int row = 0; row < controller.getInstruments().size(); row++) {
            int[] trackList = new int[controller.getNumBeats()];
            int midiKey = controller.getInstruments().get(row).getMidiNum();

            List<Boolean> beatRow = controller.getBeatRow(row);
            for (int column = 0; column < controller.getNumBeats(); column++) {
                trackList[column] = Boolean.TRUE.equals(beatRow.get(column)) ? midiKey : 0;
            }

            makeTracks(trackList, controller.getNumBeats(), track);
            track.add(makeEvent(176, 1, 127, 0, controller.getNumBeats()));
        }

        track.add(makeEvent(192, 9, 1, 0, controller.getNumBeats() - 1));
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
        for (int beat = 0; beat < numBeats; beat++) {
            int key = list[beat];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, beat));
                track.add(makeEvent(128, 9, key, 100, beat + 1));
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
