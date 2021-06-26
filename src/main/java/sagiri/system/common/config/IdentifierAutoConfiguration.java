package sagiri.system.common.config;

import artoria.identifier.JdbcTimeBasedIdGenerator;
import artoria.identifier.LongIdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * ID 生成相关的配置类.
 * @author Kahle
 */
@Configuration
public class IdentifierAutoConfiguration {
    private static Logger log = LoggerFactory.getLogger(IdentifierAutoConfiguration.class);
    private static final String EXPIRE_COLUMN = "expire";
    private static final String VALUE_COLUMN = "value";
    private static final String NAME_COLUMN = "name";
    private static final String TABLE_NAME = "t_identifier";
    private TransactionTemplate transactionTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IdentifierAutoConfiguration(TransactionTemplate transactionTemplate, JdbcTemplate jdbcTemplate) {
        this.transactionTemplate = transactionTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean(name = "fileIdGenerator")
    public LongIdentifierGenerator fileIdGenerator() {
        JdbcTimeBasedIdGenerator idGenerator = new JdbcTimeBasedIdGenerator(transactionTemplate, jdbcTemplate);
        idGenerator.setExpireColumn(EXPIRE_COLUMN);
        idGenerator.setValueColumn(VALUE_COLUMN);
        idGenerator.setNameColumn(NAME_COLUMN);
        idGenerator.setTableName(TABLE_NAME);
        idGenerator.setName("ID_GENERATOR:FILE");
        return idGenerator;
    }

}
