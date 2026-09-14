import java.util.*;
import java.util.stream.*;

/**
 * JAVA STREAMS CHEAT SHEET & COMPLETE HANDBOOK
 * -------------------------------------------------------------------------
 * WHAT IS A STREAM ?
 * - A Stream is a sequence of elements supporting sequential and parallel 
 *   aggregate operations.
 * - It is NOT a data structure. It does not store elements permanently.
 * - It acts strictly as a continuous processing pipeline that reads data 
 *   from a source, transforms it, and produces a final result.
 * 
 * PIPELINE FLOW ARCHITECTURE:
 *   [ Collection / Source ]
 *            ↓
 *       .stream()          --> Source Stage
 *            ↓
 *       .filter(...)       --> Intermediate Operation (Lazy)
 *            ↓
 *         .map(...)        --> Intermediate Operation (Lazy)
 *            ↓
 *       .sorted(...)       --> Intermediate Operation (Lazy)
 *            ↓
 *      .collect(...)       --> Terminal Operation (Triggers Processing)
 * -------------------------------------------------------------------------
 */
public class stream_Api {

    public static void main(String[] args) {

        // =========================================================================
        // CREATING STREAMS
        // Every common and simple way to instantiate a stream pipeline.
        // =========================================================================

        // From Collections (Lists, Sets)
        // Time Complexity: O(1) to initialize stream wrapper
        // Space Complexity: O(1) extra space
        List<String> frameworkList = List.of("Spring", "React", "Angular", "Express");
        Stream<String> streamFromList = frameworkList.stream();

        Set<Integer> numberSet = Set.of(10, 20, 30);
        Stream<Integer> streamFromSet = numberSet.stream();

        // From Object Arrays
        // Time Complexity: O(1) | Space Complexity: O(1)
        String[] nameArray = {"Alice", "Bob", "Charlie"};
        Stream<String> streamFromArray = Arrays.stream(nameArray);

        // From Primitive Arrays (Avoids boxing overhead)
        // Time Complexity: O(1) | Space Complexity: O(1)
        int[] rawInts = {1, 2, 3, 4, 5};
        IntStream primitiveIntStream = Arrays.stream(rawInts);

        // Direct Stream.of() values
        // Time Complexity: O(N) | Space Complexity: O(N) internal array creation
        // where N = number of elements
        Stream<Integer> directValueStream = Stream.of(100, 200, 300);

        // Ranges (Primitive sequences)
        // Time Complexity: O(1) | Space Complexity: O(1)
        IntStream exclusiveRange = IntStream.range(1, 5);       // Generates: 1, 2, 3, 4

        IntStream inclusiveRange = IntStream.rangeClosed(1, 5); // Generates: 1, 2, 3, 4, 5


        System.out.println("--- 1. FILTER ---");
        // =========================================================================
        // filter()
        // Keeps elements that satisfy a condition (boolean predicate) and discards the rest.
        // Time Complexity:  O(N) - visits each element once.
        // Space Complexity: O(1) - evaluates elements lazily in-flight .
        // =========================================================================
        List<Integer> numbers = List.of(10, 15, 20, 25, 30);

        numbers.stream()
               .filter(n -> n % 2 == 0) // Keeps only even values
               .forEach(n -> System.out.print(n + " ")); 
        System.out.println("\n");


        System.out.println("--- 2. MAP ---");
        // =========================================================================
        // map()
        // Transforms every element into another value or type using a function.
        // Time Complexity:  O(N) - applies transformation function to N items.
        // Space Complexity: O(1) - transformed in-place as elements flow through.
        // =========================================================================
        List<String> words = List.of("java", "stream", "pipeline");

        words.stream()
             .map(String::toUpperCase) // Converts "java" -> "JAVA"
             .forEach(w -> System.out.print(w + " ")); 
        System.out.println("\n");


        System.out.println("--- 3. FLATMAP ---");
        // =========================================================================
        // flatMap()
        // Flattens nested structures (e.g., List of Lists) into a single flat stream.
        // Time Complexity:  O(N * M) - where N is outer elements and M is inner items.
        // Space Complexity: O(M) - temporary sub-stream creation overhead.
        // =========================================================================
        List<List<String>> nestedList = List.of(
            List.of("A", "B"),
            List.of("C", "D")
        );

        nestedList.stream()
                  .flatMap(Collection::stream) // Unpacks lists -> "A", "B", "C", "D"
                  .forEach(ch -> System.out.print(ch + " ")); 
        System.out.println("\n");


        System.out.println("--- 4. SORTED ---");
        // =========================================================================
        // sorted()
        // Sorts elements in natural ascending order or using a custom Comparator.
        // Time Complexity:  O(N log N) - requires collecting data into an internal buffer.
        // Space Complexity: O(N) - holds elements in memory to complete sorting.
        // =========================================================================
        List<Integer> unsorted = List.of(5, 1, 4, 2, 3);

        unsorted.stream()
                .sorted() // Natural ascending order
                .forEach(n -> System.out.print(n + " ")); 
        System.out.println("\n");


        System.out.println("--- 5. DISTINCT ---");
        // =========================================================================
        // distinct()
        // Removes duplicate elements using equals() and hashCode().
        // Time Complexity:  O(N) - checks elements against internal history.
        // Space Complexity: O(N) - maintains an internal HashSet for tracking.
        // =========================================================================
        List<Integer> duplicates = List.of(1, 2, 2, 3, 3, 3, 4);

        duplicates.stream()
                  .distinct() // Keeps only unique elements
                  .forEach(n -> System.out.print(n + " ")); 
        System.out.println("\n");


        System.out.println("--- 6. LIMIT ---");
        // =========================================================================
        // limit()
        // Truncates the stream to not exceed a specified maximum size.
        // Time Complexity:  O(K) - stops processing after K elements.
        // Space Complexity: O(1) - no extra memory needed.
        // =========================================================================
        Stream.of(100, 200, 300, 400, 500)
              .limit(3) // Takes only the first 3 items
              .forEach(n -> System.out.print(n + " ")); 
        System.out.println("\n");


        System.out.println("--- 7. SKIP ---");
        // =========================================================================
        // skip()
        // Discards the first N elements of the stream and processes the rest.
        // Time Complexity:  O(N) - evaluates and discards N items.
        // Space Complexity: O(1) - no extra storage required.
        // =========================================================================
        Stream.of(100, 200, 300, 400, 500)
              .skip(2) // Ignores 100 and 200
              .forEach(n -> System.out.print(n + " ")); 
        System.out.println("\n");


        System.out.println("--- 8. FOREACH ---");
        // =========================================================================
        // forEach()
        // Performs an action on each stream element (Terminal Operation).
        // Time Complexity:  O(N) - iterates through all processed items.
        // Space Complexity: O(1) - state-free execution.
        // =========================================================================
        List.of("Apple", "Banana").stream()
            .forEach(fruit -> System.out.println("Fruit: " + fruit));


        System.out.println("\n--- 9. COLLECT ---");
        // =========================================================================
        // collect()
        // Gathers processed stream elements into a target collection (e.g., List, Set).
        // Time Complexity:  O(N) - accumulates N elements.
        // Space Complexity: O(N) - creates new output data structure.
        // =========================================================================
        List<String> collectedList = Stream.of("x", "y", "z")
                                           .map(String::toUpperCase)
                                           .filter( x-> x.charAt(0) >'e')
                                           .collect(Collectors.toList());
        System.out.println("Collected Result: " + collectedList);


        System.out.println("\n--- 10. REDUCE ---");
        // =========================================================================
        // reduce()
        // Combines stream elements into a single accumulated result value.
        // Time Complexity:  O(N) - aggregates elements sequentially.
        // Space Complexity: O(1) - tracks identity and running result state only.
        // =========================================================================
        int sum = Stream.of(1, 2, 3, 4)
                        .reduce(0, (accumulator, element) -> accumulator + element);
        System.out.println("Calculated Sum: " + sum);


        System.out.println("\n--- 11. COUNT, MIN, MAX ---");
        // =========================================================================
        // count() | min() | max()
        // Simple aggregate operations yielding summary metrics.
        // Time Complexity:  O(N) - scans the entire dataset once.
        // Space Complexity: O(1) - stores single comparison state.
        // =========================================================================
        List<Integer> sampleScores = List.of(45, 88, 92, 60, 73);

        long totalCount = sampleScores.stream().count();
        Optional<Integer> minScore = sampleScores.stream().min(Integer::compareTo);
        Optional<Integer> maxScore = sampleScores.stream().max(Integer::compareTo);

        System.out.println("Total Count: " + totalCount);
        System.out.println("Minimum Score: " + minScore.orElse(0));
        System.out.println("Maximum Score: " + maxScore.orElse(0));


        System.out.println("\n--- 12. MATCHING OPERATIONS ---");
        // =========================================================================
        // anyMatch() | allMatch() | noneMatch()
        // Short-circuiting boolean checks for stream conditions.
        // Time Complexity:  O(N) worst-case, O(1) best-case (short-circuits early).
        // Space Complexity: O(1) - zero allocations.
        // =========================================================================
        List<String> techStack = List.of("Java", "Spring", "Docker", "AWS");

        boolean hasDocker = techStack.stream().anyMatch(s -> s.equalsIgnoreCase("Docker"));
        boolean allLongWords = techStack.stream().allMatch(s -> s.length() >= 3);
        boolean noneEmptyStr = techStack.stream().noneMatch(String::isEmpty);

        System.out.println("Contains Docker? " + hasDocker);
        System.out.println("All lengths >= 3? " + allLongWords);
        System.out.println("None are empty? " + noneEmptyStr);


        System.out.println("\n--- 13. ADVANCED COLLECTORS ---");
        // =========================================================================
        // Collectors | groupingBy() | partitioningBy()
        // Utility methods for advanced grouping, key-value mapping, and splitting.
        // Time Complexity:  O(N) - buckets elements into hash entries/lists.
        // Space Complexity: O(N) - builds Map instances containing all elements.
        // =========================================================================
        List<String> cities = List.of("London", "Lisbon", "Paris", "Prague", "Tokyo");

        // groupingBy(): Groups elements based on a key extraction function
        Map<Integer, List<String>> groupedByLength = cities.stream()
            .collect(Collectors.groupingBy(String::length));

        // partitioningBy(): Splits elements into exactly two groups (True / False)
        Map<Boolean, List<String>> partitionedByLetterP = cities.stream()
            .collect(Collectors.partitioningBy(city -> city.startsWith("P")));

        System.out.println("Grouped by Name Length: " + groupedByLength);
        System.out.println("Partitioned by starts with 'P': " + partitionedByLetterP);
    }
}
