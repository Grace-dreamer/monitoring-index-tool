package com.monitoring.index.model;


import lombok.Builder;
import lombok.Data;

/**
 * @author Grace
 * @date 2024/4/28 - 10:35
 */
@Data
@Builder
public class ExportIn {
    private String importFolder;
    private String exportFolder;

}
