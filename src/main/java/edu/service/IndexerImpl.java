package edu.uwb.css143.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    TODO (Optional): Team member names
 */
@Service
public class IndexerImpl implements Indexer {
    public Map<String, List<List<Integer>>> createIndex(List<String> docs) {
        Map<String, List<List<Integer>>> index = new HashMap<>(); // do not change
        /*
         TODO: add your code
         */
        return index; // do not change. variable "index" is the result that this function should return
    }
}
