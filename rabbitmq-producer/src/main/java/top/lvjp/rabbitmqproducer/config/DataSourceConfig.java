package top.lvjp.rabbitmqproducer.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lvjp
 * @date 2019/5/3
 */
//@Configuration
//@ConfigurationProperties(prefix = "spring.datasource.hikari")
//@EnableConfigurationProperties(DataSourceConfig.class)
public class DataSourceConfig extends HikariConfig {

//    @Bean
    public DataSource hikariDataSource(){
        this.addDataSourceProperty("maxWait","800");
        this.addDataSourceProperty("connectionProperties","socketTimeout=3000;connectTimeout=1200");
        this.addDataSourceProperty("useUnicode", "true");
        this.addDataSourceProperty("characterEncoding", "utf8");
        this.setConnectionInitSql("SET NAMES utf8mb4");
        return new HikariDataSource(this);
    }
}
