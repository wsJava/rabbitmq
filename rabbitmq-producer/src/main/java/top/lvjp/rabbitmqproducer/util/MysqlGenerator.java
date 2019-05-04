package top.lvjp.rabbitmqproducer.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvjp
 * @date 2019/4/24
 */
public class MysqlGenerator {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/rabbitmq?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "mysql_lv";

//    private static final String MODULE_NAME = "rabbitmqproducer";

    private static final String AUTHOR = "lvjp";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath + "/rabbitmq-producer/src/main/java");
        config.setAuthor(AUTHOR);
        config.setOpen(false);
        config.setBaseResultMap(true);
        config.setBaseColumnList(true);
        mpg.setGlobalConfig(config);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(MODULE_NAME);
        pc.setParent("top.lvjp.rabbitmqproducer");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        String templatePath = "/templates/mapper.xml.ftl";
//        String templatePath = "/templates/mapper.xml.vm";
        List<FileOutConfig> focList = new ArrayList<>();

        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/" + "mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("top.lvjp.ssm_student.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
//        strategy.setSuperEntityColumns("id", "create_time", "update_time");
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass();
//        strategy.setInclude("major","student","academy");
        strategy.setInclude("t_order", "broker_message_log");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
