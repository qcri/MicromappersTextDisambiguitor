package org.qcri.micromappers.nlp.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.qcri.micromappers.nlp.entity.CollectionLabel;
import org.qcri.micromappers.nlp.entity.TextDisambiguityAnalysis;
import org.qcri.micromappers.nlp.exception.MicromappersServiceException;
import org.qcri.micromappers.nlp.model.TextDisambiguitorOutput;
import org.qcri.micromappers.nlp.repository.TextDisambiguityRepository;
import org.qcri.micromappers.nlp.utility.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kushal
 *
 */
@Service
public class TextDisambiguityService {
	private static Logger logger = Logger.getLogger(TextDisambiguityService.class);

	@Autowired
	TextDisambiguityRepository textDisambiguityRepository;
	@Autowired
	CollectionLabelService collectionLabelService;

	public void processText() {
		List<TextDisambiguityAnalysis> textDisambiguityList = null;
		Long collectionId = null;
		CollectionLabel collectionLabel = null;
		TextDisambiguitor textDisambiguitor = null;

		do{
			textDisambiguityList = findTop500ByStatusOrderById(Status.ONGOING);

			if(CollectionUtils.isNotEmpty(textDisambiguityList)){
				
				logger.info("Processing "+textDisambiguityList.size() + " items");
				for (TextDisambiguityAnalysis item : textDisambiguityList) {
					double firstLabelScore = 0.0;
					double secondLabelScore = 0.0;
					try {
						if(item != null){
							String tweetText = item.getFeedText();
							if(StringUtils.isNotBlank(tweetText)){

								if(collectionId == null || collectionLabel == null || textDisambiguitor == null || collectionId != item.getCollectionId()){
									collectionId = item.getCollectionId();
									collectionLabel = collectionLabelService.findByCollectionId(collectionId);
									if(collectionLabel != null){
										textDisambiguitor = new TextDisambiguitor(collectionLabel.getTopic(), collectionLabel.getFirstLabelTags(), collectionLabel.getSecondLabelTags());
									}
								}
								if(textDisambiguitor != null){
									TextDisambiguitorOutput output = textDisambiguitor.processText(tweetText);
									if(output != null){
										firstLabelScore = output.getFirstLabelScore();
										secondLabelScore = output.getSecondLabelScore();
									}
								}
							}
							item.setFirstLabelScore(firstLabelScore);
							item.setSecondLabelScore(secondLabelScore);
							item.setStatus(Status.COMPLETED);
							saveOrUpdate(item);
						}
					} catch (Exception e){
						logger.error("Exception while processing TextDisambiguityAnalysis " + e);
					}
				}
			}
		}while(textDisambiguityList != null && textDisambiguityList.size() >=500);

	}

	
	public TextDisambiguityAnalysis saveOrUpdate(TextDisambiguityAnalysis textDisambiguityAnalysis){
		try{
			return textDisambiguityRepository.save(textDisambiguityAnalysis);
		}catch (Exception e) {
			logger.error("Error while create or update TextDisambiguityAnalysis", e);
			throw new MicromappersServiceException("Exception while create or update a TextDisambiguityAnalysis", e);
		}
	}
	

	public List<TextDisambiguityAnalysis> findTop500ByStatusOrderById(Status status){
		try{
			return textDisambiguityRepository.findTop500ByStatusOrderById(status);
		}catch (Exception e) {
			logger.error("Error while finding TextDisambiguityAnalysis by status", e);
			return null;
		}
	}
}
