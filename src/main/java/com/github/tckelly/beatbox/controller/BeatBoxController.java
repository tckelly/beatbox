package com.github.tckelly.beatbox.controller;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.component.BeatBoxPanel;
import com.github.tckelly.beatbox.midi.Instrument;
import com.github.tckelly.beatbox.midi.MidiController;

import java.util.ArrayList;
import java.util.List;

public class BeatBoxController {
    private BeatBoxModel model;
    private BeatBoxPanel view;
    private final MidiController midiController;

    public BeatBoxController(BeatBoxModel model) {
        this.model = model;
        this.midiController = new MidiController();
    }

    public void setView(BeatBoxPanel view) {
        this.view = view;
    }

    public void handleTempoChange(float newTempo) {
        model.setTempo(newTempo);
//        view.updateTempoDisplay(newTempo); // If you have a display component
    }

    public void handleToggleCell(int instrumentIndex, int beatIndex) {
        boolean current = model.getBeat(instrumentIndex, beatIndex);
        model.setBeat(instrumentIndex, beatIndex, !current);
    }

    public void loadNewModel(BeatBoxModel newModel) {
        this.model = newModel;
        view.refreshWithModel();
    }

    public List<Instrument> getInstruments() {
        return new ArrayList<>(this.model.getInstruments());
    }

    public BeatBoxModel getModel() {
        return model;
    }

    public int getNumBeats() {
        return model.getNumBeats();
    }

    public boolean getBeat(int row, int col) {
        return model.getBeat(row, col);
    }

    public MidiController getMidiController() {
        return midiController;
    }

    public List<Boolean> getBeatRow(int row) {
        return model.getBeatGrid().get(row);
    }

    public float getTempo() {
        return model.getTempo();
    }
}
