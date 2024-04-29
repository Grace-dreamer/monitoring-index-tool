package util;

import com.monitoring.index.annotation.ExportProperty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Grace
 * @date 2024/4/28 - 14:32
 */
public class TxtConverter
{
	private static final String SOH_DELIMITER = "\u0001";
	public static <T> List<T> parseTxtData(String filePath, Class<T> clazz)
	{
		List<T> dataList = new ArrayList<>();
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
						if(index<=fieldArray.length){
							field.setAccessible(true);
							field.set(t, fieldArray[index]);
						}

					}

				}
				dataList.add(t);

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
		return null;
	}

	public static String[] getArray(String line)
	{
		String[] result = line.split(SOH_DELIMITER);
		return result;
	}
}