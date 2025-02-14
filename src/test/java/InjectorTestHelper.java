
import java.lang.reflect.Field;

/**
 * Вспомогательный класс для упрощения тестирования.
 */
public class InjectorTestHelper {

    /**
     * Внедряет зависимости вручную для тестирования.
     *
     * @param taskService         объект {@link TaskService}.
     * @param taskProcessor       объект {@link TaskProcessor}.
     * @param notificationSender  объект {@link NotificationSender}.
     * @return объект {@link TaskService} с внедренными зависимостями.
     */
    public TaskService injectMockDependencies(TaskService taskService, TaskProcessor taskProcessor,
                                              NotificationSender notificationSender) {
        try {
            Field taskProcessorField = TaskService.class.getDeclaredField("taskProcessor");
            taskProcessorField.setAccessible(true);
            taskProcessorField.set(taskService, taskProcessor);

            Field notificationSenderField = TaskService.class.getDeclaredField("notificationSender");
            notificationSenderField.setAccessible(true);
            notificationSenderField.set(taskService, notificationSender);
        } catch (Exception e) {
            throw new RuntimeException("Error injecting mock dependencies", e);
        }

        return taskService;
    }
}