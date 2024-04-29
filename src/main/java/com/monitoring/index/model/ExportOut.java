package com.monitoring.index.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Grace
 * @date 2024/4/28 - 10:02
 */
@Data
@Builder
public class ExportOut {
	private boolean success;
	private String msg;
}
