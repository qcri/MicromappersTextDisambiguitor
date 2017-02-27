package org.qcri.micromappers.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Kushal
 */

@EnableScheduling
@EnableAsync
@SpringBootApplication
public class MicroMappersTextDisambiguityApplication {
	
    public static void main(String[] args) {
    	SpringApplication.run(MicroMappersTextDisambiguityApplication.class, args);
    }
}