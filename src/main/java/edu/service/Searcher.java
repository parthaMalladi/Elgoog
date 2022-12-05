package edu.uwb.css143.service;

import java.util.List;
import java.util.Map;

/*
DO NOT CHANGE
 */
public interface Searcher {
    List<Integer> search(String keyPhrase, Map<String, List<List<Integer>>> indexes);

    List<Integer> search(String keyPhrase, List<String> docs);
}