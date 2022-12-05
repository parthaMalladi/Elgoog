import edu.uwb.css143.service.Indexer;
import edu.uwb.css143.service.IndexerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/*
DO NOT CHANGE
 */

public class IndexerTest {

    private Indexer indexer;

    @Before
    public void setUp() {
        indexer = new IndexerImpl();
    }

    @Test
    public void testIndexer() {
        List<TestCase> testCases = getTestCase();
        for (TestCase testCase : testCases) {
            Map<String, List<List<Integer>>> actual = indexer.createIndex(testCase.input);
            // uncomment this to see the index
            //System.out.println(actual);
            assertEquals(testCase.expect, actual);
        }
    }

    private List<TestCase> getTestCase() {
        List<String> largeTestCase = Util.getLargeTestInput();

        List<TestCase> testCases = new ArrayList<>(Arrays.asList(
                new TestCase(
                        largeTestCase,
                        Util.getTestIndex(largeTestCase.size())
                ),
                new TestCase(
                        new ArrayList<>(Arrays.asList(
                                ""
                        )),
                        new HashMap<>()
                ),
                new TestCase(
                        new ArrayList<>(Arrays.asList(
                                "",
                                ""
                        )),
                        new HashMap<>()
                )
        ));
        return testCases;
    }


    private class TestCase {
        private List<String> input;
        private Map<String, List<List<Integer>>> expect;

        public TestCase(List<String> input, Map<String, List<List<Integer>>> output) {
            this.input = input;
            this.expect = output;
        }
    }
}
