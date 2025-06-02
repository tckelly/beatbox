package com.github.tckelly.beatbox.util;

import com.github.tckelly.beatbox.midi.Instrument;

import java.util.ArrayList;
import java.util.List;

public class BeatBoxConstants {
    private static final List<Instrument> instruments = new ArrayList<>();
    public static final int DEFAULT_NUM_BEATS = 16;
    public static final int DEFAULT_TEMPO = 120;
    public static final String TITLE = "BeatBox";

    private BeatBoxConstants() {
        throw new AssertionError();
    }

    static {
        instruments.add(new Instrument("bass", 			35));
        instruments.add(new Instrument("hh - closed", 	42));
        instruments.add(new Instrument("hh - open", 	46));
        instruments.add(new Instrument("snare", 		38));
        instruments.add(new Instrument("crash", 		49));
        instruments.add(new Instrument("clap", 			39));
        instruments.add(new Instrument("hi tom", 		50));
        instruments.add(new Instrument("hi bongo", 		60));
        instruments.add(new Instrument("maracas", 		70));
        instruments.add(new Instrument("whistle", 		72));
        instruments.add(new Instrument("low conga", 	64));
        instruments.add(new Instrument("cowbell", 		56));
        instruments.add(new Instrument("vibraslap", 	58));
        instruments.add(new Instrument("low-mid tom", 	47));
        instruments.add(new Instrument("high agogo", 	67));
        instruments.add(new Instrument("open hi conga", 63));
    }

    public static List<Instrument> getDefaultInstruments() {
        return new ArrayList<>(instruments);
    }
}
