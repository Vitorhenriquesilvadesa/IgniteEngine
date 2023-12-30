package org.ignite.editor.layers.ignui;

import org.ignite.core.app.Application;
import org.ignite.editor.layers.imgui.ImGuiLayer;
import org.ignite.editor.ui.*;
import org.ignite.general.macros.debug.Macros;
import org.ignite.system.debbuging.DebugConsole;
import org.ignite.system.debbuging.DebugMessage;

import java.util.ArrayList;
import java.util.List;

public class IgnGuiLayer extends ImGuiLayer {

    public List<Widget> consoleMessages = new ArrayList<>();

    FullScreenContainer parentWidget;

    @Override
    public void onCreate() {
        this.parentWidget = new FullScreenContainer(

                new Container(0, 1080 - 64, 300, 48, new Text("FPS: " + Application.getInstance().lastFPSValue)),
                new DockingContainer(0, 96, 300, 1080 - 96)
                        .decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                                .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2)),

                new TabContainer(300, 1020 - 400, 1920 - 600, 400, "Project",
                        new Tab(300,
                                1020 - 400,
                                1920 - 600,
                                400, "Debug Console", this.consoleMessages),
                        new Tab(300,
                                1020 - 400,
                                1920 - 600,
                                400, "Project Explorer")
                ).decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                        .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2).activeColor(Colors.fromRGBA(119, 152, 255, 255)
                        ).hoverColor(Colors.fromRGBA(119, 152, 255, 255))
                ),

                new VerticalExpanded(1920 - 300, 96, 300
                ).decoration(new BoxDecoration().color(Colors.fromRGBA(30, 30, 30, 255))
                        .borderColor(Colors.fromRGBA(50, 50, 50, 255)).borderWidth(2)),

                new NoDockingStackContainer(0, 0, 1920, 32,
                        new CascadeMenu(28, 0, 80, 32, 80, "New",
                                new CascadeMenuItem("Project").onPressed(functionManager::File_NewProject),
                                new CascadeMenuItem("File").onPressed(functionManager::File_NewFile)
                        ).decoration(new BoxDecoration().borderColor(Colors.fromRGBA(30, 30, 30, 255)
                                        )
                                        .color(Colors.fromRGBA(50, 50, 50, 255)
                                        )
                        ),
                        new CascadeMenu(128, 0, 80, 32, 160, "Open",
                                new CascadeMenuItem("Project").onPressed(functionManager::File_Open),
                                new CascadeMenuItem("Recent Project").onPressed(functionManager::File_OpenRecent),
                                new CascadeMenuItem("File")
                        ).decoration(new BoxDecoration().borderColor(Colors.fromRGBA(30, 30, 30, 255))
                                .color(Colors.fromRGBA(50, 50, 50, 255)
                                )
                        )
                ).decoration(new BoxDecoration().borderColor(Colors.fromRGBA(30, 30, 30, 255))
                        .color(Colors.fromRGBA(50, 50, 50, 255)
                        )
                ),
                new NoDockingStackContainer(0, 32, 1920, 64,
                        new Image(-8, -4, 24, 24, Macros.workingDir + "res/icons/ignite.png")
                ).decoration(new BoxDecoration().color(Colors.fromRGBA(40, 40, 40, 255
                                )
                        )
                )
        ).decoration(new BoxDecoration(
                new Color(0, 0, 0, 0),
                new Color(30, 30, 30, 255),
                new Color(0, 0, 0, 0),
                0, 0, null));

        Screen screen = new Screen(parentWidget);
        Navigator.pushScreen(screen);
    }

    @Override
    public void beforeUpdate() {
        if (this.consoleMessages.size() < DebugConsole.getDebugMessages().size()) {
            for (int i = this.consoleMessages.size(); i < DebugConsole.getDebugMessages().size(); i++) {
                DebugMessage message = DebugConsole.getDebugMessages().get(i);
                this.consoleMessages.add(new Text(message.getText()).style(new TextStyle("JetBrainsMono-VariableFont_wght", FontSize.MEDIUM, message.getColor())));
            }
        }
    }
}
