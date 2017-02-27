package org.qcri.micromappers.nlp.utility.configurator;

import org.qcri.micromappers.nlp.exception.ConfigurationPropertyFileException;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyNotRecognizedException;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyNotSetException;
import org.qcri.micromappers.nlp.exception.DirectoryNotWritableException;

/**
 * @author Kushal
 *
 */
public interface Configurator {

	public void initProperties(String configLoadFileName,
							   ConfigurationProperty[] configurationProperties)
			throws ConfigurationPropertyNotSetException,
			ConfigurationPropertyNotRecognizedException,
			ConfigurationPropertyFileException;

	public String getProperty(ConfigurationProperty property);
	public String getProperty(String propertyName);
	public void setProperty(String property, String newValue);
	public void directoryIsWritable(String propertyName) throws DirectoryNotWritableException;
}
