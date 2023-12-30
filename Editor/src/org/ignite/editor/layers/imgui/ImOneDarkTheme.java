package org.ignite.editor.layers.imgui;

import imgui.ImGui;
import imgui.flag.ImGuiCol;

public class ImOneDarkTheme implements ImGuiTheme {
    @Override
    public void applyTheme() {
        ImGui.getStyle().setColor(ImGuiCol.Text, 200, 200, 200, 255);
        ImGui.getStyle().setColor(ImGuiCol.TextDisabled, 125, 125, 125, 255);
        ImGui.getStyle().setColor(ImGuiCol.WindowBg, 28, 28, 28, 255);
        ImGui.getStyle().setColor(ImGuiCol.ChildBg, 37, 37, 37, 255);
        ImGui.getStyle().setColor(ImGuiCol.PopupBg, 37, 37, 37, 255);
        ImGui.getStyle().setColor(ImGuiCol.Border, 80, 80, 80, 128);
        ImGui.getStyle().setColor(ImGuiCol.BorderShadow, 0, 0, 0, 0);
        ImGui.getStyle().setColor(ImGuiCol.FrameBg, 44, 44, 44, 255);
        ImGui.getStyle().setColor(ImGuiCol.FrameBgHovered, 68, 68, 68, 102);
        ImGui.getStyle().setColor(ImGuiCol.FrameBgActive, 39, 39, 39, 255);
        ImGui.getStyle().setColor(ImGuiCol.TitleBg, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.TitleBgActive, 44, 44, 44, 255);
        ImGui.getStyle().setColor(ImGuiCol.TitleBgCollapsed, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.MenuBarBg, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarBg, 37, 37, 37, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarGrab, 58, 58, 58, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarGrabHovered, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.ScrollbarGrabActive, 61, 61, 61, 255);
        ImGui.getStyle().setColor(ImGuiCol.CheckMark, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.SliderGrab, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.SliderGrabActive, 61, 61, 61, 255);
        ImGui.getStyle().setColor(ImGuiCol.Button, 58, 58, 58, 255);
        ImGui.getStyle().setColor(ImGuiCol.ButtonHovered, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.ButtonActive, 61, 61, 61, 255);
        ImGui.getStyle().setColor(ImGuiCol.Header, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.HeaderHovered, 68, 68, 68, 102);
        ImGui.getStyle().setColor(ImGuiCol.HeaderActive, 44, 44, 44, 255);
        ImGui.getStyle().setColor(ImGuiCol.Separator, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.SeparatorHovered, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.SeparatorActive, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.ResizeGrip, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.ResizeGripHovered, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.ResizeGripActive, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.Tab, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.TabHovered, 68, 68, 68, 102);
        ImGui.getStyle().setColor(ImGuiCol.TabActive, 44, 44, 44, 255);
        ImGui.getStyle().setColor(ImGuiCol.TabUnfocused, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.TabUnfocusedActive, 68, 68, 68, 102);
        ImGui.getStyle().setColor(ImGuiCol.DockingPreview, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.DockingEmptyBg, 68, 68, 68, 102);
        ImGui.getStyle().setColor(ImGuiCol.PlotLines, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.PlotLinesHovered, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.PlotHistogram, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.PlotHistogramHovered, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableHeaderBg, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableBorderStrong, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableBorderLight, 68, 68, 68, 102);
        ImGui.getStyle().setColor(ImGuiCol.TableRowBg, 54, 54, 54, 255);
        ImGui.getStyle().setColor(ImGuiCol.TableRowBgAlt, 63, 63, 63, 255);
        ImGui.getStyle().setColor(ImGuiCol.TextSelectedBg, 58, 58, 58, 255);
        ImGui.getStyle().setColor(ImGuiCol.DragDropTarget, 109, 109, 176, 230);
        ImGui.getStyle().setColor(ImGuiCol.NavHighlight, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.NavWindowingHighlight, 76, 76, 76, 255);
        ImGui.getStyle().setColor(ImGuiCol.NavWindowingDimBg, 63, 63, 63, 51);
        ImGui.getStyle().setColor(ImGuiCol.ModalWindowDimBg, 57, 57, 57, 89);
    }
}
