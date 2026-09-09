Below is a **GitHub-ready `Lambda_Cheatbook.md`** with the concepts organized from basic → intermediate, while keeping the explanations concise and practical.

 # Java Lambda Expressions — Cheatbook

 > A practical reference for Java Lambda Expressions, Functional Interfaces, Method References, Collections, Streams, and Optional.

---

 ## 1\. Lambda Expression

 - **Definition:** A lambda is a concise way to provide an implementation of a functional interface.
- **Syntax:** `(parameters) -> expression`
- **Block syntax:** `(parameters) -> { statements; }`
- **Example:** `n -> n * 2`
- **Example:** `(a, b) -> a + b`
- **No parameter:** `() -> "Hello"`
- **One parameter:** `n -> n * n`
- **Multiple parameters:** `(a, b) -> a + b`

---

 ## 2\. Functional Interface

 - **Definition:** An interface containing exactly one abstract method.
- **Annotation:** `@FunctionalInterface`
- **Purpose:** Functional interfaces provide the target type for lambda expressions.
- **Common interfaces:** `Predicate`, `Consumer`, `Function`, `Supplier`
- **Example:** `@FunctionalInterface interface Calculator { int add(int a, int b); }`

---

 ## 3\. Predicate\<T\>

 - **Purpose:** Checks a condition.
- **Input → Output:** `T -> boolean`
- **Method:** `test()`
- **Example:** `Predicate<Integer> isEven = n -> n % 2 == 0;`
- **Call:** `isEven.test(10)`
- **Result:** `true`
- **Memory:** **Predicate = Check**

```
Predicate<Integer> isEven = n -> n % 2 == 0;

System.out.println(isEven.test(10));
```

---

 ## 4\. Consumer\<T\>

 - **Purpose:** Performs an action.
- **Input → Output:** `T -> void`
- **Method:** `accept()`
- **Example:** `Consumer<String> print = name -> System.out.println(name);`
- **Call:** `print.accept("Krishna")`
- **Memory:** **Consumer = Consume**

```
Consumer<String> print =
        name -> System.out.println("Hello " + name);

print.accept("Krishna");
```

---

 ## 5\. Function\<T, R\>

 - **Purpose:** Transforms one value into another.
- **Input → Output:** `T -> R`
- **Method:** `apply()`
- **Example:** `Function<Integer, Integer> square = n -> n * n;`
- **Call:** `square.apply(5)`
- **Memory:** **Function = Transform**

```
Function<Integer, Integer> square = n -> n * n;

System.out.println(square.apply(5));
```

---

 ## 6\. Supplier\<T\>

 - **Purpose:** Provides or generates a value.
- **Input → Output:** `() -> T`
- **Method:** `get()`
- **Example:** `Supplier<String> message = () -> "Java is fun";`
- **Call:** `message.get()`
- **Memory:** **Supplier = Provide**

```
Supplier<String> message = () -> "Java is fun";

System.out.println(message.get());
```

---

 # 7\. UnaryOperator\<T\>

 - **Purpose:** Takes one value and returns the same type.
- **Input → Output:** `T -> T`
- **Method:** `apply()`
- **Example:** `UnaryOperator<Integer> doubleValue = n -> n * 2;`

```
UnaryOperator<Integer> doubleValue =
        n -> n * 2;

System.out.println(doubleValue.apply(5));
```

---

 # 8\. BinaryOperator\<T\>

 - **Purpose:** Takes two values and returns the same type.
- **Input → Output:** `(T, T) -> T`
- **Method:** `apply()`
- **Example:** `(a, b) -> a + b`

```
BinaryOperator<Integer> add =
        (a, b) -> a + b;

System.out.println(add.apply(10, 20));
```

---

 # 9\. BiPredicate\<T, U\>

 - **Purpose:** Checks a condition using two inputs.
- **Input → Output:** `(T, U) -> boolean`
- **Method:** `test()`

```
BiPredicate<Integer, Integer> isGreater =
        (a, b) -> a > b;

System.out.println(isGreater.test(20, 10));
```

---

 # 10\. BiConsumer\<T, U\>

 - **Purpose:** Consumes two inputs and performs an action.
- **Input → Output:** `(T, U) -> void`
- **Method:** `accept()`

```
BiConsumer<String, Integer> printPerson =
        (name, age) ->
                System.out.println(name + " is " + age);

printPerson.accept("Krishna", 22);
```

---

 # 11\. BiFunction\<T, U, R\>

 - **Purpose:** Takes two inputs and returns a result.
- **Input → Output:** `(T, U) -> R`
- **Method:** `apply()`

```
BiFunction<Integer, Integer, Integer> multiply =
        (a, b) -> a * b;

System.out.println(multiply.apply(5, 4));
```

---

 # 12\. Functional Interface Quick Reference

 | Interface | Input | Output | Method |
| --- | --- | --- | --- |
| `Predicate<T>` | `T` | `boolean` | `test()` |
| `Consumer<T>` | `T` | `void` | `accept()` |
| `Function<T,R>` | `T` | `R` | `apply()` |
| `Supplier<T>` | none | `T` | `get()` |
| `UnaryOperator<T>` | `T` | `T` | `apply()` |
| `BinaryOperator<T>` | `T,T` | `T` | `apply()` |
| `BiPredicate<T,U>` | `T,U` | `boolean` | `test()` |
| `BiConsumer<T,U>` | `T,U` | `void` | `accept()` |
| `BiFunction<T,U,R>` | `T,U` | `R` | `apply()` |

---

 # 13\. Predicate Chaining

 - **`and()`** → Both conditions must be true.
- **`or()`** → At least one condition must be true.
- **`negate()`** → Reverses the result.

```
Predicate<Integer> positive = n -> n > 0;
Predicate<Integer> even = n -> n % 2 == 0;

Predicate<Integer> positiveEven =
        positive.and(even);

Predicate<Integer> positiveOrEven =
        positive.or(even);

Predicate<Integer> notEven =
        even.negate();
```

---

 # 14\. Function Chaining

 - **`andThen()`** → Executes the current function first.
- **`compose()`** → Executes the supplied function first.

```
Function<Integer, Integer> addTen =
        n -> n + 10;

Function<Integer, Integer> multiplyByTwo =
        n -> n * 2;

Function<Integer, Integer> combined =
        addTen.andThen(multiplyByTwo);

// 5 -> 15 -> 30
System.out.println(combined.apply(5));
```

```
Function<Integer, Integer> composed =
        addTen.compose(multiplyByTwo);

// 5 -> 10 -> 20
System.out.println(composed.apply(5));
```

---

 # 15\. Consumer Chaining

 - **`andThen()`** → Executes consumers sequentially.

```
Consumer<String> first =
        s -> System.out.println("First: " + s);

Consumer<String> second =
        s -> System.out.println("Second: " + s);

Consumer<String> both =
        first.andThen(second);

both.accept("Java");
```

---

 # 16\. Method Reference

 - **Definition:** Shorter syntax for a lambda that only calls an existing method.
- **Syntax:** `ClassName::methodName`
- **Lambda:** `name -> System.out.println(name)`
- **Method reference:** `System.out::println`

```
List<String> names =
        Arrays.asList("Krishna", "Rahul", "Amit");

names.forEach(System.out::println);
```

---

 # 17\. Static Method Reference

 - **Syntax:** `ClassName::staticMethod`

```
Function<Integer, Integer> absolute =
        Math::abs;

System.out.println(absolute.apply(-50));
```

---

 # 18\. Instance Method Reference

 - **Syntax:** `object::method`

```
String text = "hello";

Supplier<String> upper =
        text::toUpperCase;

System.out.println(upper.get());
```

---

 # 19\. Constructor Reference

 - **Syntax:** `ClassName::new`
- **Purpose:** Creates objects through a functional interface.

```
Supplier<ArrayList<String>> listCreator =
        ArrayList::new;

ArrayList<String> list =
        listCreator.get();

list.add("Java");
```

---

 # 20\. Lambda with Collections

 - **`forEach()`** accepts a `Consumer`.

```
List<Integer> numbers =
        Arrays.asList(10, 20, 30, 40);

numbers.forEach(n ->
        System.out.println(n));
```

---

 # 21\. Lambda with Sorting

 - `sort()` can accept a `Comparator`.
- Lambda can define the comparison logic.

```
List<Integer> values =
        new ArrayList<>(
                Arrays.asList(50, 10, 40, 20)
        );

values.sort((a, b) -> a - b);

System.out.println(values);
```

 - **Safer comparison:** `Integer.compare(a, b)`

```
values.sort(Integer::compare);
```

---

 # 22\. Comparator.comparing()

 - **Purpose:** Sort objects using a selected property.

```
List<String> words =
        new ArrayList<>(
                Arrays.asList("Java", "C", "Python", "Go")
        );

words.sort(
        Comparator.comparing(String::length)
);
```

---

 # 23\. Comparator.reversed()

 - **Purpose:** Reverse the comparator order.

```
words.sort(
        Comparator.comparing(String::length)
                  .reversed()
);
```

---

 # 24\. Comparator.thenComparing()

 - **Purpose:** Use a second comparison when the first comparison produces equal results.

```
words.sort(
        Comparator.comparing(String::length)
                  .thenComparing(String::compareTo)
);
```

---

 # 25\. Streams + Lambda

 - **Stream:** A pipeline for processing collections of data.
- **Lambda:** Commonly used to define stream operations.
- **Common operations:** `filter()`, `map()`, `sorted()`, `distinct()`, `reduce()`, `collect()`

---

 # 26\. Stream filter()

 - **Purpose:** Select elements matching a condition.
- `filter()` accepts a `Predicate`.

```
List<Integer> nums =
        Arrays.asList(1, 2, 3, 4, 5, 6);

nums.stream()
        .filter(n -> n % 2 == 0)
        .forEach(System.out::println);
```

---

 # 27\. Stream map()

 - **Purpose:** Transform each element.
- `map()` accepts a `Function`.

```
nums.stream()
        .map(n -> n * n)
        .forEach(System.out::println);
```

---

 # 28\. Stream filter + map

 - **Common pattern:** Filter data first, then transform it.

```
nums.stream()
        .filter(n -> n % 2 == 0)
        .map(n -> n * n)
        .forEach(System.out::println);
```

---

 # 29\. Stream sorted()

 - **Purpose:** Sort stream elements.

```
nums.stream()
        .sorted()
        .forEach(System.out::println);
```

---

 # 30\. Stream distinct()

 - **Purpose:** Remove duplicate elements.

```
List<Integer> duplicateNumbers =
        Arrays.asList(1, 2, 2, 3, 3, 4);

duplicateNumbers.stream()
        .distinct()
        .forEach(System.out::println);
```

---

 # 31\. Stream reduce()

 - **Purpose:** Combine multiple elements into one result.
- **Example:** Calculate a sum.

```
int sum = nums.stream()
        .reduce(0, (a, b) -> a + b);

System.out.println(sum);
```

 - **Method reference alternative:** `.reduce(0, Integer::sum)`

---

 # 32\. Optional + Lambda

 - **Purpose:** Represent a value that may or may not exist.
- `Optional` provides several lambda-friendly methods.

```
Optional<String> username =
        Optional.of("Krishna");

username.ifPresent(
        name -> System.out.println(name)
);
```

---

 # 33\. Optional.map()

 - **Purpose:** Transform the value inside an `Optional`.

```
Optional<String> result =
        username.map(String::toUpperCase);

System.out.println(result.get());
```

---

 # 34\. Optional.filter()

 - **Purpose:** Keep the value only when a condition is satisfied.

```
Optional<String> validName =
        username.filter(
                name -> name.length() > 5
        );

validName.ifPresent(System.out::println);
```

---

 # 35\. Optional.orElseGet()

 - **Purpose:** Generate a fallback value when the `Optional` is empty.
- **Uses:** `Supplier`.

```
String name =
        username.orElseGet(
                () -> "Guest"
        );
```

---

 # 36\. Variable Capture

 - A lambda can access local variables from its surrounding scope.
- Local variables must be **final or effectively final**.
- **Effectively final:** A variable that is not explicitly `final` but is never reassigned.

```
int age = 22;

Consumer<String> showAge =
        name -> System.out.println(
                name + " is " + age
        );

showAge.accept("Krishna");
```

 - This is invalid because `age` is reassigned:

```
int age = 22;

age = 23;

// Lambda cannot capture a non-effectively-final variable.
```

---

 # 37\. Primitive Functional Interfaces

 - **Purpose:** Work directly with primitive types and avoid unnecessary boxing/unboxing.
- `IntPredicate` → `int -> boolean`
- `IntConsumer` → `int -> void`
- `IntFunction<R>` → `int -> R`
- `IntSupplier` → `() -> int`

```
IntPredicate positive =
        n -> n > 0;

IntConsumer print =
        n -> System.out.println(n);

IntFunction<String> convert =
        n -> "Number: " + n;

IntSupplier number =
        () -> 100;
```

---

 # 38\. Lambda with Map

 - `Map.forEach()` accepts a `BiConsumer`.

```
Map<String, Integer> students =
        new HashMap<>();

students.put("Krishna", 90);
students.put("Rahul", 80);
students.put("Amit", 95);

students.forEach(
        (student, marks) ->
                System.out.println(
                        student + " : " + marks
                )
);
```

---

 # 39\. Custom Functional Interface

 - Use `@FunctionalInterface` to define your own functional interface.
- A lambda can implement the single abstract method.

```
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);
}

public class Main {

    public static void main(String[] args) {

        Calculator add =
                (a, b) -> a + b;

        System.out.println(
                add.calculate(10, 20)
        );
    }
}
```

---

 # 40\. Lambda vs Anonymous Class

 - **Lambda:** Concise and designed for functional interfaces.
- **Anonymous class:** Creates an anonymous implementation of a class/interface.
- **Important:** Lambda does not create its own `this`.
- **Anonymous class:** Has its own `this`.

```
// Lambda
Runnable task1 =
        () -> System.out.println("Lambda");

// Anonymous class
Runnable task2 =
        new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Class");
            }
        };
```

---

 # 41\. Common Lambda Patterns

 - **Check:** `Predicate<T>`
- **Action:** `Consumer<T>`
- **Transform:** `Function<T,R>`
- **Provide:** `Supplier<T>`
- **Transform same type:** `UnaryOperator<T>`
- **Combine two values:** `BinaryOperator<T>`
- **Two inputs + boolean:** `BiPredicate<T,U>`
- **Two inputs + action:** `BiConsumer<T,U>`
- **Two inputs + result:** `BiFunction<T,U,R>`

---

 # 42\. Lambda → Stream Relationship

 - `filter()` → usually uses `Predicate`.
- `map()` → usually uses `Function`.
- `forEach()` → uses `Consumer`.
- `orElseGet()` → uses `Supplier`.
- `sort()` → uses `Comparator`.
- `reduce()` → combines values using a function/operator.

```
Predicate  → filter()
Function   → map()
Consumer   → forEach()
Supplier   → orElseGet()
Comparator → sort()
Operator   → reduce()
```

---

 # 43\. Important Methods to Remember

 | Interface/Class | Important Methods |
| --- | --- |
| `Predicate` | `test()`, `and()`, `or()`, `negate()` |
| `Consumer` | `accept()`, `andThen()` |
| `Function` | `apply()`, `andThen()`, `compose()` |
| `Supplier` | `get()` |
| `UnaryOperator` | `apply()` |
| `BinaryOperator` | `apply()` |
| `BiPredicate` | `test()` |
| `BiConsumer` | `accept()` |
| `BiFunction` | `apply()` |
| `Comparator` | `comparing()`, `reversed()`, `thenComparing()` |
| `Optional` | `map()`, `filter()`, `ifPresent()`, `orElseGet()` |
| `Stream` | `filter()`, `map()`, `sorted()`, `distinct()`, `reduce()` |

---

 # 44\. Quick Memory Trick

```
P → Predicate  → Check       → test()
C → Consumer   → Action      → accept()
F → Function   → Transform   → apply()
S → Supplier   → Provide     → get()

U → UnaryOperator  → T → T
B → BinaryOperator → T,T → T

BiPredicate → Two inputs → boolean
BiConsumer  → Two inputs → void
BiFunction  → Two inputs → result
```

---

 # 45\. Learning Roadmap

```
Lambda Syntax
      ↓
Functional Interfaces
      ↓
Predicate / Consumer / Function / Supplier
      ↓
BiPredicate / BiConsumer / BiFunction
      ↓
UnaryOperator / BinaryOperator
      ↓
Method References
      ↓
Constructor References
      ↓
Predicate & Function Chaining
      ↓
Comparator + Lambdas
      ↓
Collections + Lambdas
      ↓
Streams + Lambdas
      ↓
Optional + Lambdas
      ↓
Custom Functional Interfaces
      ↓
Advanced Stream API
```

---

 ## Final Recap

 - **Predicate** → `T -> boolean` → **Check**
- **Consumer** → `T -> void` → **Action**
- **Function** → `T -> R` → **Transform**
- **Supplier** → `() -> T` → **Provide**
- **UnaryOperator** → `T -> T` → **Transform same type**
- **BinaryOperator** → `(T,T) -> T` → **Combine**
- **BiPredicate** → `(T,U) -> boolean` → **Check two values**
- **BiConsumer** → `(T,U) -> void` → **Consume two values**
- **BiFunction** → `(T,U) -> R` → **Transform two values**
- **Method Reference** → `::` → **Shorter lambda syntax**
- **Predicate chaining** → `and()`, `or()`, `negate()`
- **Function chaining** → `andThen()`, `compose()`
- **Comparator** → `comparing()`, `reversed()`, `thenComparing()`
- **Stream** → `filter()`, `map()`, `sorted()`, `distinct()`, `reduce()`
- **Optional** → `map()`, `filter()`, `ifPresent()`, `orElseGet()`
- **Variable capture** → Local variables must be **final/effectively final**
- **Primitive interfaces** → Avoid unnecessary boxing/unboxing
- **Custom functional interface** → Use `@FunctionalInterface`

 > **Core idea:** Lambda expressions are primarily about passing behavior as a value; functional interfaces define the shape of that behavior.