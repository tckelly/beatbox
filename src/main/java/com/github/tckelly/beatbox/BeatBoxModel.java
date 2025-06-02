package com.github.tckelly.beatbox;

import com.github.tckelly.beatbox.midi.Instrument;
import com.github.tckelly.beatbox.util.BeatBoxConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeatBoxModel implements Serializable {
    private final List<Instrument> instruments;
    private final int numBeats;
    private float tempo;
    private final List<List<Boolean>> beatGrid;

    private BeatBoxModel(Builder builder) {
        this.instruments = builder.getBuilderInstruments();
        this.numBeats = builder.getBuilderNumBeats();
        this.tempo = builder.getBuilderTempo();
        this.beatGrid = initializeEmptyBeatGrid();
    }

    private BeatBoxModel(BeatBoxModel that) {
        this.instruments = new ArrayList<>(that.getInstruments());
        this.numBeats = that.getNumBeats();
        this.tempo = that.getTempo();
        this.beatGrid = new ArrayList<>();
        for (List<Boolean> row : that.getBeatGrid()) {
            this.beatGrid.add(new ArrayList<>(row));
        }
    }

    public static BeatBoxModel copyOf(BeatBoxModel other) {
        return new BeatBoxModel(other);
    }

    private List<List<Boolean>> initializeEmptyBeatGrid() {
        List<List<Boolean>> grid = new ArrayList<>();
        for (int i = 0; i < instruments.size(); i++) {
            List<Boolean> row = new ArrayList<>();
            for (int j = 0; j < numBeats; j++) {
                row.add(Boolean.FALSE);
            }
            grid.add(row);
        }
        return grid;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public int getNumBeats() {
        return numBeats;
    }

    public float getTempo() {
        return tempo;
    }

    public List<List<Boolean>> getBeatGrid() {
        return beatGrid;
    }

    public void setBeat(int instrumentIndex, int beatIndex, boolean value) {
        beatGrid.get(instrumentIndex).set(beatIndex, value);
    }

    public boolean getBeat(int instrumentIndex, int beatIndex) {
        return beatGrid.get(instrumentIndex).get(beatIndex);
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public static class Builder {
        // initialize builder with default values
        private List<Instrument> builderInstruments = BeatBoxConstants.getDefaultInstruments();
        private int builderNumBeats = BeatBoxConstants.DEFAULT_NUM_BEATS;
        private int builderTempo = BeatBoxConstants.DEFAULT_TEMPO;

        public Builder instruments(List<Instrument> instruments) {
            this.builderInstruments = instruments;
            return this;
        }

        public Builder numBeats(int numBeats) {
            this.builderNumBeats = numBeats;
            return this;
        }

        public Builder tempo(int tempo) {
            this.builderTempo = tempo;
            return this;
        }

        public BeatBoxModel build() {
            return new BeatBoxModel(this);
        }

        public List<Instrument> getBuilderInstruments() {
            return builderInstruments;
        }

        public int getBuilderNumBeats() {
            return builderNumBeats;
        }

        public int getBuilderTempo() {
            return builderTempo;
        }
    }
}