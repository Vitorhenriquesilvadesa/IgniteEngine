package org.ignite.system.debbuging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DebugMessage {
    private String text;
    private DebugColor color;
    private DebugLevel level;
    private String origin;

    public DebugMessage(String text, DebugColor color, DebugLevel level, String origin) {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        this.text = "[" + formattedTime + "] [" + level.name() + "] ->   " + text;
        this.color = color;
        this.level = level;
        this.origin = origin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public DebugColor getColor() {
        return color;
    }

    public void setColor(DebugColor color) {
        this.color = color;
    }

    public DebugLevel getLevel() {
        return level;
    }

    public void setLevel(DebugLevel level) {
        this.level = level;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}

