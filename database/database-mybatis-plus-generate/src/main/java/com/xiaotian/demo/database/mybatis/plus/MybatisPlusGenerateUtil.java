package com.xiaotian.demo.database.mybatis.plus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * Mybatis 代码生成公工具
 *
 * @author sunfeilong   (sunfeilong@eglsgame.com)
 * @version V1.0
 * @date 2019/8/17 13:38
 */
public class MybatisPlusGenerateUtil {

    public static void main(String[] args) {
        //初始化 core 模块代码
        //Config core = Config.CORE;
        //core.setTables("");
        //generate(Config.CORE);
        //初始化 cr 模块代码
        //generate(Config.CR);
    }


    public enum Config {

        /**
         * Core 模块配置
         */
        CORE("D:\\work\\hades\\hades\\hades-core\\", "com.egls.cashloan.hades", "core", "arc_sys_menu,arc_sys_menu_perm,arc_sys_perm,arc_sys_role,arc_sys_role_menu,,arc_sys_role_perm,arc_sys_user,arc_sys_user_role,arc_sys_dict,arc_sys_dict_detail"),
        CR("D:\\work\\hades\\hades\\hades-cr\\", "com.egls.cashloan.hades", "cr", "cl_user_rule,cr_scene_business,cr_scene_business_log,cr_third_credit_req_log,cr_tpp,cr_tpp_business"),
        ;

        /**
         * 项目路径
         */
        private final String projectPath;
        /**
         * 项目包名
         */
        private final String packageName;

        /**
         * 模块儿名
         */
        private final String modelName;
        /**
         * 数据库表明，多个用逗号分隔
         */
        private String tables;

        Config(String projectPath, String packageName, String modelName, String tables) {
            this.projectPath = projectPath;
            this.packageName = packageName;
            this.modelName = modelName;
            this.tables = tables;
        }


        /**
         * 设置生成的表，多表以逗号分隔
         *
         * @param tables 表，多个用逗号分隔
         */
        public void setTables(String tables) {
            this.tables = tables;
        }
    }

    /**
     * 根据配置生成Mapper等文件
     *
     * @param generateConfig 初始化配置
     */
    public static void generate(Config generateConfig) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig config = config();
        mpg.setGlobalConfig(config);
        mpg.setDataSource(datasource());
        PackageConfig packageInfo = packageConfig();
        mpg.setPackageInfo(packageInfo);

        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = generateConfig.projectPath + "/src/main/resources/mapper/" + packageInfo.getModuleName() + "/";
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        InjectionConfig injectionConfig = injectionConfig();
        injectionConfig.setFileOutConfigList(focList);
//        injectionConfig.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                //不创建 Controller 文件
//                if (fileType == FileType.CONTROLLER) {
//                    return false;
//                }
//                return true;
//            }
//        });
        mpg.setCfg(injectionConfig);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);

        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(generateConfig.tables.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageInfo.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static GlobalConfig config(Config generateConfig) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(generateConfig.projectPath + "/src/main/java");
        globalConfig.setAuthor("sunfielong");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setFileOverride(false);
        globalConfig.setDateType(DateType.TIME_PACK);
        globalConfig.setOpen(false);
        return globalConfig;
    }

    private static DataSourceConfig datasource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://10.0.8.36:3306/cashloan_hades?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        // dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("tuhOSw2Cdg)W");
        return dataSourceConfig;
    }

    private static PackageConfig packageConfig(Config generateConfig) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(generateConfig.modelName);
        packageConfig.setParent(generateConfig.packageName);
        return packageConfig;
    }

    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
    }
}
