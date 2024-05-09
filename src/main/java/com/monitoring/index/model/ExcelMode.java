package com.monitoring.index.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author Grace
 * @date 2024/5/9 - 9:26
 */
@EqualsAndHashCode
@Data
public class ExcelMode
{
    @ExcelProperty(index = 0)
    private String orderNumber;
    @ExcelProperty(index = 1)
    private String metrics;
    private String rowNumber;

}