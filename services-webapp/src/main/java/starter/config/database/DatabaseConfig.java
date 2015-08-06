package starter.config.database;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    @Autowired
    Environment env;

    @Bean
    public DataSource getDatasource() throws Exception {
        Properties dbProperties = new Properties();
        dbProperties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        dbProperties.setProperty("url", env.getProperty("db.url"));
        dbProperties.setProperty("username", env.getProperty("db.username"));
        dbProperties.setProperty("password", env.getProperty("db.password"));
        return BasicDataSourceFactory.createDataSource(dbProperties);
    }

    @Bean
    @Autowired
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    @Autowired
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setPackagesToScan("model");
        sessionFactory.setDataSource(dataSource);
        sessionFactory.afterPropertiesSet();

        return sessionFactory.getObject();
    }
}
