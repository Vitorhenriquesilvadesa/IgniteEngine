package org.ignite.layers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class GlobalKey extends Key<Widget> {

    private static Map<Integer, GlobalKey> keys = new HashMap<>();

    public GlobalKey(Widget widget){
        super(widget.getId(), widget);
        addKey(this);
    }

    private static void addKey(GlobalKey widget){
        keys.put(widget.id, widget);
    }

    public static GlobalKey get(int id){
        return keys.get(id);
    }

    public static Collection<GlobalKey> getAll(){
        return keys.values();
    }
}
