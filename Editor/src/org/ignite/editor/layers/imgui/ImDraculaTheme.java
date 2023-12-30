package org.ignite.editor.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiCol;

public class ImDraculaTheme implements ImGuiTheme {
    @Override
    public void applyTheme() {

        ImGui.getStyle().setColor(ImGuiCol.Text, 210, 199, 181, 255);
        ImGui.getStyle().setColor(ImGuiCol.TextDisabled, 102, 98, 84, 255);
        ImGui.getStyle().setColor(ImGuiCol.WindowBg, 40, 42, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.ChildBg, 68, 71, 90, 58);
        ImGui.getStyle().setColor(ImGuiCol.PopupBg, 45, 47, 59, 255);
        ImGui.getStyle().setColor(ImGuiCol.Border, 99, 112, 141, 255);
        ImGui.getStyle().setColor(ImGuiCol.BorderShadow, 33, 38, 47, 255);
        ImGui.getStyle().setColor(ImGuiCol.FrameBg, 68, 71, 90, 58);
        ImGui.getStyle().setColor(ImGuiCol.FrameBgHovered, 82, 85, 103, 102);
        ImGui.getStyle().setColor(ImGuiCol.FrameBgActive, 87, 87, 103, 171);
        ImGui.getStyle().setColor(ImGuiCol.TitleBg, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TitleBgActive, 70, 70, 90, 255);
        ImGui.getStyle().setColor(ImGuiCol.TitleBgCollapsed, 57, 58, 74, 65);
        ImGui.getStyle().setColor(ImGuiCol.MenuBarBg, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarBg, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarGrab, 104, 105, 128, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarGrabHovered, 134, 135, 160, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarGrabActive, 191, 190, 178, 255);
        ImGui.getStyle().setColor(ImGuiCol.CheckMark, 185, 175, 141, 255);
        ImGui.getStyle().setColor(ImGuiCol.SliderGrab, 77, 83, 101, 255);
        ImGui.getStyle().setColor(ImGuiCol.SliderGrabActive, 112, 113, 138, 255);
        ImGui.getStyle().setColor(ImGuiCol.Button, 104, 105, 128, 255);
        ImGui.getStyle().setColor(ImGuiCol.ButtonHovered, 104, 105, 128, 248);
        ImGui.getStyle().setColor(ImGuiCol.ButtonActive, 104, 105, 128, 171);
        ImGui.getStyle().setColor(ImGuiCol.Header, 121, 124, 146, 248);
        ImGui.getStyle().setColor(ImGuiCol.HeaderHovered, 164, 164, 186, 248);
        ImGui.getStyle().setColor(ImGuiCol.HeaderActive, 182, 182, 206, 248);
        ImGui.getStyle().setColor(ImGuiCol.Separator, 50, 50, 50, 255);
        ImGui.getStyle().setColor(ImGuiCol.SeparatorHovered, 110, 110, 110, 255);
        ImGui.getStyle().setColor(ImGuiCol.SeparatorActive, 180, 180, 180, 255);
        ImGui.getStyle().setColor(ImGuiCol.ResizeGrip, 95, 95, 95, 255);
        ImGui.getStyle().setColor(ImGuiCol.ResizeGripHovered, 230, 230, 230, 255);
        ImGui.getStyle().setColor(ImGuiCol.ResizeGripActive, 37, 37, 37, 255);
        ImGui.getStyle().setColor(ImGuiCol.Tab, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TabHovered, 79, 82, 94, 102);
        ImGui.getStyle().setColor(ImGuiCol.TabActive, 88, 92, 102, 171);
        ImGui.getStyle().setColor(ImGuiCol.TabUnfocused, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TabUnfocusedActive, 57, 58, 74, 65);
        ImGui.getStyle().setColor(ImGuiCol.DockingPreview, 0, 0, 0, 0);
        ImGui.getStyle().setColor(ImGuiCol.DockingEmptyBg, 90, 90, 90, 255);
        ImGui.getStyle().setColor(ImGuiCol.PlotLines, 0, 0, 0, 0);
        ImGui.getStyle().setColor(ImGuiCol.PlotLinesHovered, 0, 0, 0, 0);
        ImGui.getStyle().setColor(ImGuiCol.PlotHistogram, 0, 0, 0, 0);
        ImGui.getStyle().setColor(ImGuiCol.PlotHistogramHovered, 0, 0, 0, 0);
        ImGui.getStyle().setColor(ImGuiCol.TableHeaderBg, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableBorderStrong, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableBorderLight, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableRowBg, 45, 47, 59, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableRowBgAlt, 57, 58, 74, 255);
        ImGui.getStyle().setColor(ImGuiCol.TextSelectedBg, 82, 85, 103, 255);
        ImGui.getStyle().setColor(ImGuiCol.DragDropTarget, 95, 103, 121, 255);
        ImGui.getStyle().setColor(ImGuiCol.NavHighlight, 70, 70, 90, 255);
        ImGui.getStyle().setColor(ImGuiCol.NavWindowingHighlight, 70, 70, 90, 255);
        ImGui.getStyle().setColor(ImGuiCol.NavWindowingDimBg, 57, 58, 74, 51);
        ImGui.getStyle().setColor(ImGuiCol.ModalWindowDimBg, 57, 58, 74, 89);
    }
}