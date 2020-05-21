package pl.seb.czech.ilegal.front.client.act;

import org.springframework.stereotype.Service;
import pl.seb.czech.ilegal.front.domain.act.Act;

@Service
public class ActFilenameGenerator {
    private final String unifiedTextSuffix = "Lj";
    private final String extension = ".pdf";

    public String generateUnifiedTxtFilename(Act actToGenerate) {
        String id = actToGenerate.getApiId();
        char firstLetter = id.charAt(1);
        String year = id.substring(3, 7);
        String position = id.substring(id.length() - 4);
        return firstLetter + year + position + unifiedTextSuffix + extension;
    }

    public String generatePublishedTxtFileName(Act actToGenerate) {
        return generateUnifiedTxtFilename(actToGenerate).replaceAll(unifiedTextSuffix, "");
    }
}
