package com.monitoring.index.model;

import com.monitoring.index.annotation.ExportProperty;
import lombok.Data;

/**
 * @author Grace
 * @date 2024/4/28 - 11:36
 */
@Data
public class MetricsMode
{
    private String RepCode;
    private String ReportDate;
    private String VersionId;
    private String Range;
    private String Currency;
    private String Unit;
    @ExportProperty(index = 7)
    private String metricsCode;
    @ExportProperty(index = 11)
    private String metricsValue;

}