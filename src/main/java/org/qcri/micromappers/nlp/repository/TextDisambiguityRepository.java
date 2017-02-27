package org.qcri.micromappers.nlp.repository;

import java.util.List;

import org.qcri.micromappers.nlp.entity.TextDisambiguityAnalysis;
import org.qcri.micromappers.nlp.utility.Status;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kushal
 *
 */
public abstract interface TextDisambiguityRepository extends CrudRepository<TextDisambiguityAnalysis, Long> {
    TextDisambiguityAnalysis findByFeedId(String feedId);
    List<TextDisambiguityAnalysis> findTop500ByStatusOrderById(Status status);
}
