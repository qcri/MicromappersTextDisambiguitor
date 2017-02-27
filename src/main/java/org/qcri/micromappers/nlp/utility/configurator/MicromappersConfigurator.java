package org.qcri.micromappers.nlp.utility.configurator;

import org.qcri.micromappers.nlp.exception.ConfigurationPropertyFileException;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyNotRecognizedException;
import org.qcri.micromappers.nlp.exception.ConfigurationPropertyNotSetException;

/**
 * @author Kushal
 *
 */
public class MicromappersConfigurator extends BaseConfigurator {
	
	public static final String configLoadFileName = "config.properties";

	private static final MicromappersConfigurator instance = new MicromappersConfigurator();

	private MicromappersConfigurator() {
		this.initProperties(MicromappersConfigurator.configLoadFileName, MicromappersConfigurationProperty.values());
		this.directoryIsWritable(MicromappersConfigurationProperty.WORD_VECTOR_MODEL_FILE_PATH.getName());
	}

	public static MicromappersConfigurator getInstance()
			throws ConfigurationPropertyNotSetException,
			ConfigurationPropertyNotRecognizedException,
			ConfigurationPropertyFileException {
		return instance;
	}

}
