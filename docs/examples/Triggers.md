# Trigger Events

Trigger events are methods that are called once as soon as a specific condition becomes true, and they can only be called again if the condition becomes false and then true again at a given time. To use a trigger event, you need to add it to the active events in the application using the "addTriggerEvent" method, passing the method to be called and the name of the event as parameters. Below is an example of this type of event:

```java
import org.ignite.core.app.Application;
import org.ignite.events.KeyCode;
import org.ignite.platform.windows.WindowsInput;
import org.ignite.system.functions.Trigger;

public class Sandbox extends Application {

    Trigger onTabPress = new Trigger();

    public void start() {
        addTriggerEvent(this::tabPrint, "tabPress");
    }

    public void update() {
        callTriggerEvent("tabPress", WindowsInput.isKeyPressed(KeyCode.Tab));
    }

    public void tabPrint() {
        System.out.println("Tab is pressed");
    }
}
```

It is also possible to attach descriptors to events, passing a list of attributes to manage them. Here's another example below:

```java
import org.ignite.core.app.Application;
import org.ignite.system.functions.Trigger;

public class Sandbox extends Application {

    Trigger onTabPress = new Trigger();

    public void start() {
        addTriggerEvent(this::funnyEvent, "funny event",
                "funny event will be called only one time when the application starts putting TRUE on the condition parameter of callTriggerEvent.",
                "Try calling the funnyEvent");
    }

    public void update() {
        callTriggerEvent("funny event", true);
    }

    public void funnyEvent() {
        System.out.println("Trigger events are funny!!!");
    }
}
```

Moreover, it is also possible to adapt the code to call methods with one or more parameters in an event. Below is another example:

```java
import org.ignite.core.app.Application;
import org.ignite.events.KeyCode;
import org.ignite.layers.ExampleLayer;
import org.ignite.layers.imgui.ImGuiLayer;
import org.ignite.platform.windows.WindowsInput;
import org.ignite.system.functions.Trigger;

public class Sandbox extends Application {

    Trigger onTabPress = new Trigger();

    public void start() {
        app.pushLayer(new ExampleLayer());
        app.pushOverlay(new ImGuiLayer(app));
        addTriggerEvent(this::tabPrint, "tabPress");
    }

    public void update() {
        callTriggerEvent("tabPress", WindowsInput.isKeyPressed(KeyCode.Tab));
    }

    public void tabPrint() {
        myMethodWithParameters("Method ", "with ", "multiple ", "params, ", "like ", "a ", "number: ", 120);
    }

    public void myMethodWithParameters(Object... args) {
        for (Object s : args)
            System.out.print(s.toString());
    }
}
```

In this example, we adapted the code so that the method tabPrint can call the method myMethodWithParameters with several different parameters.

These adaptations provide you with greater control over trigger events, allowing you to call associated methods with custom conditions and parameters.

I hope these examples are helpful in understanding how to work with custom trigger events. If you have any further questions or need more assistance, please feel free to ask. We are here to help!