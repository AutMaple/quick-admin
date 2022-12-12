package com.autmaple.oauth;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Generator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/quick_admin?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";

        List<String> tables = Arrays.asList("auth_permission", "auth_role", "auth_role_permission", "auth_user", "auth_user_role");
        List<String> prefix = Arrays.asList("auth_");
        String author = "AutMaple";
        // 模块名
        String moduleName = "/quick-admin-oauth/";
        // mapper对应的 xml 文件存放的位置
        String mapperXmlLocation = System.getProperty("user.dir") + moduleName + "src/main/resources";
        String baseLocation = System.getProperty("user.dir") + moduleName + "src/main/java";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author)
                            .enableSpringdoc() // 开启 SpringDoc
                            .outputDir(baseLocation) // 代码输出的文件夹
                            .dateType(DateType.ONLY_DATE)
                            .disableOpenDir();
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix(prefix) // 忽略表的前缀
                            .addInclude(tables) // 设置需要自动生成代码的表名

                            // mapper 相关的配置
                            .mapperBuilder()
                            .enableFileOverride()
                            .enableMapperAnnotation() // 开启 mapper 注解
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .enableBaseResultMap()

                            // service 相关的配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .enableFileOverride()

                            // 实体类相关的配置
                            .entityBuilder()
                            .enableTableFieldAnnotation()
                            .enableLombok()
                            .enableFileOverride()
                            .naming(NamingStrategy.underline_to_camel)

                            // controller 相关的配置
                            .controllerBuilder()
                            .enableFileOverride()
                            .formatFileName("%sController");
                })
                .packageConfig(builder -> {
                    // outputDir + parent
                    builder.parent("com.autmaple")
                            // 自动生成的代码放在 com.autmaple.oauth 文件夹下
                            .moduleName("oauth")
                            // 实体类放在 com.autmaple.oauth.entity 文件夹下
                            .entity("entity")
                            // service 层放在 com.autmaple.oauth.service 文件夹下
                            .service("service")
                            // service 的实现类放在 com.autmaple.oauth.service.impl 文件夹下
                            .serviceImpl("service.impl")
                            // controller 层放在 com.autmaple.oauth.controller 文件夹下
                            .controller("controller")
                            // mapper 层放在 com.autmaple.oauth.mapper 文件夹下
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlLocation)); // 设置 mapper xml 文件存放的的位置
                })
                // 设置模板引擎
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
