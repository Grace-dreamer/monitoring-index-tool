package com.monitoring.index.controller;

import com.monitoring.index.model.ExportIn;
import com.monitoring.index.model.ExportOut;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Grace
 * @date 2024/4/28 - 9:51
 */
public class IndexController
{

    //方法一：输入参数为指定文件夹，导出到指定文件夹
    public ExportOut export(ExportIn exportIn)
    {
        //获取目标文件路径下所有的txt文件
         List<File> importFiles = getAllFiles(exportIn.getImportFolder());
        //总共多少个excel文件
        int n = importFiles.size();
        if (n < 1)
        {
            return ExportOut.builder().success(false).msg("文件夹里没有excel文件").build();
        }
        for(File file :importFiles)
        {

        }

        return null;
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