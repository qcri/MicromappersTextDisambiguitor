package org.qcri.micromappers.nlp.model;

/**
 * @author Kushal
 *
 */
public class TextDisambiguitorOutput {

    private double firstLabelScore;
    private double secondLabelScore;

    public TextDisambiguitorOutput(double firstLabelScore, double secondLabelScore) {
        this.setFirstLabelScore(firstLabelScore);
        this.setSecondLabelScore(secondLabelScore);
    }

	/**
	 * @return the firstLabelScore
	 */
	public double getFirstLabelScore() {
		return firstLabelScore;
	}

	/**
	 * @param firstLabelScore the firstLabelScore to set
	 */
	public void setFirstLabelScore(double firstLabelScore) {
		this.firstLabelScore = firstLabelScore;
	}

	/**
	 * @return the secondLabelScore
	 */
	public double getSecondLabelScore() {
		return secondLabelScore;
	}

	/**
	 * @param secondLabelScore the secondLabelScore to set
	 */
	public void setSecondLabelScore(double secondLabelScore) {
		this.secondLabelScore = secondLabelScore;
	}

    
}
