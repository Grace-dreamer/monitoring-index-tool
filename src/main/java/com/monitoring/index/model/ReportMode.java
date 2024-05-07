package com.monitoring.index.model;

import lombok.Data;

import java.util.Map;

/**
 * @author Grace
 * @date 2024/5/7 - 15:03
 */
@Data
public class ReportMode
{
	public String RepCode;
	public String VersionId;
	public String ReportDate;
	public String Range;
	public String Currency;
	public String Unit;
	public String Tabulator;
	public String Principal;
	public Map<Integer,Map<String,String>> resultMap;

}