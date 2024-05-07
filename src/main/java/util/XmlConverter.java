package util;


import com.monitoring.index.model.ReportMode;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class XmlConverter {

	/**
	 * 将list数据文件生成xml方法
	 */
	public static void createXml(ReportMode model,String outPath,String fileName){
		try {
			// 1、创建document对象
			Document document = DocumentHelper.createDocument();
			// 2、创建根节点rss
			Element cbrcReports = document.addElement("CbrcReports");
			// 3、向cbrcReports节点添加xmlns属性
			cbrcReports.addAttribute("xmlns", "http://www.cbrc.gov.cn/report/1104");
			Element Report = cbrcReports.addElement("Report");
			Report.addAttribute("Report","");
			Report.addAttribute("VersionId","");
			Report.addAttribute("ReportDate","");
			Report.addAttribute("Range","");
			Report.addAttribute("Currency","");
			Report.addAttribute("Unit","");

			Element info = Report.addElement("info");
			Element tabulator=info.addElement("Tabulator");
			tabulator.setText("");
			Element auditor=info.addElement("Auditor");
			auditor.setText("");
			Element principal=info.addElement("Principal");
			principal.setText("");

			Element data = Report.addElement("Data");
			data.addAttribute("part","");
			TreeMap<Integer,TreeMap<String,String>> treeMap=model.getResultMap();
			for (Integer key : treeMap.keySet()) {
				Element row = data.addElement("Row");
				data.addAttribute("id", String.valueOf(key));
				TreeMap<String,String> rowMap=treeMap.get(key);
				for(String cellKey:rowMap.keySet())
				{
					Element cell = data.addElement("Cell");
					cell.addAttribute("id",cellKey);
					cell.setText(rowMap.get(cellKey));
				}

			}

			// 5、设置生成xml的格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			// 设置编码格式
			format.setEncoding("UTF-8");
			// 6、生成xml文件
			File file = new File(outPath+"/"+fileName);
			XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
			// 设置是否转义，默认使用转义字符
			writer.setEscapeText(false);
			writer.write(document);
			writer.close();

			System.out.println("生成"+model.getRepCode()+"xml成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("生成"+model.getRepCode()+"xml失败");
		}
	}


}