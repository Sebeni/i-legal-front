package pl.seb.czech.ilegal.front.client.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.front.client.URIGenerator;
import pl.seb.czech.ilegal.front.domain.SearchQuery;
import pl.seb.czech.ilegal.front.domain.act.Act;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class IsapURIGenerator extends URIGenerator {
    private ActFilenameGenerator generator;
    private final String searchEndpointUrl = "/search";
    
    @Autowired
    public IsapURIGenerator(ActFilenameGenerator generator) {
        super("http://isap.sejm.gov.pl/api/isap");
        this.generator = generator;
    }
    
    public URI generateDownloadActURI(Act actToDownload, ActTextType textType) {
        String downloadEndpointURL = apiUrl + "/deeds/{publisher}/{year}/{position}/text/{type}/{fileName}";
        Map<String, String> params = new HashMap<>();
        params.put("publisher", actToDownload.getPublisher().name());
        params.put("year", actToDownload.getYear().toString());
        params.put("position", actToDownload.getPosition().toString());
        params.put("type", textType == ActTextType.UNIFIED ? "U" : "O");
        String fileName = textType == ActTextType.UNIFIED ?
                generator.generateUnifiedTxtFilename(actToDownload)
                : generator.generatePublishedTxtFileName(actToDownload);
        params.put("fileName", fileName);

        return UriComponentsBuilder.fromUriString(downloadEndpointURL).buildAndExpand(params).toUri();
    }

 
    public URI generateSearchQueryUri(SearchQuery query) {
        return super.generateSearchQueryUri(query, searchEndpointUrl);
    }
}