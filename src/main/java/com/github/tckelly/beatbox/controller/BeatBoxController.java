package com.github.tckelly.beatbox.controller;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.view.BeatBoxPanel;
import com.github.tckelly.beatbox.midi.Instrument;
import com.github.tckelly.beatbox.midi.MidiPlaybackService;

import java.util.ArrayList;
import java.util.List;

public class BeatBoxController {
    private BeatBoxModel model;
    private BeatBoxPanel view;
    private final MidiPlaybackService midiPlaybackService;

    public BeatBoxController(BeatBoxModel model) {
        this.model = model;
        this.midiPlaybackService = new MidiPlaybackService();
    }

    public void setView(BeatBoxPanel view) {
        this.view = view;
    }

    public void handleTempoChange(float newTempo) {
        stopPlayback();
        model.setTempo(newTempo);
    }

    public void handleToggleCell(int instrumentIndex, int beatIndex) {
        boolean current = model.getBeat(instrumentIndex, beatIndex);
        model.setBeat(instrumentIndex, beatIndex, !current);
    }

    public void loadNewModel(BeatBoxModel newModel) {
        this.model = newModel;
        view.refreshWithModel();
    }

    public void stopPlayback() {
        midiPlaybackService.getSequencer().stop();
    }

    public void buildTrackAndStartPlayback() {
        midiPlaybackService.buildTrackAndStart(this);
    }

    public List<Instrument> getInstruments() {
        return new ArrayList<>(this.model.getInstruments());
    }

    public BeatBoxModel getModelDefensiveCopy() {
        return BeatBoxModel.copyOf(model);
    }

    public int getNumBeats() {
        return model.getNumBeats();
    }

    public boolean getBeat(int row, int col) {
        return model.getBeat(row, col);
    }

    public List<Boolean> getBeatRow(int row) {
        return model.getBeatGrid().get(row);
    }

    public float getTempo() {
        return model.getTempo();
    }
}
