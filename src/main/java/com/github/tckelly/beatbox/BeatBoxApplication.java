package com.github.tckelly.beatbox;

import com.github.tckelly.beatbox.action.ExitAction;
import com.github.tckelly.beatbox.action.OpenAction;
import com.github.tckelly.beatbox.action.SaveAction;
import com.github.tckelly.beatbox.component.BeatBoxPanel;

import javax.swing.*;

public class BeatBoxApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BeatBoxModel sixteenBeatModel = new BeatBoxModel.Builder().numBeats(16).build();
            JFrame frame = wrapInFrame("BeatBox", new BeatBoxPanel(sixteenBeatModel));

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static JFrame wrapInFrame(String title, BeatBoxPanel beatBoxPanel) {
        final JFrame frame = new JFrame(title);
        frame.setJMenuBar(buildJMenuBar(beatBoxPanel));
        frame.setContentPane(beatBoxPanel);
        return frame;
    }

    private static JMenuBar buildJMenuBar(BeatBoxPanel beatBoxPanel) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new OpenAction(beatBoxPanel));
        fileMenu.add(new SaveAction(beatBoxPanel));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem(new ExitAction()));
        menuBar.add(fileMenu);
        return menuBar;
    }
}
