
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit тесты для класса {@link TaskService}.
 */
class TaskServiceTest {

    private TaskService taskService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws Exception {
        // Инициализируем Injector с тестовым файлом конфигурации
        Injector injector = new Injector("src/test/resources/test-config.properties");
        taskService = injector.inject(new TaskService());

        // Перенаправляем стандартный вывод в ByteArrayOutputStream для проверки вывода
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testExecuteTask() {
        taskService.executeTask("Test Task");

        // Проверяем вывод
        String expectedOutput = "Processing task: Test Task\r\n" +
                "Sending email notification: Task completed: Test Task\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testGetTaskProcessor() {
        assertNotNull(taskService.getTaskProcessor());
        assertTrue(taskService.getTaskProcessor() instanceof SimpleTaskProcessor);
    }

    @Test
    void testGetNotificationSender() {
        assertNotNull(taskService.getNotificationSender());
        assertTrue(taskService.getNotificationSender() instanceof EmailNotificationSender);
    }

    // Восстанавливаем стандартный вывод после каждого теста
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
}