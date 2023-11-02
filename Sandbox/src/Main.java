import org.ignite.core.app.Application;

public class Main {
    public static void main(String[] args) {
        Application.init();
        Application.setInstance(Sandbox.class);
        Application.getInstance().run();
    }
}