package com.github.tckelly.beatbox;

import com.github.tckelly.beatbox.view.BeatBoxPanel;
import com.github.tckelly.beatbox.controller.BeatBoxController;

public class BeatBoxAppFactory {
    private BeatBoxAppFactory() {}

    public static BeatBoxApp createApp() {
        BeatBoxModel sixteenBeatModel = new BeatBoxModel.Builder().numBeats(16).build();
        BeatBoxController controller = new BeatBoxController(sixteenBeatModel);
        BeatBoxPanel view = new BeatBoxPanel(controller);
        controller.setView(view);
        return new BeatBoxApp(controller, view);
    }
}
