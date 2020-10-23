package sagiri.core.common;

import artoria.util.StringUtils;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setFileOverride(true);
        gc.setOpen(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);

        gc.setAuthor("liaopeng");
        gc.setOpen(false);
//      gc.setSwagger2(true); //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc =
                new DataSourceConfig().setDbQuery(new MySqlQuery() {

                    /**
                     * 重写父类预留查询自定义字段<br>
                     * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
                     * 模板中调用：  table.fields 获取所有字段信息，
                     * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
                     */
                    @Override
                    public String[] fieldCustom() {
                        return new String[]{"NULL", "PRIVILEGES"};
                    }
                });

        dsc.setUrl("jdbc:mysql://localhost:3306/movie?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
//      dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
//        pc.setParent("com.study.mybatisplus");
        mpg.setPackageInfo(pc);

        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//
//        // 如果模板引擎是 freemarker
//        String templatePath = "/templates/mapper.xml.ftl";
//        // 如果模板引擎是 velocity
//        // String templatePath = "/templates/mapper.xml.vm";
//
//        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
         /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);

        // 配置模板
//        TemplateConfig templateConfig = new TemplateConfig();
//
//        // 配置自定义输出模板
//        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        templateConfig.setEntity("templates/entity.java");
//        templateConfig.setService("templates/service.java");
//        templateConfig.setServiceImpl("templates/serviceImpl.java");
//        templateConfig.setController("templates/controller.java");
//        templateConfig.setMapper("templates/mapper.java");
//        templateConfig.setXml("templates/mapper.xml");
//
//        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.mybatisplus.movie.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.mybatisplus.movie.common.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setEntityColumnConstant(true);
        strategy.setEntityBuilderModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);

        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        //自定义文件生成路径，包路径
        //        //这里调用customPackagePath方法，使用可以自己在内部灵活配置路径
        //        //如果不调用该方法、就会使用MyBatis-Plus默认的文件生成路径和包路径生成文件、但可以使用上面的PackageConfig做一些简单的配置
        customPackagePath(pc,mpg);

        mpg.execute();
    }

    /**
     * 自定义包路径，文件生成路径，这边配置更灵活
     * 虽然也可以使用InjectionConfig设置FileOutConfig的方式设置路径
     * 这里直接使用Map方式注入ConfigBuilder配置对象更加直观
     */
    public static void customPackagePath(PackageConfig pc,AutoGenerator mpg) throws NoSuchFieldException, IllegalAccessException {

        String projectPath = System.getProperty("user.dir");
        String mavenPath = "\\src\\main\\java\\";
        String srcPath = projectPath+mavenPath;

        String moduleName = pc.getModuleName();

        /*
         * packageInfo配置controller、service、serviceImpl、entity、mapper等文件的包路径
         * 这里包路径可以根据实际情况灵活配置
         */
        Map<String,String> packageInfo = new HashMap<>();
        packageInfo.put(ConstVal.CONTROLLER, "com.study.mybatisplus.controller."+moduleName);
        packageInfo.put(ConstVal.SERVICE, "com.study.mybatisplus.services."+moduleName+".service");
        packageInfo.put(ConstVal.SERVICE_IMPL, "com.study.mybatisplus.services."+moduleName+".service.impl");
        packageInfo.put(ConstVal.ENTITY, "com.study.mybatisplus.entity."+moduleName);
        packageInfo.put(ConstVal.MAPPER, "com.study.mybatisplus.mapper."+moduleName);

        /*
         * pathInfo配置controller、service、serviceImpl、entity、mapper、mapper.xml等文件的生成路径
         * srcPath也可以更具实际情况灵活配置
         * 后面部分的路径是和上面packageInfo包路径对应的源码文件夹路径
         * 这里你可以选择注释其中某些路径，可忽略生成该类型的文件，例如:注释掉下面pathInfo中Controller的路径，就不会生成Controller文件
         */
        Map<String,String> pathInfo = new HashMap<>();
        pathInfo.put(ConstVal.CONTROLLER_PATH, srcPath + packageInfo.get(ConstVal.CONTROLLER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_PATH, srcPath + packageInfo.get(ConstVal.SERVICE).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, srcPath + packageInfo.get(ConstVal.SERVICE_IMPL).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.ENTITY_PATH, srcPath + packageInfo.get(ConstVal.ENTITY).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.MAPPER_PATH, srcPath + packageInfo.get(ConstVal.MAPPER).replaceAll("\\.", StringPool.BACK_SLASH + File.separator));
        pathInfo.put(ConstVal.XML_PATH, projectPath+"\\src\\main\\resources\\mapper\\"+moduleName);
        pc.setPathInfo(pathInfo);

        /*
         * 创建configBuilder对象，传入必要的参数
         * 将以上的定义的包路径packageInfo配置到赋值到configBuilder对象的packageInfo属性上
         * 因为packageInfo是私有成员变量，也没有提交提供公共的方法，所以使用反射注入
         * 为啥要这么干，看源码去吧
         */
        ConfigBuilder configBuilder = new ConfigBuilder(mpg.getPackageInfo(), mpg.getDataSource(), mpg.getStrategy(), mpg.getTemplate(), mpg.getGlobalConfig());
        Field packageInfoField = configBuilder.getClass().getDeclaredField("packageInfo");
        packageInfoField.setAccessible(true);
        packageInfoField.set(configBuilder,packageInfo);

        /*
         * 设置配置对象
         */
        mpg.setConfig(configBuilder);
    }

}
