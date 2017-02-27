package org.qcri.micromappers.nlp.service;

import org.apache.log4j.Logger;
import org.qcri.micromappers.nlp.entity.CollectionLabel;
import org.qcri.micromappers.nlp.repository.CollectionLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Kushal
 *
 */
@Service
public class CollectionLabelService {
    private static Logger logger = Logger.getLogger(CollectionLabelService.class);

    @Autowired
    CollectionLabelRepository collectionLabelRepository;

    public CollectionLabel findByCollectionId(Long collectionId){
        return collectionLabelRepository.findByCollectionId(collectionId);
    }
}
