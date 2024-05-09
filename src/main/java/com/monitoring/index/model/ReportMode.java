package com.monitoring.index.model;

import lombok.Data;

import java.util.TreeMap;

/**
 * @author Grace
 * @date 2024/5/7 - 15:03
 */
@Data
public class ReportMode
{
	public String repCode;
	public String versionId;
	public String reportDate;
	public String range;
	public String currency;
	public String unit;
	public String tabulator;
	public String principal;

	public String fileName;
	public TreeMap<Integer,TreeMap<String,String>> resultMap;

}