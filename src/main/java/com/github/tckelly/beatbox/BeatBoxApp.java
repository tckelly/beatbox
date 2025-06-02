package com.github.tckelly.beatbox;

import com.github.tckelly.beatbox.view.BeatBoxPanel;
import com.github.tckelly.beatbox.controller.BeatBoxController;

public class BeatBoxApp {
    private final BeatBoxController controller;
    private final BeatBoxPanel view;

    public BeatBoxApp(BeatBoxController controller, BeatBoxPanel view) {
        this.controller = controller;
        this.view = view;
    }

    public BeatBoxController getController() {
        return controller;
    }

    public BeatBoxPanel getView() {
        return view;
    }
}
