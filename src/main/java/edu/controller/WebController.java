package edu.uwb.css143.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uwb.css143.model.Index;
import edu.uwb.css143.model.SearchKey;
import edu.uwb.css143.repository.IndexRepository;
import edu.uwb.css143.service.Indexer;
import edu.uwb.css143.service.Searcher;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
DO NOT CHANGE
 */

@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private IndexRepository indexRepository;

    @Autowired
    private Searcher searcher;

    @Autowired
    private Indexer indexer;

    @RequestMapping(value = {"/", "/search", "/home"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("searchKey", new SearchKey());
        model.addAttribute("hasResult", false);
        return "page";
    }

    @GetMapping("/docs")
    public String docs(Model model) throws JsonProcessingException {
        // get index from in-memory database #hacky
        Index indexInJson = indexRepository.findAll().get(0);
        List<String> docs = new ObjectMapper().readValue(indexInJson.getDocs(), List.class);
        model.addAttribute("docs", docs);

        Map<String, List<List<Integer>>> index = new ObjectMapper().readValue(indexInJson.getIndices(), Map.class);
        String idxStr = indexToString(index);

        // uncomment the following print line if you need to print index to console
        // System.out.println(idxStr);

        model.addAttribute("index", idxStr);
        return "docs";
    }

    private String indexToString(Map<String, List<List<Integer>>> index) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, List<List<Integer>>> entry : index.entrySet()) {
            buffer.append("\"" + entry.getKey() + "\" : {<br />");
            List<List<Integer>> idxInDoc = entry.getValue();
            for (int i = 0; i < idxInDoc.size(); i++) {
                buffer.append(String.format("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;document[%d]: %s", i, idxInDoc.get(i).toString()));
                buffer.append("<br />");
            }
            buffer.append("}<br />");
        }
        return buffer.toString();
    }

    @GetMapping("/reindex")
    public String reindex(Model model) {
        model.addAttribute("toIndex", new Index());
        return "reindex";
    }

    @PostMapping("/reindex")
    public String reindex(@ModelAttribute Index idx, Model model) throws JsonProcessingException {
        String[] docs = idx.getDocs().split("\n");
        for (int i = 0; i < docs.length; i++) {
            docs[i] = docs[i].trim().toLowerCase();
        }

        List<String> newDocs = Arrays.asList(docs);
        String jsonIdx = new ObjectMapper().writeValueAsString(indexer.createIndex(newDocs));
        String jsonDoc = new ObjectMapper().writeValueAsString(newDocs);

        indexRepository.deleteAll();
        indexRepository.save(new Index(jsonIdx, jsonDoc));
        return "redirect:/docs";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute SearchKey key, Model model) throws JsonProcessingException {
        // get index from in-memory storage
        Index indexInJson = indexRepository.findAll().get(0);
        Map<String, List<List<Integer>>> indices = new ObjectMapper().readValue(indexInJson.getIndices(), Map.class);
        List<String> docs = new ObjectMapper().readValue(indexInJson.getDocs(), List.class);

        // run "smart" search
        long startTime = System.nanoTime();

        List<Integer> result = searcher.search(key.getPhrase(), indices);

        long searchTime = System.nanoTime() - startTime;

        // run "naive" search
        startTime = System.nanoTime();

        List<Integer> naiveSearchResult = searcher.search(key.getPhrase(), docs);

        long naiveSearchTime = System.nanoTime() - startTime;

        // render result page
        model.addAttribute("docs", docs);
        model.addAttribute("phrase", "\"" + key.getPhrase() + "\"");
        model.addAttribute("searchResult", result.toString());
        model.addAttribute("naiveSearchResult", naiveSearchResult.toString());
        model.addAttribute("searchResultMatch", result.equals(naiveSearchResult));
        model.addAttribute("emptyResult", result.isEmpty());
        model.addAttribute("searchKey", new SearchKey());
        model.addAttribute("hasResult", true);
        model.addAttribute("searchTime", searchTime);
        model.addAttribute("naiveSearchTime", naiveSearchTime);
        return "page";
    }

    /*
    this is mainly used for submission test, but also cool to be able to curl test result
    examples:
        curl http://127.0.0.1:8081/v2/search/more
            {"searchResult":{"indexedSearchResult":[1,8,15,16],"resultMatch":true}}%
        curl http://127.0.0.1:8081/v2/search/that_allows
            {"searchResult":{"indexedSearchResult":[4],"resultMatch":true}}%
     */
    @GetMapping("/v2/search/{key}")
    @ResponseBody
    public ResponseEntity<String> search_v2(@PathVariable String key) throws JsonProcessingException {
        JSONObject obj = new JSONObject();

        String searchKey = key.trim().replace("_", " ");

        // get index from in-memory storage
        Index indexInJson = indexRepository.findAll().get(0);
        Map<String, List<List<Integer>>> indices = new ObjectMapper().readValue(indexInJson.getIndices(), Map.class);
        List<String> docs = new ObjectMapper().readValue(indexInJson.getDocs(), List.class);

        List<Integer> indexedSearchResult = searcher.search(searchKey, indices);
        List<Integer> naiveSearchResult = searcher.search(searchKey, docs);

        // send JSON result back
        obj.put("resultMatch", indexedSearchResult.equals(naiveSearchResult));
        obj.put("indexedSearchResult", indexedSearchResult);
        JSONObject response = new JSONObject();
        response.put("searchResult", obj);
        return ResponseEntity.status(HttpStatus.OK).body(response.toString());
    }
}