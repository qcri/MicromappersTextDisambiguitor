package org.qcri.micromappers.nlp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kushal
 *
 */
@Entity
@Table(name="collection_label")
public class CollectionLabel extends ExtendedBaseEntity
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1569106115295713636L;

	@Column(name = "collection_id", nullable=false, unique=true)
	private Long collectionId;
	
	@Column(name="topic", length = 100, nullable=false)
	private String topic;
	
	@Column(name="first_label", length = 100, nullable=false)
	private String firstLabel;
	
	@Column(name="second_label", length = 100, nullable=false)
	private String secondLabel;
	
	@Column(name="first_label_tags", length = 1000, nullable=false)
	private String firstLabelTags;
	
	@Column(name="second_label_tags", length = 1000, nullable=false)
	private String secondLabelTags;

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the firstLabel
	 */
	public String getFirstLabel() {
		return firstLabel;
	}

	/**
	 * @param firstLabel the firstLabel to set
	 */
	public void setFirstLabel(String firstLabel) {
		this.firstLabel = firstLabel;
	}

	/**
	 * @return the secondLabel
	 */
	public String getSecondLabel() {
		return secondLabel;
	}

	/**
	 * @param secondLabel the secondLabel to set
	 */
	public void setSecondLabel(String secondLabel) {
		this.secondLabel = secondLabel;
	}

	/**
	 * @return the firstLabelTags
	 */
	public String getFirstLabelTags() {
		return firstLabelTags;
	}

	/**
	 * @param firstLabelTags the firstLabelTags to set
	 */
	public void setFirstLabelTags(String firstLabelTags) {
		this.firstLabelTags = firstLabelTags;
	}

	/**
	 * @return the secondLabelTags
	 */
	public String getSecondLabelTags() {
		return secondLabelTags;
	}

	/**
	 * @param secondLabelTags the secondLabelTags to set
	 */
	public void setSecondLabelTags(String secondLabelTags) {
		this.secondLabelTags = secondLabelTags;
	}

	/**
	 * @return the collectionId
	 */
	public Long getCollectionId() {
		return collectionId;
	}

	/**
	 * @param collectionId the collectionId to set
	 */
	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}
}
