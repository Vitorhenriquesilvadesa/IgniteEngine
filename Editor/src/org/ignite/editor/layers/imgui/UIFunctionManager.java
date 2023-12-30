package org.ignite.editor.layers.imgui;

import org.ignite.system.debbuging.*;

import javax.swing.*;

public class UIFunctionManager {

    public void File_NewProject(){
        DebugConsole.sendMessage(new DebugMessage("New Project required", DebugColor.BLUE, DebugLevel.INFO, "UIFunctionManager"));
    }

    public void File_NewFile(){
        DebugConsole.sendMessage(new DebugMessage("New File required", DebugColor.BLUE, DebugLevel.INFO, "UIFunctionManager"));
    }

    public void File_Open(){
        DebugConsole.sendMessage(new DebugMessage("Open required", DebugColor.BLUE, DebugLevel.INFO, "UIFunctionManager"));

        JFileChooser fileChooser = new JFileChooser();

        int returnVal = fileChooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            DebugConsole.sendMessage(new DebugMessage(file.getAbsolutePath(), DebugColor.BLUE, DebugLevel.INFO, "UIFunctionManager"));
        } else {
            DebugConsole.sendMessage(new DebugMessage("Not file selected", DebugColor.RED, DebugLevel.ERROR, "UIFunctionManager"));
        }
    }

    public void File_OpenRecent(){
        DebugConsole.sendMessage(new DebugMessage("Open Recent required", DebugColor.BLUE, DebugLevel.INFO, "UIFunctionManager"));
    }

    public void File_Save(){
        DebugConsole.sendMessage(new DebugMessage("Save required", DebugColor.BLUE, DebugLevel.INFO, "UIFunctionManager"));
    }
}
