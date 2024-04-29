package util;

import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import com.monitoring.index.model.TCdTableDataVo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlConverter {
	public static String convertListToXml(List<TCdTableDataVo> list) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			Element rootElement = document.createElement("Students");
			document.appendChild(rootElement);

			for (TCdTableDataVo student : list) {
				Element studentElement = document.createElement("Student");
				rootElement.appendChild(studentElement);

				Element nameElement = document.createElement("orgName");
				nameElement.appendChild(document.createTextNode(student.getOrgName()));
				studentElement.appendChild(nameElement);

				Element ageElement = document.createElement("orgNo");
				ageElement.appendChild(document.createTextNode(String.valueOf(student.getOrgNo())));
				studentElement.appendChild(ageElement);
			}

			return documentToString(document);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String documentToString(Document document) {
		// 在这里将Document对象转换成字符串并返回
		return document.toString();
	}
}