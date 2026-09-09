# Java Stream API — Notes & Handbook

 > A concise reference covering Java Stream API fundamentals through strong-intermediate concepts.

---

 ## 1\. What is Stream API?

 - Introduced in **Java 8**.
- Provides functional-style processing of data.
- Processes a sequence of elements through a pipeline.
- Does not permanently store data.
- Does not normally modify the source.
- Supports sequential and parallel processing.

 ### Definition

 > A Stream is a pipeline for processing data from a source through a sequence of operations.

 ### Basic Structure

```
Source
  ↓
Intermediate Operations
  ↓
Terminal Operation
  ↓
Result
```

---

 ## 2\. Why Use Streams?

 Use Streams to:

 - Filter data.
- Transform data.
- Sort elements.
- Remove duplicates.
- Search for elements.
- Perform aggregations.
- Group or partition data.
- Convert collections.
- Flatten nested structures.
- Build readable data-processing pipelines.

 ### Typical Use Case

```
Collection
    ↓
Filter
    ↓
Transform
    ↓
Sort
    ↓
Collect Result
```

---

 ## 3\. Stream Pipeline

 A Stream pipeline consists of three main parts:

 ### Source

 - Provides the data.
- Examples:
  - `List`
  - `Set`
  - Array
  - Map entries
  - Primitive ranges

```
list.stream();
```

 ### Intermediate Operations

 - Transform or filter the stream.
- Lazy in execution.
- Examples:
  - `filter()`
  - `map()`
  - `flatMap()`
  - `sorted()`
  - `distinct()`
  - `limit()`
  - `skip()`

 ### Terminal Operations

 - Produce the final result.
- Trigger stream execution.
- Examples:
  - `toList()`
  - `collect()`
  - `reduce()`
  - `count()`
  - `forEach()`
  - `findFirst()`
  - `anyMatch()`

---

 ## 4\. Important Stream Characteristics

 - Streams are **lazy**.
- Intermediate operations execute only when required.
- A terminal operation triggers processing.
- A Stream generally cannot be reused after termination.
- Streams do not normally modify their source.
- Operations can be chained.
- Streams support sequential and parallel execution.

---

 ## 5\. Creating Streams

 ### From Collection

```
list.stream();
```

 ### From Set

```
set.stream();
```

 ### From Object Array

```
Arrays.stream(array);
```

 ### From Primitive Array

```
Arrays.stream(intArray);
```

 Returns:

```
IntStream
```

 ### From Values

```
Stream.of(10, 20, 30);
```

 ### From Map

```
map.keySet().stream();
map.values().stream();
map.entrySet().stream();
```

 ### Primitive Ranges

```
IntStream.range(1, 5);
```

 Produces:

```
1 2 3 4
```

```
IntStream.rangeClosed(1, 5);
```

 Produces:

```
1 2 3 4 5
```

---

 ## 6\. filter()

 ### Definition

 > `filter()` keeps elements that satisfy a condition.

```
numbers.stream()
       .filter(n -> n % 2 == 0)
       .toList();
```

 ### Use When

 - Selecting matching elements.
- Removing unwanted elements.
- Applying conditions.

 ### Type

 - Intermediate operation.
- Lazy operation.

---

 ## 7\. map()

 ### Definition

 > `map()` transforms each element into another value.

```
names.stream()
     .map(String::toUpperCase)
     .toList();
```

 ### Use When

 - Transforming values.
- Extracting object properties.
- Converting one type to another.

 ### Example

```
employees.stream()
         .map(Employee::getName)
         .toList();
```

 ### Type

 - Intermediate operation.
- Usually maintains the number of elements.

---

 ## 8\. flatMap()

 ### Definition

 > `flatMap()` transforms elements and flattens nested results into one stream.

```
nestedList.stream()
          .flatMap(Collection::stream)
          .toList();
```

 ### Use When

 - Working with nested collections.
- Converting `Stream<List<T>>` into `Stream<T>`.
- Flattening multiple values from each element.

 ### Key Difference

```
map()
→ Transform

flatMap()
→ Transform + Flatten
```

---

 ## 9\. sorted()

 ### Definition

 > `sorted()` orders stream elements using natural ordering or a Comparator.

 ### Natural Ordering

```
numbers.stream()
       .sorted()
       .toList();
```

 ### Reverse Ordering

```
numbers.stream()
       .sorted(Comparator.reverseOrder())
       .toList();
```

 ### Object Sorting

```
employees.stream()
         .sorted(Comparator.comparing(Employee::getSalary))
         .toList();
```

 ### Characteristics

 - Intermediate operation.
- Stateful operation.
- Typically requires additional memory.
- Typical time complexity: `O(N log N)`.

---

 ## 10\. distinct()

 ### Definition

 > `distinct()` removes duplicate elements.

```
numbers.stream()
       .distinct()
       .toList();
```

 ### Uses

- Remove duplicate values.
- Obtain unique elements.

 ### Duplicate Detection

Uses:

```
equals()
hashCode()
```

 For custom objects, correct `equals()` and `hashCode()` implementations are important.

---

 ## 11\. limit()

 ### Definition

 > `limit()` restricts the stream to at most N elements.

```
numbers.stream()
       .limit(3)
       .toList();
```

 ### Uses

 - Get top N elements.
- Restrict processing.
- Limit potentially large or infinite streams.

 ### Example

```
employees.stream()
         .sorted(Comparator.comparing(Employee::getSalary).reversed())
         .limit(3)
         .toList();
```

---

 ## 12\. skip()

 ### Definition

 > `skip()` ignores the first N elements.

```
numbers.stream()
       .skip(2)
       .toList();
```

 Example:

```
[10, 20, 30, 40, 50]
        ↓ skip(2)
[30, 40, 50]
```

 ### Uses

 - Skipping initial elements.
- Basic pagination-style processing.

---

 ## 13\. forEach()

 ### Definition

 > `forEach()` performs an action for each stream element.

```
names.stream()
     .forEach(System.out::println);
```

 ### Characteristics

 - Terminal operation.
- Useful for output and external actions.
- Avoid unnecessary shared-state mutation.

---

 ## 14\. collect()

 ### Definition

 > `collect()` accumulates stream elements into a result structure.

```
List<String> result = names.stream()
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```

 ### Common Collectors

```
toList()
toSet()
toMap()
joining()
groupingBy()
partitioningBy()
counting()
mapping()
summingInt()
averagingInt()
summarizingInt()
collectingAndThen()
```

---

 ## 15\. Stream.toList()

 Modern Java provides:

```
List<String> result = names.stream()
        .map(String::toUpperCase)
        .toList();
```

 ### Notes

- Concise way to obtain a List.
- Available since **Java 16**.

---

 ## 16\. reduce()

 ### Definition

 > `reduce()` combines stream elements into a single result.

```
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

 ### Uses

- Sum.
- Product.
- Combining values.
- Custom aggregation.

 ### Mental Model

```
1 + 2 + 3 + 4 + 5
        ↓
       15
```

 ### Rule

```
Multiple values → Single combined value
```

---

 ## 17\. count()

 ### Definition

 > `count()` returns the number of elements.

```
long count = numbers.stream()
        .count();
```

 ### Return Type

```
long
```

---

 ## 18\. min()

 ### Definition

 > `min()` returns the minimum element according to a Comparator.

```
Optional<Integer> min = numbers.stream()
        .min(Integer::compareTo);
```

 ### Return Type

```
Optional<T>
```

 The result may not exist for an empty stream.

---

 ## 19\. max()

 ### Definition

 > `max()` returns the maximum element according to a Comparator.

```
Optional<Integer> max = numbers.stream()
        .max(Integer::compareTo);
```

 ### Return Type

```
Optional<T>
```

---

 ## 20\. anyMatch()

 ### Definition

 > Checks whether at least one element satisfies a condition.

```
boolean result = numbers.stream()
        .anyMatch(n -> n > 100);
```

 ### Characteristics

- Terminal operation.
- Short-circuiting.
- May stop as soon as a match is found.

---

 ## 21\. allMatch()

 ### Definition

 > Checks whether every element satisfies a condition.

```
boolean result = numbers.stream()
        .allMatch(n -> n > 0);
```

 ### Characteristics

- Terminal operation.
- Short-circuiting.
- Stops when a failing element is found.

---

 ## 22\. noneMatch()

 ### Definition

 > Checks whether no elements satisfy a condition.

```
boolean result = numbers.stream()
        .noneMatch(n -> n < 0);
```

 ### Characteristics

- Terminal operation.
- Short-circuiting.

---

 ## 23\. findFirst()

 ### Definition

 > Returns the first matching element according to encounter order.

```
Optional<Integer> result = numbers.stream()
        .filter(n -> n > 10)
        .findFirst();
```

 ### Characteristics

 - Terminal operation.
- Returns `Optional<T>`.
- Short-circuiting.
- Useful when order matters.

---

 ## 24\. findAny()

 ### Definition

 > Returns any matching element.

```
Optional<Integer> result = numbers.stream()
        .filter(n -> n > 10)
        .findAny();
```

 ### Characteristics

 - Terminal operation.
- Short-circuiting.
- Particularly useful with parallel streams.
- Use `findFirst()` when the first element is specifically required.

---

 ## 25\. peek()

 ### Definition

 > `peek()` allows elements to be observed while passing through the pipeline.

```
numbers.stream()
       .peek(n -> System.out.println("Value: " + n))
       .filter(n -> n > 10)
       .toList();
```

 ### Primary Use

 - Debugging.
- Understanding pipeline execution.

 ### Avoid

 - Business logic.
- Important side effects.
- Shared mutable state.

---

 ## 26\. takeWhile()

 Introduced in **Java 9**.

 ### Definition

 > `takeWhile()` keeps elements while the condition remains true.

```
numbers.stream()
       .takeWhile(n -> n < 10)
       .toList();
```

 Example:

```
[1, 3, 5, 12, 15]
        ↓
[1, 3, 5]
```

---

 ## 27\. dropWhile()

 Introduced in **Java 9**.

 ### Definition

 > `dropWhile()` skips elements while the condition remains true.

```
numbers.stream()
       .dropWhile(n -> n < 10)
       .toList();
```

 Example:

```
[1, 3, 5, 12, 15]
        ↓
[12, 15]
```

---

 ## 28\. Primitive Streams

 Java provides:

```
IntStream
LongStream
DoubleStream
```

 ### Benefits

- Avoid unnecessary boxing.
(
    int num = 5;              // primitive
    Integer boxed = num;      // boxing (primitive → object)
)

(
Integer boxed = 10;       // object
int unboxed = boxed;      // unboxing (object → primitive)
)

- Provide specialized numeric operations.
- Useful for numeric calculations.

---

 ## 29\. mapToInt()

 ### Definition

 > Converts an object stream into an `IntStream`.

```
int total = employees.stream()
        .mapToInt(Employee::getSalary)
        .sum();
```

 ### Useful Operations

```
sum()
average()
min()
max()
count()
summaryStatistics()
```

---

 ## 30\. mapToLong()

```
long total = employees.stream()
        .mapToLong(Employee::getSalary)
        .sum();
```

 Useful for `long`-based numeric processing.

---

 ## 31\. mapToDouble()

```
double average = employees.stream()
        .mapToDouble(Employee::getSalary)
        .average()
        .orElse(0);
```

 Useful for decimal calculations.

---

 ## 32\. Numeric Stream Operations

 Primitive streams support operations such as:

```
sum()
average()
min()
max()
count()
```

 Example:

```
IntStream.of(10, 20, 30)
         .sum();
```

---

 ## 33\. groupingBy()

 ### Definition

 > `groupingBy()` groups elements according to a calculated key.

```
Map<Integer, List<String>> result =
        cities.stream()
              .collect(Collectors.groupingBy(String::length));
```

 ### Example

```
5 → [Paris, Tokyo]
6 → [London]
```

 ### Uses

 - Group employees by department.
- Group products by category.
- Group records by calculated properties.

---

 ## 34\. groupingBy() + counting()

```
Map<String, Long> result =
        employees.stream()
                 .collect(Collectors.groupingBy(
                     Employee::getDepartment,
                     Collectors.counting()
                 ));
```

 Example:

```
IT       → 10
HR       → 5
Finance  → 8
```

---

 ## 35\. groupingBy() + mapping()

```
Map<String, List<String>> result =
        employees.stream()
                 .collect(Collectors.groupingBy(
                     Employee::getDepartment,
                     Collectors.mapping(
                         Employee::getName,
                         Collectors.toList()
                     )
                 ));
```

 ### Use

 - Group objects.
- Store only a selected property from each group.

---

 ## 36\. partitioningBy()

 ### Definition

 > `partitioningBy()` divides elements into exactly two groups based on a boolean condition.

```
Map<Boolean, List<Integer>> result =
        numbers.stream()
               .collect(Collectors.partitioningBy(
                   n -> n % 2 == 0
               ));
```

 Result:

```
true  → even
false → odd
```

 ### Key Difference

```
groupingBy()
→ Multiple groups based on keys

partitioningBy()
→ Exactly two groups: true / false
```

---

 ## 37\. toSet()

```
Set<String> result = names.stream()
        .collect(Collectors.toSet());
```

 ### Use When

 - Unique values are required.
- Set semantics are appropriate.

---

 ## 38\. toMap()

 ### Definition

 > `toMap()` converts stream elements into key-value pairs.

```
Map<Integer, String> result =
        employees.stream()
                 .collect(Collectors.toMap(
                     Employee::getId,
                     Employee::getName
                 ));
```

 ### Mental Model

```
Object → Key + Value
```

---

 ## 39\. toMap() and Duplicate Keys

 This can fail if duplicate keys exist:

```
Collectors.toMap(
    Employee::getDepartment,
    Employee::getName
);
```

 Use a merge function:

```
Collectors.toMap(
    Employee::getDepartment,
    Employee::getName,
    (oldValue, newValue) -> oldValue
);
```

 ### Interview Rule

 > Always check whether duplicate keys are possible when using `toMap()`.

---

 ## 40\. joining()

 ### Definition

 > `joining()` combines stream elements into a single String.

```
String result = names.stream()
        .collect(Collectors.joining(", "));
```

 Result:

```
Alice, Bob, Charlie
```

 ### Prefix and Suffix

```
Collectors.joining(", ", "[", "]")
```

 Result:

```
[Alice, Bob, Charlie]
```

---

 ## 41\. summingInt()

```
int total =
        employees.stream()
                 .collect(Collectors.summingInt(
                     Employee::getSalary
                 ));
```

 ### Use

 - Sum a numeric property across elements.

---

 ## 42\. averagingInt()

```
double average =
        employees.stream()
                 .collect(Collectors.averagingInt(
                     Employee::getSalary
                 ));
```

 ### Use

 - Calculate the average of a numeric property.

---

 ## 43\. summarizingInt()

 ### Definition

 > `summarizingInt()` produces multiple statistics for an integer property.

```
IntSummaryStatistics stats =
        employees.stream()
                 .collect(Collectors.summarizingInt(
                     Employee::getSalary
                 ));
```

 Provides:

```
stats.getCount();
stats.getSum();
stats.getMin();
stats.getMax();
stats.getAverage();
```

---

 ## 44\. collectingAndThen()

 ### Definition

 > `collectingAndThen()` performs an additional operation after collection.

```
List<String> result =
        employees.stream()
                 .collect(Collectors.collectingAndThen(
                     Collectors.toList(),
                     Collections::unmodifiableList
                 ));
```

 ### Mental Model

```
Stream
  ↓
Collect
  ↓
Finish / Transform Result
```

---

 ## 45\. Optional with Streams

 Common Stream operations returning `Optional` include:

```
findFirst()
findAny()
min()
max()
average()
```

 ### Why Optional?

 - The requested value may not exist.
- Helps represent absence explicitly.

 Example:

```
Optional<String> result =
        names.stream()
             .filter(n -> n.startsWith("Z"))
             .findFirst();
```

---

 ## 46\. Optional map()

```
optionalName.map(String::toUpperCase);
```

 ### Definition

 > Transforms the value when it is present.

---

 ## 47\. Optional filter()

```
optionalName
    .filter(name -> name.length() > 5);
```

 ### Definition

 > Keeps the Optional value only when the condition is satisfied.

---

 ## 48\. Optional flatMap()

```
optionalUser.flatMap(User::getAddress);
```

 ### Definition

 > Used when the mapping function itself returns an Optional.

 Prevents:

```
Optional<Optional<T>>
```

---

 ## 49\. orElse()

```
String name = optionalName.orElse("Unknown");
```

 ### Definition

 > Returns the value if present; otherwise returns the fallback.

---

 ## 50\. orElseGet()

```
String name = optionalName
        .orElseGet(() -> getDefaultName());
```

 ### Difference

```
orElse()
→ Fallback expression is evaluated.

orElseGet()
→ Fallback supplier is evaluated only when needed.
```

---

 ## 51\. orElseThrow()

```
String name = optionalName.orElseThrow();
```

 ### Use

 - When absence should result in an exception.

---

 ## 52\. Stream Laziness

 ### Definition

 > Intermediate Stream operations are lazy and execute only when a terminal operation requires the result.

 Example:

```
numbers.stream()
       .filter(n -> {
           System.out.println(n);
           return n > 10;
       });
```

 Nothing is processed yet.

 Execution begins with:

```
.toList();
```

 ### Key Rule

```
Intermediate Operation
→ Builds the pipeline

Terminal Operation
→ Triggers execution
```

---

 ## 53\. Short-Circuiting

 ### Definition

 > A short-circuiting operation can stop processing once the required result is known.

 Examples:

```
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
limit()
takeWhile()
```

 Example:

```
numbers.stream()
       .filter(n -> n > 10)
       .findFirst();
```

 The entire stream may not need to be processed.

---

 ## 54\. Stateless vs Stateful Operations

 ### Stateless Operations

 Each element can generally be processed independently.

 Examples:

```
filter()
map()
peek()
```

 ### Stateful Operations

 May need information about multiple elements.

 Examples:

```
sorted()
distinct()
```

 ### Why It Matters

 Important for:

 - Performance.
- Memory usage.
- Parallel streams.

---

 ## 55\. Encounter Order

 ### Definition

 > Encounter order is the order in which a stream presents elements when its source has an established order.

 Examples:

 - `List` normally has encounter order.
- Some `Set` implementations do not guarantee a meaningful order.

 Order can matter for:

```
findFirst()
forEachOrdered()
sorted()
```

---

 ## 56\. forEach() vs forEachOrdered()

 ### forEach()

```
stream.forEach(System.out::println);
```

 - Does not guarantee encounter order for parallel streams.

 ### forEachOrdered()

```
stream.forEachOrdered(System.out::println);
```

 - Preserves encounter order when applicable.
- May reduce some parallel-processing benefits.

---

 ## 57\. Streams Cannot Be Reused

 Invalid:

```
Stream<String> stream = names.stream();

stream.count();
stream.forEach(System.out::println);
```

 The second operation fails because the Stream has already been consumed.

 ### Correct

```
names.stream().count();

names.stream().forEach(System.out::println);
```

---

 ## 58\. Streams and Source Modification

 Stream operations normally do not modify the source.

```
List<Integer> numbers = List.of(1, 2, 3);

List<Integer> result = numbers.stream()
        .map(n -> n * 10)
        .toList();
```

 Original:

```
[1, 2, 3]
```

 Result:

```
[10, 20, 30]
```

---

 ## 59\. Sequential Streams

 Created using:

```
list.stream();
```

 ### Characteristics

 - Sequential by default.
- Operations normally execute in encounter order where applicable.
- Simple and predictable.

 ### Prefer When

 - Dataset is moderate.
- Operations are inexpensive.
- Ordering matters.
- Simplicity is important.

---

 ## 60\. Parallel Streams

 Created using:

```
list.parallelStream();
```

 or:

```
list.stream().parallel();
```

 ### Characteristics

 - Can execute independent operations concurrently.
- Uses the Java Fork/Join common pool by default.
- Can benefit CPU-intensive workloads.

 ### Consider When

 - Dataset is sufficiently large.
- Per-element processing is expensive.
- Operations are independent.
- Workload scales well across CPU cores.

 ### Avoid Blindly Using When

 - Dataset is small.
- Operations are I/O-heavy.
- Shared mutable state exists.
- Ordering is critical.
- Parallel overhead outweighs the benefit.

---

 ## 61\. Parallel Stream Thread Safety

 Avoid unsafe shared mutation:

```
List<Integer> result = new ArrayList<>();

numbers.parallelStream()
       .forEach(result::add);
```

 Prefer:

```
List<Integer> result =
        numbers.parallelStream()
               .filter(...)
               .toList();
```

 ### Principle

 > Prefer stateless operations and Stream-managed collection over shared mutable state.

---

 ## 62\. Common Stream Pipeline

```
List<String> result =
        employees.stream()
                 .filter(Employee::isActive)
                 .sorted(Comparator.comparing(Employee::getName))
                 .map(Employee::getName)
                 .toList();
```

 Mental model:

```
Employees
    ↓
filter
    ↓
sorted
    ↓
map
    ↓
toList
```

---

 ## 63\. map() vs flatMap()

 ### map()

```
One input → One output
```

 Example:

```
.map(Employee::getName)
```

 ### flatMap()

```
One input → Multiple values → Flatten
```

 Example:

```
.flatMap(dept -> dept.getEmployees().stream())
```

 ### Remember

```
map     = Transform
flatMap = Transform + Flatten
```

---

 ## 64\. filter() vs map()

 ### filter()

 Changes the number of elements.

```
[1, 2, 3, 4]
      ↓ filter even
[2, 4]
```

 ### map()

 Transforms values.

```
[1, 2, 3, 4]
      ↓ map × 10
[10, 20, 30, 40]
```

---

 ## 65\. reduce() vs collect()

 ### reduce()

 Use for:

```
Multiple values → One combined value
```

 Example:

```
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

 ### collect()

 Use for:

```
Stream → Collection / Map / Result Container
```

 Example:

```
List<String> names = employees.stream()
        .map(Employee::getName)
        .toList();
```

 ### Simple Rule

```
Single combined value → reduce()
Collection / Map       → collect()
```

---

 ## 66\. groupingBy() vs partitioningBy()

 ### groupingBy()

```
Multiple possible groups
```

 Example:

```
IT       → [...]
HR       → [...]
Finance  → [...]
```

 ### partitioningBy()

```
Exactly two groups
```

 Example:

```
true  → [...]
false → [...]
```

---

 ## 67\. Common Stream Problem Patterns

 ### Find Duplicates

 For simple learning examples:

```
Set<Integer> duplicates =
        numbers.stream()
               .filter(n -> Collections.frequency(numbers, n) > 1)
               .collect(Collectors.toSet());
```

 For large datasets, prefer a frequency map or another `O(N)`-style approach instead of repeatedly calling `Collections.frequency()`.

---

 ### Find Second-Highest Number

```
Optional<Integer> secondHighest =
        numbers.stream()
               .distinct()
               .sorted(Comparator.reverseOrder())
               .skip(1)
               .findFirst();
```

---

 ### Find Top 3 Salaries

```
employees.stream()
         .sorted(Comparator.comparing(Employee::getSalary).reversed())
         .limit(3)
         .toList();
```

---

 ### Group Employees by Department

```
employees.stream()
         .collect(Collectors.groupingBy(
             Employee::getDepartment
         ));
```

---

 ### Count Employees by Department

```
employees.stream()
         .collect(Collectors.groupingBy(
             Employee::getDepartment,
             Collectors.counting()
         ));
```

---

 ### Highest Salary by Department

```
employees.stream()
         .collect(Collectors.groupingBy(
             Employee::getDepartment,
             Collectors.maxBy(
                 Comparator.comparing(Employee::getSalary)
             )
         ));
```

---

 ### Get Employee Names

```
employees.stream()
         .map(Employee::getName)
         .toList();
```

---

 ### Get Employees Above a Salary

```
employees.stream()
         .filter(e -> e.getSalary() > 100000)
         .toList();
```

---

 ### Check Whether an Employee Exists

```
employees.stream()
         .anyMatch(e -> e.getName().equals("Alice"));
```

---

 ## 68\. Stream Complexity — General Guidelines

 Complexity depends on the source, ordering, implementation, and pipeline.

 | Operation | Typical Time | Typical Extra Space |
| --- | --- | --- |
| `filter()` | O(N) | O(1) |
| `map()` | O(N) | O(1) |
| `flatMap()` | O(total elements processed) | Depends on pipeline |
| `sorted()` | O(N log N) | O(N) |
| `distinct()` | O(N) average | O(N) |
| `limit()` | Depends on source/order | Usually low |
| `skip()` | Depends on source/order | Usually low |
| `count()` | Usually O(N) | O(1) |
| `reduce()` | O(N) | O(1) for simple reductions |
| `min()` | O(N) | O(1) |
| `max()` | O(N) | O(1) |
| `anyMatch()` | O(N) worst case | O(1) |
| `allMatch()` | O(N) worst case | O(1) |
| `noneMatch()` | O(N) worst case | O(1) |



 ## 69\. Common Mistakes

 ### Reusing a Stream

```
stream.count();
stream.toList(); // ❌
```

 Create a new Stream instead.

 ### Using forEach() for Collection Building

 Avoid:

```
List<String> result = new ArrayList<>();

names.stream()
     .forEach(result::add);
```

 Prefer:

```
List<String> result = names.stream()
        .toList();
```


---

 ## 70\. When NOT to Use Streams

 Prefer a normal loop when:

 - Logic is highly stateful.
- Multiple mutable variables are required.
- Complex branching reduces readability.
- Debugging becomes difficult.
- `break` / `continue` behavior is central to the algorithm.
- A loop communicates the intent more clearly.

 ### Principle

 > Use Streams when they improve clarity, not simply because Streams are available. and note that streams are one timers i mean when once they are gone they cant be the same after used you can create new Streams tho :

---

 ## 71\. Stream API Mental Cheat Sheet

```
START
  ↓
stream()
  ↓
filter()       → Select elements
  ↓
map()          → Transform elements
  ↓
flatMap()      → Flatten nested data
  ↓
distinct()     → Remove duplicates
  ↓
sorted()       → Sort
  ↓
limit()        → Take first N
  ↓
skip()         → Ignore first N
  ↓
TERMINAL
  ↓
toList()       → List
collect()      → Collection / Map / Result
reduce()       → Single combined value
count()        → Number of elements
min()/max()    → Minimum / Maximum
findFirst()    → First match
findAny()      → Any match
anyMatch()     → At least one
allMatch()     → Every element
noneMatch()    → No element
forEach()      → Perform action
```

---

 ## 72. Most Important Operations

 ### Intermediate Operations

```
filter()
map()
flatMap()
sorted()
distinct()
limit()
skip()
peek()
takeWhile()
dropWhile()
mapToInt()
mapToLong()
mapToDouble()
```

 ### Terminal Operations

```
toList()
collect()
reduce()
forEach()
count()
min()
max()
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
```

 ### Important Collectors

```
toList()
toSet()
toMap()
joining()
groupingBy()
partitioningBy()
counting()
mapping()
summingInt()
averagingInt()
summarizingInt()
collectingAndThen()
```

---

 ## 73\. Java Version Reference

 | Feature | Introduced |
| --- | --- |
| Stream API | Java 8 |
| Lambda Expressions | Java 8 |
| Optional | Java 8 |
| Collectors | Java 8 |
| Primitive Streams | Java 8 |
| `parallelStream()` | Java 8 |
| `takeWhile()` | Java 9 |
| `dropWhile()` | Java 9 |
| `Stream.toList()` | Java 16 |

---

 ## 74\. Strong-Intermediate Checklist

 ### Fundamentals

 - [ ] Stream definition.
- [ ] Stream vs Collection.
- [ ] Stream pipeline.
- [ ] Source, intermediate, and terminal operations.
- [ ] Lazy evaluation.
- [ ] Stream reuse rules.
- [ ] Source modification behavior.

 ### Core Operations

 - [ ] `filter()`
- [ ] `map()`
- [ ] `flatMap()`
- [ ] `sorted()`
- [ ] `distinct()`
- [ ] `limit()`
- [ ] `skip()`
- [ ] `forEach()`
- [ ] `collect()`
- [ ] `toList()`
- [ ] `reduce()`
- [ ] `count()`
- [ ] `min()`
- [ ] `max()`

 ### Searching and Matching

 - [ ] `findFirst()`
- [ ] `findAny()`
- [ ] `anyMatch()`
- [ ] `allMatch()`
- [ ] `noneMatch()`
- [ ] Short-circuiting.

 ### Collectors

 - [ ] `groupingBy()`
- [ ] `partitioningBy()`
- [ ] `toMap()`
- [ ] Duplicate-key handling.
- [ ] `joining()`
- [ ] `mapping()`
- [ ] `counting()`
- [ ] `summingInt()`
- [ ] `averagingInt()`
- [ ] `summarizingInt()`
- [ ] `collectingAndThen()`

 ### Advanced Concepts

 - [ ] `peek()`
- [ ] `takeWhile()`
- [ ] `dropWhile()`
- [ ] Primitive streams.
- [ ] `mapToInt()`
- [ ] `mapToLong()`
- [ ] `mapToDouble()`
- [ ] `Optional` with Streams.
- [ ] Encounter order.
- [ ] Stateless vs stateful operations.
- [ ] Sequential vs parallel streams.
- [ ] Thread-safety concerns.
- [ ] Stream complexity.
- [ ] Knowing when not to use Streams.


---

 ## 75\. Final Mental Model

```
                    STREAM API
                         |
        +----------------+----------------+
        |                |                |
      SOURCE         PROCESSING         RESULT
        |                |                |
     List/Set         filter()          toList()
     Array            map()             collect()
     Map              flatMap()         reduce()
     Range            sorted()          count()
                      distinct()        findFirst()
                      limit()           anyMatch()
                      skip()            min()/max()
```

 ### Five Core Ideas

 1. **Stream = data-processing pipeline, not a data structure.**
2. **Intermediate operations are lazy.**
3. **Terminal operations trigger execution.**
4. **`map()` transforms, `filter()` selects, and `flatMap()` flattens.**
5. **Use Streams for readable data processing, not for every problem.**

---

 ## 76\. One-Liners

 ### What is a Stream?

 > A Stream is a Java API for processing sequences of elements using functional-style operations.

 ### Is Stream a data structure?

 > No. A Stream processes data from a source; it does not permanently store the data.

 ### Is Stream lazy?

 > Intermediate operations are lazy and execute when a terminal operation is invoked.

 ### Can a Stream be reused?

 > No. Once consumed by a terminal operation, a Stream cannot be reused.

 ### Does Stream modify the original collection?

 > Stream operations normally do not modify the source unless explicit side effects are introduced.

 ### `map()` vs `flatMap()`?

 > `map()` transforms each element, while `flatMap()` transforms and flattens nested results.

 ### `map()` vs `filter()`?

 > `map()` transforms values, while `filter()` selects values based on a condition.

 ### `reduce()` vs `collect()`?

 > `reduce()` combines elements into a single value, while `collect()` accumulates elements into a result container.

 ### `groupingBy()` vs `partitioningBy()`?

 > `groupingBy()` creates groups based on keys, while `partitioningBy()` creates two groups based on a boolean condition.

 ### What is short-circuiting?

 > Short-circuiting allows stream processing to stop when the required result is already known.

 ### Should parallelStream() always be used for large data?

 > No. Parallel streams have overhead and should be used only when the workload benefits from parallel execution.

---

 ## 77\. Final Problem-Solving Approach

 When solving a Stream problem, ask:

```
1. What is my source?
2. Do I need to FILTER?
3. Do I need to TRANSFORM?
4. Do I need to FLATTEN?
5. Do I need to REMOVE DUPLICATES?
6. Do I need to SORT?
7. Do I need GROUPING or PARTITIONING?
8. Do I need a SINGLE VALUE or a COLLECTION?
9. Can I SHORT-CIRCUIT?
10. Would PARALLEL processing actually help?
11. Would a normal loop be clearer?
```

 ### Core Decision Guide

```
Need to select?
→ filter()

Need to transform?
→ map()

Need to flatten?
→ flatMap()

Need unique values?
→ distinct()

Need ordering?
→ sorted()

Need first N?
→ limit()

Need to skip N?
→ skip()

Need one combined result?
→ reduce()

Need a collection/map?
→ collect() / toList()

Need groups?
→ groupingBy()

Need true/false split?
→ partitioningBy()

Need to check a condition?
→ anyMatch() / allMatch() / noneMatch()

Need one element?
→ findFirst() / findAny()

Need numeric calculations?
→ mapToInt() / mapToLong() / mapToDouble()

Need debugging?
→ peek()

Need parallel processing?
→ parallelStream() — only when justified.
```