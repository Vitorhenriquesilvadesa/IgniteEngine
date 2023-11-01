package org.ignite.system.debbuging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class DebugConsole {

    private static List<DebugMessage> debugMessages = new ArrayList<>();

    public static void sendMessage(DebugMessage message){
        debugMessages.add(message);
    }

    public static List<DebugMessage> getDebugMessages(){
        return debugMessages;
    }
}
