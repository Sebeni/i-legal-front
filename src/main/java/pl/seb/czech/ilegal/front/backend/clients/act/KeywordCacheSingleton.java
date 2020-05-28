package pl.seb.czech.ilegal.front.backend.clients.act;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class KeywordCacheSingleton {
    private static KeywordCacheSingleton keywordCacheInstance;
    @Getter
    private List<String> actProperNames = new ArrayList<>();
    @Getter
    private List<String> actKeywords = new ArrayList<>();
    
    public static KeywordCacheSingleton getInstance(ActDbClient client){
        if(keywordCacheInstance == null) {
            synchronized (KeywordCacheSingleton.class) {
                if(keywordCacheInstance == null) {
                    keywordCacheInstance = new KeywordCacheSingleton(client);
                }
            }
        }
        return keywordCacheInstance;
    }

    private KeywordCacheSingleton(ActDbClient client) {
        String[] allKeywordsAndName = client.getAllKeywordsAndNames();
        for(String s : allKeywordsAndName){
            if(Character.isUpperCase(s.charAt(0))) {
                actProperNames.add(s);
            } else {
                actKeywords.add(s);
            }
        }
    }
}
