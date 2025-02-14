
public class SimpleTaskProcessor implements TaskProcessor {
    @Override
    public void processTask(String taskName) {
        System.out.println("Processing task: " + taskName);
    }
}