package org.qcri.micromappers.nlp.utility.configurator;

/**
 * @author Kushal
 * 
 * Enum containing all the property keys required by the Micromappers.
 *
 */

public enum MicromappersConfigurationProperty implements ConfigurationProperty {
	WORD_VECTOR_MODEL_FILE_PATH("WORD_VECTOR_MODEL_FILE_PATH");
	private final String configurationProperty;

	private MicromappersConfigurationProperty(String property) {
		configurationProperty = property;
	}

	@Override
	public String getName() {
		return this.configurationProperty;
	}

}
