package edu.uwb.css143.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearcherImpl implements Searcher {
    // a naive search
    // DO NOT CHANGE
    public List<Integer> search(String searchPhrase, List<String> docs) {
        List<Integer> result = new ArrayList<>();
        String[] searchWords = searchPhrase.trim().toLowerCase().split("\\s+");

        // search in each doc for consecutive matches of each word in the search phrase
        for (int i = 0; i < docs.size(); i++) {
            String doc = docs.get(i).trim();
            if (doc.isEmpty()) {
                continue;
            }
            String[] wordsInADoc = doc.split("\\s+");

            for (int j = 0; j < wordsInADoc.length; j++) {
                boolean matchFound = true;
                for (int k = 0; k < searchWords.length; k++) {
                    if (j + k >= wordsInADoc.length || !searchWords[k].equals(wordsInADoc[j + k])) {
                        matchFound = false;
                        break;
                    }
                }
                if (matchFound) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }


    public List<Integer> search(String searchPhrase, Map<String, List<List<Integer>>> index) {
        List<Integer> searchResult = new ArrayList<>(); 
        String[] searchWords = searchPhrase.trim().toLowerCase().split("\\s+");

        if (searchWords.length == 0 || !index.containsKey(searchWords[0])) {
            return searchResult;
        }

        int i = 0;
        List<List<Integer>> documents = index.get(searchWords[0]);
        for (List<Integer> indexes : documents) {
            for (int start : indexes) {
                boolean matchFound = true;
                for (int j = 0; j < searchWords.length; j++) {
                    String newSearchWord = searchWords[j];
                    if (!index.get(newSearchWord).get(i).contains(start+j)) {
                        matchFound = false;
                        break;
                    }
                }
                if (matchFound) {
                    searchResult.add(i);
                    break;
                }
            }
            i++;
        }

        return searchResult; 
    }
}
