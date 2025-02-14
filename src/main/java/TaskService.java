
public class TaskService {
    @AutoInjectable
    private TaskProcessor taskProcessor;

    @AutoInjectable
    private NotificationSender notificationSender;

    public void executeTask(String taskName) {
        taskProcessor.processTask(taskName);
        notificationSender.sendNotification("Task completed: " + taskName);
    }

    public TaskProcessor getTaskProcessor() {
        return taskProcessor;
    }

    public NotificationSender getNotificationSender() {
        return notificationSender;
    }
}