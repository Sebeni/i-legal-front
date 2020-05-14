package pl.seb.czech.ilegal.front.domain.judgement.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import pl.seb.czech.ilegal.front.domain.judgement.JudgmentDetails;
import pl.seb.czech.ilegal.front.domain.judgement.ReferencedRegulation;

import java.io.IOException;

public class JudgmentDetailsDeserializer extends JsonDeserializer<JudgmentDetails> {

    @Override
    public JudgmentDetails deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);
        JsonNode root = node.get("data");
        
        Long id = root.get("id").asLong();
        
        String textContent = root.get("textContent").asText();
        
        JsonNode refRegulationNode = root.get("referencedRegulations");
        ReferencedRegulation[] referencedRegulations = codec.treeToValue(refRegulationNode, ReferencedRegulation[].class);
        
        JsonNode legalBasesNode = root.get("legalBases");
        String[] legalBases = codec.treeToValue(legalBasesNode, String[].class);

        JsonNode keywordsNode = root.get("keywords");
        String[] keyWords = codec.treeToValue(keywordsNode, String[].class);
        
        return new JudgmentDetails(id, textContent, referencedRegulations, legalBases, keyWords);
    }
}
