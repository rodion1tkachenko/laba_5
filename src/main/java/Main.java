
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Injector injector = new Injector("src/main/resources/config.properties");
            TaskService taskService = injector.inject(new TaskService());
            taskService.executeTask("Sample Task");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}