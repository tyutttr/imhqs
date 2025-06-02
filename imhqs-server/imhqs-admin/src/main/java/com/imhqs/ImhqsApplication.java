package com.imhqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ImhqsApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ImhqsApplication.class, args);
        System.out.println("\n" +
                "  ╭━╮╭━╮╱╱╭╮╱╱╭╮╱╭━━╮╭━━╮╭━━━╮\n" +
                "  ┃┃╰╯┃┃╱╱┃┃╱╱┃┃╰╯┏━╯╰┫┣╯┃╭━╮┃\n" +
                "  ┃╭╮╭╮┣━━┫┃╭╮┃┃╱╱┗━━╮┃┃╱┃╰━╯┃\n" +
                "  ┃┃┃┃┃┃╭╮┃╰╯╯┃┃╱╱╱╱╱┃┃┃╱┃╭━━╯\n" +
                "  ┃┃┃┃┃┃╰╯┃╭╮╭┫╰╮╱┏━╯┣┫┣╮┃┃\n" +
                "  ╰╯╰╯╰┻━━┻╯╰╯╰━╯╱╰━━╯╰━━╯╰╯\n" +
                "  \n" +
                "  (✿◠‿◠) 欢迎参加心理健康知识挑战！");
    }
}
