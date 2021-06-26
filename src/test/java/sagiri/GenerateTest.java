package sagiri;

import artoria.engine.template.VelocityTemplateEngine;
import artoria.generator.JavaCodeGenerator1;
import artoria.jdbc.DatabaseClient;
import artoria.jdbc.SimpleDataSource;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class GenerateTest {
    private static DatabaseClient databaseClient = new DatabaseClient(new SimpleDataSource());

    @Test
    public void generate() {
        JavaCodeGenerator1 generator = new JavaCodeGenerator1().newCreator()
                .setDatabaseClient(databaseClient)
                .setBaseTemplatePath("classpath:templates/generator/java/custom1")
                .setBaseOutputPath("src\\main\\java")
                .setBasePackageName("sagiri.system")
                .setTemplateEngine(new VelocityTemplateEngine())
                .addRemovedTableNamePrefixes("t_")
//                .addExcludedTables("t_user_bak")
                .addReservedTables("t_system_user")
                ;
        generator.addAttribute("author", "Kahle");
        generator.generate();
    }

}
