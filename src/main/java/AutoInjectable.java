
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для автоматического внедрения зависимостей
 * Поля, помеченные этой аннотацией, будут автоматически инициализированы экземплярами классов, указанных
 * в файле конфигурации
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {
}