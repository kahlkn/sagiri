package sagiri;

import artoria.generator.JavaCodeGenerator;
import artoria.jdbc.DatabaseClient;
import artoria.jdbc.SimpleDataSource;
import artoria.template.VelocityRenderer;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class GenerateTest {
    private static DatabaseClient databaseClient = new DatabaseClient(new SimpleDataSource());

    @Test
    public void generate() {
        JavaCodeGenerator generator = new JavaCodeGenerator().newCreator()
                .setDatabaseClient(databaseClient)
                .setBaseTemplatePath("classpath:templates/generator/java/custom1")
                .setBaseOutputPath("src\\main\\java")
                .setBasePackageName("sagiri.collect")
                .setRenderer(new VelocityRenderer())
                .addRemovedTableNamePrefixes("t_")
//                .addExcludedTables("t_user_bak")
                .addReservedTables("t_event_record")
                ;
        generator.addAttribute("author", "Kahle");
        generator.generate();
    }

}
