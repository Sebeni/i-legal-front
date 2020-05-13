package pl.seb.czech.ilegal.front.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public abstract class InitDataProvider<E> {
    private Set<E> convertedElements = new HashSet<>();
    private static final Logger LOGGER = LoggerFactory.getLogger("InitDataProvider");
    private static final String PATH_TO_EXAMPLES = "jsonExamples/";


    public InitDataProvider(ObjectMapper objectMapper, Class<E> elementType, String rootFolder, String... fileNames) {
        for (String jsonFileName : fileNames) {
            URL fileURL = this.getClass().getClassLoader().getResource( PATH_TO_EXAMPLES + rootFolder + "/" + jsonFileName + ".json");
            if(fileURL != null){
                try {
                    convertedElements.add(objectMapper.readValue(fileURL, elementType));
                } catch (IOException e) {
                    LOGGER.error("Exception in Data provider " + elementType.toString(), e);
                }
            }
        }
    }

    public Set<E> getConvertedElements() {
        return convertedElements;
    }
}
