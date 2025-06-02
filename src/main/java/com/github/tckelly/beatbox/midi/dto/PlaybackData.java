package com.github.tckelly.beatbox.midi.dto;

import com.github.tckelly.beatbox.midi.Instrument;

import java.util.List;

public interface PlaybackData {
    float getTempo();
    int getNumBeats();
    List<Instrument> getInstruments();
    List<Boolean> getBeatRow(int row);
}
