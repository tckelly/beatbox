package com.github.tckelly.beatbox.midi;

import com.github.tckelly.beatbox.midi.dto.PlaybackData;

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

    public void buildTrackAndStart(PlaybackData playbackData) {
        Sequence sequence = buildNewSequence();
        if (sequence == null) {
            return;
        }
        buildTrack(sequence, playbackData);
        setSequenceAndStart(sequence, playbackData);
    }

    private void setSequenceAndStart(Sequence sequence, PlaybackData playbackData) {
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.setTempoInBPM(playbackData.getTempo());
            sequencer.start();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void buildTrack(Sequence sequence, PlaybackData playbackData) {
        Track track = sequence.createTrack();

        for (int row = 0; row < playbackData.getInstruments().size(); row++) {
            int[] trackList = new int[playbackData.getNumBeats()];
            int midiKey = playbackData.getInstruments().get(row).getMidiNum();

            List<Boolean> beatRow = playbackData.getBeatRow(row);
            for (int column = 0; column < playbackData.getNumBeats(); column++) {
                trackList[column] = Boolean.TRUE.equals(beatRow.get(column)) ? midiKey : 0;
            }

            makeTracks(trackList, playbackData.getNumBeats(), track);
            track.add(makeEvent(176, 1, 127, 0, playbackData.getNumBeats()));
        }

        track.add(makeEvent(192, 9, 1, 0, playbackData.getNumBeats() - 1));
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
