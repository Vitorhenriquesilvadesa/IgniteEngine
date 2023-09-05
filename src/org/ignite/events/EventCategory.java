package org.ignite.events;

import static org.ignite.core.macros.debug.Macros.*;

public final class EventCategory {

    public static final int None = 0;
    public static final int EventCategoryApplication = _bit(0);
    public static final int EventCategoryInput = _bit(1);
    public static final int EventCategoryKeyboard = _bit(2);
    public static final int EventCategoryMouse = _bit(3);
    public static final int EventCategoryMouseButton = _bit(4);
}
