import java.util.*;

/*
DO NOT CHANGE
 */

public class Util {
    public static Map<String, List<List<Integer>>> getTestIndex(int numOfDocs) {
        Map<String, List<List<Integer>>> index = new HashMap<String, List<List<Integer>>>() {{
            //note: A fill-in section is following to add to the right amount of lists in each index value
            put("world", new ArrayList<>(
                    Arrays.asList(
                            // "world" appears in doc 0 at location 0,1,3
                            new ArrayList<>(Arrays.asList(0, 1, 3)),
                            // "world" appears in doc 1 at location 0,1
                            new ArrayList<>(Arrays.asList(0, 1)),
                            new ArrayList<>(Arrays.asList(0, 4)),
                            new ArrayList<>(Arrays.asList(2)),
                            new ArrayList<>(),
                            new ArrayList<>(),  // TODO: for instructor only. Refactor out these ugly empty list
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(0))
                    ))
            );
            put("hello", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(Arrays.asList(2)),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(2)),
                            new ArrayList<>(Arrays.asList(1))
                    ))
            );
            put("hallo", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(2))
                    ))
            );
            put("seattle", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(1))
                    ))
            );
            put("abc", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(3)),
                            new ArrayList<>(Arrays.asList(0))
                    ))
            );
            put("fun", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(3)),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(0, 2)),
                            new ArrayList<>(Arrays.asList(2))
                    ))
            );
            put("sunny", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(1))
                    ))
            );
            put("day", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(5)),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(1)),
                            new ArrayList<>(Arrays.asList(0))
                    ))
            );
            put("better", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(1))
                    ))
            );
            put("x1", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(Arrays.asList(0)),
                            new ArrayList<>(Arrays.asList(0))
                    ))
            );
            put("x2", new ArrayList<>(
                    Arrays.asList(
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(),
                            new ArrayList<>(Arrays.asList(0))
                    ))
            );
        }};

        // filling up to the size of documents
        for (Map.Entry<String, List<List<Integer>>> entry : index.entrySet()) {

            List<List<Integer>> indexValue = entry.getValue();

            int numOfListToAdd = numOfDocs - indexValue.size();
            for (int i = 0; i < numOfListToAdd; i++) {
                indexValue.add(new ArrayList<>());
            }
        }

        return index;
    }

    public static List<String> getDocumentsForIntTest() {
        return new ArrayList<>(
                Arrays.asList(
                        "say hello world like you mean hello world not just world",
                        "hello world",
                        "hello",
                        "world",
                        "world world hello",
                        "world seattle rains hello abc world",
                        "sunday hello world fun",
                        " the new macbook is  awesome  "
                )
        );
    }

    public static List<Integer> emptyResult() {
        return new ArrayList<>();
    }

    public static List<String> getLargeTestInput() {
        return new ArrayList<>(Arrays.asList(
                "world world hello world",
                "world world hallo",
                "world seattle hello abc world day",
                "abc hello world fun",
                "sunny    better",
                "     fun day fun",
                "     day sunny fun   ",
                "x1",
                "x2",
                "x1",
                "x1",
                "x1",
                "x1",
                "x1",
                "x1",
                "x1",
                "world"));
    }
}
