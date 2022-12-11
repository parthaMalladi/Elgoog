package edu.uwb.css143.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    TODO (Optional): Partha Malladi and Nikhil Mehta
 */
@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> createIndex(List<String> docs) {
        Map<String, List<List<Integer>>> index = new HashMap<>(); // do not change

        int i = 0;
        for (String doc : docs) {
            String parsedDoc = doc.trim();
            if (parsedDoc.isEmpty()) continue;
            String[] words = parsedDoc.split("\\s+");
            for (int j = 0; j < words.length; j++) {
                String word = words[j];
                if (!index.containsKey(word)) {
                    List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();
                    for (int k = 0; k < docs.size(); k++) {
                        listOfLists.add(new ArrayList<Integer>());
                    }
                    index.put(word, listOfLists);
                }
                index.get(word).get(i).add(j);
            }
            i++;
        }

        return index; // do not change. variable "index" is the result that this function should return
    }
}
