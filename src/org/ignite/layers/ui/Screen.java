package org.ignite.layers.ui;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

import static org.ignite.core.app.Application.ClientLog;

public class Screen {

    Widget parentNode;

    public Screen(Widget parentNode){
        this.parentNode = parentNode;
        ClientLog.trace(parentNode.toString());
    }

    public void render(){
        if(ImGui.begin("Screen", ImGuiWindowFlags.NoBackground | ImGuiWindowFlags.NoDecoration |
                ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoSavedSettings |
                ImGuiWindowFlags.NoInputs | ImGuiWindowFlags.NoFocusOnAppearing | ImGuiWindowFlags.NoBringToFrontOnFocus |
                ImGuiWindowFlags.NoNavFocus | ImGuiWindowFlags.NoNavInputs | ImGuiWindowFlags.NoNav)) {

            this.parentNode.render();

            ImGui.end();
        }
    }
}
