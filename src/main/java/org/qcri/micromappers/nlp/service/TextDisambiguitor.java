package org.qcri.micromappers.nlp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.util.DataTypeUtil;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.accum.distances.CosineSimilarity;
import org.nd4j.linalg.factory.Nd4j;
import org.qcri.micromappers.nlp.model.TextDisambiguitorOutput;
import org.qcri.micromappers.nlp.utility.Twokenize;
import org.qcri.micromappers.nlp.utility.configurator.MicromappersConfigurationProperty;
import org.qcri.micromappers.nlp.utility.configurator.MicromappersConfigurator;

/**
 * @author Kushal
 *
 */
public class TextDisambiguitor {
	private static Logger logger = Logger.getLogger(TextDisambiguitor.class);
    private static WordVectors model;
    private INDArray firstLabelCentroid;
    private INDArray secondLabelCentroid;
    private int ndim = 200;
    private String DISAMBIGUATE_WORD;
    private static MicromappersConfigurator configProperties = MicromappersConfigurator.getInstance();

    
    static{
    	String modelFile = Paths.get(configProperties.getProperty(MicromappersConfigurationProperty.WORD_VECTOR_MODEL_FILE_PATH)).toString();
    	try {
			model = WordVectorSerializer.loadTxtVectors(new File(modelFile));
		} catch (IOException e) {
			logger.error("Exception in WORD_VECTOR_MODEL_FILE_PATH", e);
		}
    }

    
    public TextDisambiguitor(String topic, String firstLabelTags, String secondLabelTags) {
        try{
            DataTypeUtil.setDTypeForContext(DataBuffer.Type.FLOAT);
            DISAMBIGUATE_WORD = topic;
            firstLabelCentroid = get_centroid_vector(firstLabelTags.trim().split("\\s*,\\s*"), ndim);
            secondLabelCentroid = get_centroid_vector(secondLabelTags.trim().split("\\s*,\\s*"), ndim);
        }
        catch (Exception e){
        	logger.error("Exception whilie finding the centroid ", e);
        }
    }

    
    private  INDArray get_centroid_vector(String[] list_of_words, int ndim) {
        INDArray vector = Nd4j.zeros(1, ndim);

        for(String word : list_of_words) {
        	try{
        		word = word.replaceAll("[^A-Za-z0-9]","");
                vector = vector.addi(model.getWordVectorMatrix(word));
        	}catch (Exception e) {
				logger.error("Exception while processing the label tags", e);
			}
        	
        }
        vector =  vector.divi(list_of_words.length);
        return vector;
    }
    
    
    public TextDisambiguitorOutput processText(String text){
        TextDisambiguitorOutput output = null;
        String originalText = text;
        text = text.toLowerCase();
        try{
            Twokenize twokenize = new Twokenize();

            if(text.contains(DISAMBIGUATE_WORD)){
                List<String> token_list = twokenize.tokenizeRawTweetText(text);
                output = this.findAvgSimilarity(originalText, token_list);
            }else{
            	output = new TextDisambiguitorOutput(0.0, 0.0);
            }
        }catch (Exception e) {
			logger.error("Exception while processing the text", e);
		}
        finally {
			return output;
		}
    }

    private TextDisambiguitorOutput findAvgSimilarity(String item, List<String> token_list){
        List<String> context_words = this.get_context_words(token_list);
        double avg_first_label_centroid = getavgsimilarity_centroid(context_words.toArray(), firstLabelCentroid);
        double avg_second_label_centroid = getavgsimilarity_centroid(context_words.toArray(), secondLabelCentroid);

        return new TextDisambiguitorOutput(avg_first_label_centroid, avg_second_label_centroid);
    }


    private double getavgsimilarity_centroid(Object[] context_words, INDArray centroid){
        double sim = 0.0;
        int total_pass = 0;
        for(Object o : context_words){
            String word = o.toString();
            for(int i = 0; i < word.length(); i++){
                String word_to_search = word.substring(0, word.length() - i);
                try{
                    INDArray vector = model.getWordVectorMatrix(word_to_search);
                    double ret2 =  Nd4j.getExecutioner().execAndReturn(new CosineSimilarity(vector, centroid)).getFinalResult().doubleValue();
                    double simx =  ret2  ;

                    sim = sim + simx;
                    total_pass = total_pass + 1 ;
                }
                catch (Exception e){
                	logger.error("Exception while finding avg similarity centroid",e);
                }
            }
        }
        return sim/(total_pass+0.00001);
    }


    private List<String> get_context_words(List<String> token_list){
        List<String> context_words = new ArrayList<String>();

        context_words = token_list.stream()
        		.filter(item -> !item.equalsIgnoreCase(DISAMBIGUATE_WORD))
        		.collect(Collectors.toList());

        return context_words;
    }
}
