package edu.uwb.css143.service;

import java.util.List;
import java.util.Map;

/*
DO NOT CHANGE
 */

public interface Indexer {
    Map<String, List<List<Integer>>> createIndex(List<String> docs);
}
