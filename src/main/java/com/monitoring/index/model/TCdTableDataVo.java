package com.monitoring.index.model;

import com.monitoring.index.annotation.ExportProperty;
import lombok.Data;

/**
 * @author Grace
 * @date 2024/4/28 - 11:36
 */
@Data
public class TCdTableDataVo
{
    @ExportProperty(index = 1)
    private String orgType;
    @ExportProperty(index = 2)
    private String orgName;
    @ExportProperty(index = 3)
    private String orgNo;

}