package pl.seb.czech.ilegal.front.client.act;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pl.seb.czech.ilegal.front.domain.act.Act;
import pl.seb.czech.ilegal.front.domain.act.ActSearchQuery;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class IsapURIGenerator {
    private ActFilenameGenerator generator;
    
    final static String ISAP_API_URL = "http://isap.sejm.gov.pl/api/isap";

    @Autowired
    public IsapURIGenerator(ActFilenameGenerator generator) {
        this.generator = generator;
    }


    public URI generateDownloadActURI(Act actToDownload, ActTextType textType) {
        String downloadEndpointURL = ISAP_API_URL + "/deeds/{publisher}/{year}/{position}/text/{type}/{fileName}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("publisher", actToDownload.getPublisher());
        params.put("year", actToDownload.getYear().toString());
        params.put("position", actToDownload.getPosition().toString());
        params.put("type", textType == ActTextType.UNIFIED ? "U" : "O");
        String fileName = textType == ActTextType.UNIFIED ?
                generator.generateUnifiedTxtFilename(actToDownload)
                : generator.generatePublishedTxtFileName(actToDownload);
        params.put("fileName", fileName);

        return UriComponentsBuilder.fromUriString(downloadEndpointURL).buildAndExpand(params).toUri();
    }

    public URI generateSearchActQueryUri(ActSearchQuery query) {
        String searchEndpointURL = "/search";
        return UriComponentsBuilder.fromHttpUrl(ISAP_API_URL + searchEndpointURL).queryParams(query.getQueryParams()).build().toUri();
    }
}