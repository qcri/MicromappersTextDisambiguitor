package org.qcri.micromappers.nlp.repository;

import org.qcri.micromappers.nlp.entity.CollectionLabel;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kushal
 *
 */
public abstract interface CollectionLabelRepository extends CrudRepository<CollectionLabel, Long> {
    CollectionLabel findByCollectionId(Long collectionId);
}
