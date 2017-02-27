package org.qcri.micromappers.nlp.utility.configurator;

import org.apache.log4j.Logger;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyFileException;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyNotRecognizedException;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyNotSetException;
import org.qcri.micromappers.nlp.exception.DirectoryNotWritableException;

import java.io.File;
import java.util.Map;

/**
 * @author Kushal
 *
 */
public abstract class BaseConfigurator implements Configurator {

	private static final Logger logger = Logger.getLogger(BaseConfigurator.class);

	private Map<String, String> propertyMap;

	@Override
	public void initProperties(String configLoadFileName,
			ConfigurationProperty[] configurationProperties)
			throws ConfigurationPropertyNotSetException,
			ConfigurationPropertyNotRecognizedException,
			ConfigurationPropertyFileException {
		logger.info("Initializing Properties: " + configurationProperties
				+ " from file : " + configLoadFileName);
		propertyMap = ConfigurationPropertiesUtil.readConfigurations(
				configurationProperties, configLoadFileName);
	}

	@Override
	public String getProperty(ConfigurationProperty property) {
		return propertyMap.get(property.getName());
	}
	
	@Override
	public String getProperty(String propertyName) {
		return propertyMap.get(propertyName);
	}
	
	@Override
	public void setProperty(String property, String newValue) {
		if(propertyMap.containsKey(property)){
			propertyMap.put(property,newValue);
		}
		else{
			logger.error("Encountered an unexpected property:'"
					+ property + "' with the value: "
					+ newValue);
			throw new ConfigurationPropertyNotRecognizedException(
					property, null);
		}
	}
	
	@Override
	public void directoryIsWritable(String propertyName) throws DirectoryNotWritableException{
		String directoryLocation = this.getProperty(propertyName);
		File f = new File(directoryLocation);
		if(!f.canWrite()) {
			logger.info(propertyName+ " = " +directoryLocation+ " is not writable. Please verify if this is a valid writable directory.");
			throw new DirectoryNotWritableException(propertyName, directoryLocation);
		}
	}
}
