import edu.uwb.css143.service.Searcher;
import edu.uwb.css143.service.SearcherImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
DO NOT CHANGE
 */

public class SearcherTest {
    private Searcher searcher;

    @Before
    public void setUp() {
        searcher = new SearcherImpl();
    }

    @Test
    public void testSearcher() {
        List<SearcherTest.TestCase> testCases = getTestCases();
        for (SearcherTest.TestCase testCase : testCases) {
            List<Integer> actual = searcher.search(testCase.target, testCase.indices);
            assertEquals(testCase.target, testCase.expect, actual);
        }
    }

    private List<TestCase> getTestCases() {

        int size = Util.getLargeTestInput().size();
        assertTrue(size > 0);

        Map<String, List<List<Integer>>> indices = Util.getTestIndex(size);
        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        indices,
                        "",
                        Util.emptyResult()
                ),
                new TestCase(
                        indices,
                        "ttle hello",
                        Util.emptyResult()
                ),
                new TestCase(
                        indices,
                        "hello",
                        new ArrayList<>(Arrays.asList(0, 2, 3))
                ),
                new TestCase(
                        indices,
                        "world",
                        new ArrayList<>(Arrays.asList(0, 1, 2, 3, 16))
                ),
                new TestCase(
                        indices,
                        "seattle hello",
                        new ArrayList<>(Arrays.asList(2))
                ),
                new TestCase(
                        indices,
                        "hello world",
                        new ArrayList<>(Arrays.asList(0, 3))
                ),
                new TestCase(
                        indices,
                        "hello world fun",
                        new ArrayList<>(Arrays.asList(3))
                ),
                new TestCase(
                        indices,
                        "world world fun",
                        Util.emptyResult()
                ),
                new TestCase(
                        indices,
                        "chicago",
                        Util.emptyResult()
                ),
                new TestCase(
                        indices,
                        "need coffee",
                        Util.emptyResult()
                ),
                new TestCase(
                        indices,
                        "need coffee",
                        Util.emptyResult()
                ),
                new TestCase(
                        indices,
                        "sunny",
                        new ArrayList<>(Arrays.asList(4, 6))
                ),
                new TestCase(
                        indices,
                        "x1",
                        new ArrayList<>(Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15))
                )
        ));

        return testCases;
    }

    private class TestCase {
        private Map<String, List<List<Integer>>> indices;
        private String target;
        private List<Integer> expect;

        public TestCase(Map<String, List<List<Integer>>> indices, String target, List<Integer> expect) {
            this.indices = indices;
            this.target = target;
            this.expect = expect;
        }
    }
}
