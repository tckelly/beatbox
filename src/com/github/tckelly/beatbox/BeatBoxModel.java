package com.github.tckelly.beatbox;

import com.github.tckelly.beatbox.midi.Instrument;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

public class BeatBoxModel implements Serializable {
    private final List<Instrument> instruments;
    private final int numBeats;
    private float tempo;
    private final List<JCheckBox> checkboxes;

    public BeatBoxModel(Builder builder) {
        this.instruments = builder.getBuilderInstruments();
        this.numBeats = builder.getBuilderNumBeats();
        this.tempo = builder.getBuilderTempo();
        this.checkboxes = new ArrayList<>();
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

    public List<JCheckBox> getCheckboxes() {
        return checkboxes;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public static class Builder {
        // initalize builder with default values
        private List<Instrument> builderInstruments = BeatBoxConstants.getDefaultInstruments();
        private int builderNumBeats = BeatBoxConstants.DEFAULT_NUM_BEATS;
        private int builderTempo = BeatBoxConstants.DEFAULT_TEMPO;

        public Builder() {
        }

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