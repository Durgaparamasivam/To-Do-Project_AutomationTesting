package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReusable 
{
	String filePath;
	File file;
	FileInputStream fip;
	Properties properties;
	
	public PropertiesReusable(String filePath) throws IOException {
		this.filePath = filePath;
		file = new File(filePath);
		fip = new FileInputStream(file);
		properties = new Properties();
		properties.load(fip);
	}
	
	public String getPropertyValue(String key) {
		String value = properties.getProperty(key);
		return value;
	}

}
