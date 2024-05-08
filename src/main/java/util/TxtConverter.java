package util;

import com.monitoring.index.annotation.ExportProperty;
import com.monitoring.index.model.ReportMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author Grace
 * @date 2024/4/28 - 14:32
 */
public class TxtConverter
{
	//private static final String SOH_DELIMITER = "\u0001";
	private static final String SOH_DELIMITER = "\\|";


	//解析TXT数据文件
	public static <T> ReportMode parseTxtData(String filePath, Class<T> clazz, Map<String,Integer> rowMap)
	{
		ReportMode reportMode=new ReportMode();
		Map<String,String> map=new HashMap<>();
		//将每一行有效数据读取出来
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] fieldArray=getArray(line);
				T t=clazz.newInstance();
				//获取clazz类上的属性
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					//判断属性上是否有指定注解
					if (field.isAnnotationPresent(ExportProperty.class)) {
						ExportProperty annotation = field.getAnnotation(ExportProperty.class);
						Integer index = annotation.index();
						if(index<fieldArray.length){
							field.setAccessible(true);
							field.set(t, fieldArray[index]);
						}

					}

				}
				Field field1 =t.getClass().getDeclaredField("metricsCode");
				Field field2 =t.getClass().getDeclaredField("metricsValue");
				String code= (String) field1.get(t);
				String value= (String) field2.get(t);
				map.put(code,value);


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		catch (NoSuchFieldException e)
		{
			throw new RuntimeException(e);
		}

		TreeMap<Integer,TreeMap<String,String>> result=parseLineNumber(map,rowMap);
		reportMode.setResultMap(result);
		return reportMode;
	}
   //将TXT文件 行数据按SOH间隔符分割
	public static String[] getArray(String line)
	{
		String[] result = line.split(SOH_DELIMITER);
		return result;
	}
	//将txt文件里map解析
	public static TreeMap<Integer,TreeMap<String,String>> parseLineNumber(Map<String,String> map,Map<String,Integer> rowMap)
	{
		TreeMap<Integer,TreeMap<String,String>> result = new TreeMap<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer k1, Integer k2) {
				// 对于字符串key升序排序
				return k1.compareTo(k2);
				// 若需要降序排序，则可以改为
				// return k2.compareTo(k1);
			}
		});

		map.forEach((key,value)->{
			TreeMap<String,String> rowData= new TreeMap<>(new Comparator<String>() {
				@Override
				public int compare(String k1, String k2) {
					// 对于字符串key升序排序
					return k1.compareTo(k2);
					// 若需要降序排序，则可以改为
					// return k2.compareTo(k1);
				}
			});
			String[] keys=key.split("\\.");

			//列
			String column=keys[keys.length-1];
			String rowKey=key.replace(column,"");
			//行
			Integer row=rowMap.get(rowKey);
			rowData.put(column,value);
			if(result.get(row)==null)
			{
				result.put(row,rowData);
			}else
			{
				result.get(row).putAll(rowData);
			}



		});

		return result;
	}
}