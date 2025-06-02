package com.github.tckelly.beatbox.midi.dto;

import com.github.tckelly.beatbox.midi.Instrument;

import java.util.ArrayList;
import java.util.List;

public class PlaybackSnapshot implements PlaybackData {
    private final float tempo;
    private final int numBeats;
    private final List<Instrument> instruments;
    private final List<List<Boolean>> beatGrid;

    public PlaybackSnapshot(float tempo, int numBeats, List<Instrument> instruments, List<List<Boolean>> beatGrid) {
        this.tempo = tempo;
        this.numBeats = numBeats;
        this.instruments = new ArrayList<>(instruments);
        this.beatGrid = new ArrayList<>(beatGrid); // shallow copy
    }

    @Override
    public float getTempo() {
        return tempo;
    }

    @Override
    public int getNumBeats() {
        return numBeats;
    }

    @Override
    public List<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public List<Boolean> getBeatRow(int row) {
        return beatGrid.get(row);
    }
}
