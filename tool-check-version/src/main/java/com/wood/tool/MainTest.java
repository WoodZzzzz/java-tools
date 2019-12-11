package com.wood.tool;

import java.util.List;

/**
 * Author: Dreamer-1
 * Date: 2019-03-01
 * Time: 10:13
 * Description: 示例程序入口类
 */
public class MainTest {
 
    public static void main(String[] args) {
        // 设定Excel文件所在路径
        String excelFileName = "C:\\Users\\thinkpad\\Desktop\\122.xls";
        // 读取Excel文件内容
        List<ExcelModuleVO> readResult = ExcelReader.readExcel(excelFileName);
        System.out.println(readResult);
        // todo 进行业务操作
    }
 
}