package com.sunlx.eduservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 *  generator service code
 * author sunlx
 */

public class CodeGenerate {

    @Test
    public void main(){
        // 1. create code generate machine
        AutoGenerator mpg=new AutoGenerator();

        // 2.global setting
        GlobalConfig gc=new GlobalConfig();
        String projectPath=System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("sunlx");
        gc.setOpen(false);//is or not open resource manager after generator
        gc.setFileOverride(false);//is or not override code when again generator

        /**
         * mpg generator serivce layer code ,defalt interface name's first is I
         *
         */
         gc.setServiceName("%Service");
         gc.setIdType(IdType.ID_WORKER);
         gc.setDateType(DateType.ONLY_DATE);
         gc.setSwagger2(true);

         mpg.setGlobalConfig(gc);

         //3. datasource setting
        DataSourceConfig dsc=new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://81.70.41.63:3306/edu?useUnicode=true&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4 package setting
        PackageConfig pc=new PackageConfig();
        pc.setModuleName("serviceedu");
        pc.setParent("com.sunlx");
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        //5 Strategy setting （策略配置）
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setInclude("edu_teacher");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
        strategyConfig.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategyConfig.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
        strategyConfig.setRestControllerStyle(true); //restful api风格控制器
        strategyConfig.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        mpg.setStrategy(strategyConfig);

        //6 exute
        mpg.execute();
    }

}
