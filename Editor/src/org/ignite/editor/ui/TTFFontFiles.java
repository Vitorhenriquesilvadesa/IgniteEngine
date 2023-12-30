package org.ignite.editor.ui;

import imgui.ImFont;
import imgui.ImGui;
import imgui.ImGuiIO;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.ignite.editor.Editor.EditorLogger;
import static org.ignite.general.macros.debug.Macros.*;

public class TTFFontFiles {

    private static String directory = workingDir + "res/fonts/";
    private static File folder = new File(directory);
    private static Map<String, String> ttfFilesMap = new HashMap<>();

    public static void fetchFonts() {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".ttf")) {
                        for (int i = 0; i < 5; i++) {
                            ttfFilesMap.put(file.getName().substring(0, file.getName().length() - 4), file.getAbsolutePath());
                            ImGuiIO io = ImGui.getIO();
                            ImFont font = io.getFonts().addFontFromFileTTF(file.getAbsolutePath(), 12 + i * 4);
                            FontFamily.addFont(file.getName().substring(0, file.getName().length() - 4) + (12 + i * 4), font);
                        }
                    }
                }
            }

            for (String key : ttfFilesMap.keySet()) {
                EditorLogger.info("Font: " + key + " : " + ttfFilesMap.get(key));
            }

        } else {
            EditorLogger.error("The directory " + directory + " is not a directory.");
        }
    }
}
