package org.ignite.system.debbuging;

import java.util.ArrayList;
import java.util.List;

public final class DebugConsole {

    private static List<String> debugMessages = new ArrayList<>();

    public static void sendMessage(String message){
        debugMessages.add("[DEBUGGING MESSAGE]   ->   " + message);
    }

    public static List<String> getDebugMessages(){
        return debugMessages;
    }
}
