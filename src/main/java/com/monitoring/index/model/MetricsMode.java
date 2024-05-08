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
    public String RepCode;
    public String ReportDate;
    public String VersionId;
    public String Range;
    public String Currency;
    public String Unit;
    @ExportProperty(index = 7)
    public String metricsCode;
    @ExportProperty(index = 11)
    public String metricsValue;

}