package com.github.tckelly.beatbox.midi;

import java.io.Serializable;

public class Instrument implements Serializable {
    private final String description;
    private final int midiNum;

    public Instrument(String description, int midiNum) {
        this.description = description;
        this.midiNum = midiNum;
    }

    public String getDescription() {
        return description;
    }

    public int getMidiNum() {
        return midiNum;
    }
}