
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс для автоматического внедрения зависимостей.
 * Использует аннотацию {@link AutoInjectable} для поиска полей,
 * которые необходимо инициализировать, и инициализирует их с использованием
 * конфигурации из файла properties.
 */
public class Injector {

    private final Properties properties;

    /**
     * Создает новый экземпляр Injectable и загружает конфигурацию.
     *
     * @param propertiesFilePath путь к файлу конфигурации
     * @throws IOException если файл конфигурации не найден или не может быть прочитан.
     */
    public Injector(String propertiesFilePath) throws IOException {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        }
    }

    /**
     * Внедряет зависимости в объект, используя аннотацию {@link AutoInjectable}
     *
     * @param object объект, в который необходимо внедрить зависимость
     * @param <T>    тип объекта
     * @return объект с внедренными зависимостями.
     * @throws RuntimeException если возникают ошибки при создании экземпляров или установке значений
     */
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implClassName = properties.getProperty(interfaceName);

                if (implClassName == null) {
                    // Если реализация не найдена, выбрасываем исключение
                    throw new RuntimeException("No implementation found for interface: " + interfaceName);
                }

                try {
                    Class<?> implClass = Class.forName(implClassName);
                    Object implInstance = implClass.getDeclaredConstructor().newInstance();

                    field.setAccessible(true);
                    field.set(object, implInstance);
                } catch (Exception e) {
                    throw new RuntimeException("Error injecting field: " + field.getName(), e);
                }
            }
        }

        return object;
    }
}