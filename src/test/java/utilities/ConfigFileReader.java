package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath= "./configs//config.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getTestDataFile(){
		String driverPath = properties.getProperty("TestDataSheet");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("TestDataSheet not specified in the Configuration.properties file.");		
	}
	

	
	public String getPropertyValue(String arg1) {
		String driverPath = properties.getProperty(arg1);
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException( arg1 + " not specified in the Configuration.properties file.");		
	
	}



}
