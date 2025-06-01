package com.github.tckelly.beatbox;

import com.github.tckelly.beatbox.action.ExitAction;
import com.github.tckelly.beatbox.action.OpenAction;
import com.github.tckelly.beatbox.action.SaveAction;
import com.github.tckelly.beatbox.component.BeatBoxPanel;
import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;

public class BeatBoxLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BeatBoxLauncher::launchApplication);
    }

    private static void launchApplication() {
        BeatBoxApp app = BeatBoxAppFactory.createApp();
        JFrame frame = createMainFrame(BeatBoxConstants.TITLE, app.getController(), app.getView());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JFrame createMainFrame(String title, BeatBoxController controller, BeatBoxPanel view) {
        final JFrame frame = new JFrame(title);
        frame.setJMenuBar(createJMenuBar(controller));
        frame.setContentPane(view);
        return frame;
    }

    private static JMenuBar createJMenuBar(BeatBoxController controller) {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new OpenAction(controller));
        fileMenu.add(new SaveAction(controller));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem(new ExitAction()));
        menuBar.add(fileMenu);
        return menuBar;
    }
}
