package com.github.tckelly.beatbox.action;

import com.github.tckelly.beatbox.controller.BeatBoxController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveAction extends AbstractAction {
    private static final Logger LOGGER = Logger.getLogger(SaveAction.class.getName());

    private final transient BeatBoxController controller;

    public SaveAction(BeatBoxController controller) {
        super("Save...");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window owner = null;

        if (e.getSource() instanceof Component) {
            owner = SwingUtilities.getWindowAncestor((Component) e.getSource());
        }

        JFileChooser fileSave = new JFileChooser();
        int saveDialogState = fileSave.showSaveDialog(owner);
        File saveFile = fileSave.getSelectedFile();

        if (saveFile != null) {
            saveBeat(saveFile, owner);
        } else if (JFileChooser.APPROVE_OPTION == saveDialogState) {
            JOptionPane.showConfirmDialog(owner, "Please select a valid file", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveBeat(File saveFile, Window owner) {
        String message = "Saved beat successfully";
        boolean isErrorMessage = false;
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(saveFile.toPath()))) {
            out.writeObject(controller.getModel());
        } catch (Exception ex) {
            message = "Got an exception while saving beat to file";
            isErrorMessage = true;
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
        JOptionPane.showConfirmDialog(owner, message, isErrorMessage ? "Error" : "Success", JOptionPane.OK_CANCEL_OPTION, isErrorMessage ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }
}
