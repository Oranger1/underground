//package cn.oranger.cs;
//
///**
// * @author Oranger
// * @date 2020/12/29
// */
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
//import com.baomidou.mybatisplus.generator.config.po.TableFill;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
//import javax.sql.DataSource;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Logger;
//
//
///**
// * @author Oranger
// * @date 2020/12/1
// */
//class CodeGenerator {
//
//    private static String table= "take_record";
//    private static String basePackage = "cn.oranger.cs";
//
//    public static void main(String[] args) {
//        //需要构建一个代码自动生成器对象
//        AutoGenerator mpg = new AutoGenerator();
//        //配置策略
//
//        //1、全局配置
//        GlobalConfig gc = new GlobalConfig();
//
//        baseConfig(gc);
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath+"/src/main/java");
//        mpg.setGlobalConfig(gc);
//
//        //2、数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUrl("jdbc:mysql://localhost:3306/underground?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        mpg.setDataSource(dsc);
//
//        TemplateConfig targetTemplate = new TemplateConfig();
//        targetTemplate.setXml("");
//
//        //3、包的配置
//        PackageConfig pc =  new PackageConfig();
//
//        pc.setParent(basePackage);
//        mpg.setPackageInfo(pc);
//
//        mpg.setTemplate(targetTemplate);
//
//        //4、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        // 设置要映射的表名
//        strategy.setInclude(table);
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        // 自动lombok；
//        strategy.setEntityLombokModel(true);
//        //是否配置逻辑删除
//        strategy.setLogicDeleteFieldName("deleted");
//        // 自动填充配置
//        TableFill createTime = new TableFill("creation_time", FieldFill.INSERT);
//        TableFill modifiedTime = new TableFill("updateion_time", FieldFill.INSERT_UPDATE);
//        ArrayList<TableFill> tableFills = new ArrayList<>();
//        tableFills.add(createTime);
//        tableFills.add(modifiedTime);
//        strategy.setTableFillList(tableFills);
//
//        // 乐观锁
//        strategy.setVersionFieldName("version");
//        strategy.setRestControllerStyle(true);
//        strategy.setControllerMappingHyphenStyle(true);
//        mpg.setStrategy(strategy);
//
//
//        mpg.execute(); //执行
//
//    }
//
//    public static void baseConfig(GlobalConfig gc){
//        gc.setAuthor("Oranger");
//        gc.setOpen(false);
//        // 是否覆盖
//        gc.setFileOverride(true);
//        // 去Service的I前缀
//        gc.setServiceName("%sService");
//        gc.setIdType(IdType.ID_WORKER);
//        gc.setDateType(DateType.ONLY_DATE);
//        gc.setSwagger2(true);
//    }
//}
