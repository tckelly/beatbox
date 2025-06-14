package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.BeatBoxModel;
import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenAction extends AbstractAction {
    private static final Logger LOGGER = Logger.getLogger(OpenAction.class.getName());

    private final transient BeatBoxController beatBoxController;

    public OpenAction(BeatBoxController beatBoxController) {
        super("Open...");
        this.beatBoxController = beatBoxController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window parentComponent = null;

        if (e.getSource() instanceof Component) {
            parentComponent = SwingUtilities.getWindowAncestor((Component) e.getSource());
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(parentComponent);
        File file = fileChooser.getSelectedFile();

        if (file == null) {
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            beatBoxController.loadNewModel((BeatBoxModel) in.readObject());
        } catch (Exception ex) {
            String message = "Got an exception while reading beat from file";
            LOGGER.log(Level.SEVERE, message, ex);
            JOptionPane.showConfirmDialog(parentComponent, message, "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
}