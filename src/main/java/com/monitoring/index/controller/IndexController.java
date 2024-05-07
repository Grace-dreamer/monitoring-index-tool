package com.monitoring.index.controller;
import com.monitoring.index.model.ExportOut;
import com.monitoring.index.model.MetricsMode;
import com.monitoring.index.model.ReportMode;
import util.TxtConverter;
import util.XmlConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Grace
 * @date 2024/4/28 - 9:51
 */
public class IndexController
{
    private String importFolder;
    private String exportFolder;
    private static final Map<String, Integer> g0109Map;
    static
    {
        g0109Map = new HashMap<String, Integer>();
        g0109Map.put("G0109.1..", 7);
        g0109Map.put("G0109.2..", 8);
        g0109Map.put("G0109.3..", 9);
        g0109Map.put("G0109.4..", 10);
        g0109Map.put("G0109.5..", 11);
        g0109Map.put("G0109.6..", 12);
        g0109Map.put("G0109.7..", 13);
        g0109Map.put("G0109.8..", 14);
        g0109Map.put("G0109.9..", 15);
        g0109Map.put("G0109.10..", 16);
        g0109Map.put("G0109.11..", 17);
    }

    //方法一：输入参数为指定文件夹，导出到指定文件夹
    public ExportOut export(String importFolder,String exportFolder)
    {
        //获取目标文件路径下所有的txt文件
         List<File> importFiles = getAllFiles(importFolder);
        //总共多少个excel文件
        int n = importFiles.size();
        if (n < 1)
        {
            return ExportOut.builder().success(false).msg("文件夹里没有excel文件").build();
        }
        for(File file :importFiles)
        {
            ReportMode reportMode= TxtConverter.parseTxtData(file.getAbsolutePath(), MetricsMode.class, g0109Map);
            XmlConverter.createXml(reportMode,exportFolder,reportMode.getRepCode());
        }

        return ExportOut.builder().success(true).build();
    }
    public static List<File> getAllFiles(String importPath)
    {
        List<File> fileList = new ArrayList<>();
        File folder = new File(importPath);
        File[] files = folder.listFiles();
        if (files != null)
        {
            for (File file : files)
            {
                if (file.isFile() && (file.getName().endsWith(".txt")))
                {
                    fileList.add(file);
                }
                else if (file.isDirectory())
                {
                    List<File> subFiles = getAllFiles(file.getAbsolutePath());
                    fileList.addAll(subFiles);
                }
            }
        }
        return fileList;
    }

}