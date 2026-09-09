import java.util.*;
import java.util.function.*;

class Main {

    public static void main(String[] args) {

        // ========================================================
        // 1. PREDICATE
        // T -> boolean
        // Used for checking conditions
        // ========================================================

        Predicate<Integer> isEven = n -> n % 2 == 0;

        System.out.println(isEven.test(10)); // true


        // ========================================================
        // 2. CONSUMER
        // T -> void
        // Used to perform an action
        // ========================================================

        Consumer<String> print = name ->
                System.out.println("Hello " + name);

        print.accept("Krishna");


        // ========================================================
        // 3. FUNCTION
        // T -> R
        // Used to transform a value
        // ========================================================

        Function<Integer, Integer> square = n -> n * n;

        System.out.println(square.apply(5)); // 25


        // ========================================================
        // 4. SUPPLIER
        // () -> T
        // Used to provide/generate a value
        // ========================================================

        Supplier<String> message = () -> "Java is fun";

        System.out.println(message.get());


        // ========================================================
        // 5. UNARY OPERATOR
        // T -> T
        // Input and output are the same type
        // ========================================================

        UnaryOperator<Integer> doubleValue = n -> n * 2;

        System.out.println(doubleValue.apply(5)); // 10


        // ========================================================
        // 6. BINARY OPERATOR
        // (T, T) -> T
        // Takes two values and returns the same type
        // ========================================================

        BinaryOperator<Integer> add = (a, b) -> a + b;

        System.out.println(add.apply(10, 20)); // 30


        // ========================================================
        // 7. BIPREDICATE
        // (T, U) -> boolean
        // Checks a condition using two values
        // ========================================================

        BiPredicate<Integer, Integer> isGreater =
                (a, b) -> a > b;

        System.out.println(isGreater.test(20, 10)); // true


        // ========================================================
        // 8. BICONSUMER
        // (T, U) -> void
        // Consumes two values
        // ========================================================

        BiConsumer<String, Integer> printPerson =
                (name, age) ->
                        System.out.println(name + " is " + age);

        printPerson.accept("Krishna", 22);


        // ========================================================
        // 9. BIFUNCTION
        // (T, U) -> R
        // Takes two inputs and returns a result
        // ========================================================

        BiFunction<Integer, Integer, Integer> multiply =
                (a, b) -> a * b;

        System.out.println(multiply.apply(5, 4)); // 20


        // ========================================================
        // 10. PREDICATE CHAINING
        // and(), or(), negate()
        // ========================================================

        Predicate<Integer> positive = n -> n > 0;
        Predicate<Integer> even = n -> n % 2 == 0;

        Predicate<Integer> positiveEven =
                positive.and(even);

        System.out.println(positiveEven.test(10)); // true

        Predicate<Integer> positiveOrEven =
                positive.or(even);

        System.out.println(positiveOrEven.test(-2)); // true

        Predicate<Integer> notEven =
                even.negate();

        System.out.println(notEven.test(5)); // true


        // ========================================================
        // 11. FUNCTION CHAINING
        // andThen()
        // ========================================================

        Function<Integer, Integer> addTen = n -> n + 10;
        Function<Integer, Integer> multiplyByTwo = n -> n * 2;

        Function<Integer, Integer> combined =
                addTen.andThen(multiplyByTwo);

        // 5 + 10 = 15
        // 15 * 2 = 30
        System.out.println(combined.apply(5));


        // ========================================================
        // 12. FUNCTION compose()
        // compose() executes the second function first
        // ========================================================

        Function<Integer, Integer> composed =
                addTen.compose(multiplyByTwo);

        // 5 * 2 = 10
        // 10 + 10 = 20
        System.out.println(composed.apply(5));


        // ========================================================
        // 13. CONSUMER CHAINING
        // andThen()
        // ========================================================

        Consumer<String> first =
                s -> System.out.println("First: " + s);

        Consumer<String> second =
                s -> System.out.println("Second: " + s);

        Consumer<String> both =
                first.andThen(second);

        both.accept("Java");


        // ========================================================
        // 14. METHOD REFERENCE
        // Lambda:
        // name -> System.out.println(name)
        //
        // Method reference:
        // System.out::println
        // ========================================================

        List<String> names =
                Arrays.asList("Krishna", "Rahul", "Amit");

        names.forEach(System.out::println);


        // ========================================================
        // 15. STATIC METHOD REFERENCE
        // ClassName::staticMethod
        // ========================================================

        Function<Integer, Integer> absolute =
                Math::abs;

        System.out.println(absolute.apply(-50)); // 50


        // ========================================================
        // 16. INSTANCE METHOD REFERENCE
        // object::method
        // ========================================================

        String text = "hello";

        Supplier<String> upper =
                text::toUpperCase;

        System.out.println(upper.get()); // HELLO


        // ========================================================
        // 17. CONSTRUCTOR REFERENCE
        // ClassName::new
        // ========================================================

        Supplier<ArrayList<String>> listCreator =
                ArrayList::new;

        ArrayList<String> list = listCreator.get();

        list.add("Java");

        System.out.println(list);


        // ========================================================
        // 18. LAMBDA WITH COLLECTIONS
        // forEach()
        // ========================================================

        List<Integer> numbers =
                Arrays.asList(10, 20, 30, 40);

        numbers.forEach(n ->
                System.out.println(n));


        // ========================================================
        // 19. LAMBDA WITH SORTING
        // Comparator + Lambda
        // ========================================================

        List<Integer> values =
                new ArrayList<>(Arrays.asList(50, 10, 40, 20));

        values.sort((a, b) -> a - b);

        System.out.println(values);


        // ========================================================
        // 20. COMPARATOR
        // comparing()
        // ========================================================

        List<String> words =
                new ArrayList<>(
                        Arrays.asList("Java", "C", "Python", "Go")
                );

        words.sort(Comparator.comparing(String::length));

        System.out.println(words);


        // ========================================================
        // 21. COMPARATOR reversed()
        // Sort in descending order
        // ========================================================

        words.sort(
                Comparator.comparing(String::length).reversed()
        );

        System.out.println(words);


        // ========================================================
        // 22. COMPARATOR thenComparing()
        // First sort by length
        // Then sort alphabetically
        // ========================================================

        words.sort(
                Comparator.comparing(String::length)
                          .thenComparing(String::compareTo)
        );

        System.out.println(words);


        // ========================================================
        // 23. STREAM + FILTER
        // filter() takes a Predicate
        // ========================================================

        List<Integer> nums =
                Arrays.asList(1, 2, 3, 4, 5, 6);

        nums.stream()
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);


        // ========================================================
        // 24. STREAM + MAP
        // map() takes a Function
        // ========================================================

        nums.stream()
                .map(n -> n * n)
                .forEach(System.out::println);


        // ========================================================
        // 25. STREAM + FILTER + MAP
        // Very common real-world Lambda pattern
        // ========================================================

        nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .forEach(System.out::println);


        // ========================================================
        // 26. STREAM + SORTED
        // ========================================================

        nums.stream()
                .sorted()
                .forEach(System.out::println);


        // ========================================================
        // 27. STREAM + DISTINCT
        // ========================================================

        List<Integer> duplicateNumbers =
                Arrays.asList(1, 2, 2, 3, 3, 4);

        duplicateNumbers.stream()
                .distinct()
                .forEach(System.out::println);


        // ========================================================
        // 28. STREAM + REDUCE
        // Combines multiple values into one value
        // ========================================================

        int sum = nums.stream()
                .reduce(0, (a, b) -> a + b);

        System.out.println(sum); // 21


        // ========================================================
        // 29. OPTIONAL + LAMBDA
        // ifPresent()
        // ========================================================

        Optional<String> username =
                Optional.of("Krishna");

        username.ifPresent(name ->
                System.out.println("User: " + name));


        // ========================================================
        // 30. OPTIONAL + MAP
        // ========================================================

        Optional<String> result =
                username.map(String::toUpperCase);

        System.out.println(result.get());


        // ========================================================
        // 31. OPTIONAL + FILTER
        // ========================================================

        Optional<String> validName =
                username.filter(name ->
                        name.length() > 5);

        validName.ifPresent(System.out::println);


        // ========================================================
        // 32. OPTIONAL + orElseGet()
        // Supplier is used internally
        // ========================================================

        String name = username.orElseGet(
                () -> "Guest"
        );

        System.out.println(name);


        // ========================================================
        // 33. VARIABLE CAPTURE
        // Local variables must be final or effectively final
        // ========================================================

        int age = 22;

        Consumer<String> showAge =
                name2 -> System.out.println(
                        name2 + " is " + age
                );

        showAge.accept("Krishna");


        // ========================================================
        // 34. PRIMITIVE FUNCTIONAL INTERFACES
        // Avoid unnecessary boxing/unboxing
        // ========================================================

        IntPredicate positiveNumber =
                n -> n > 0;

        IntConsumer printNumber =
                n -> System.out.println(n);

        IntFunction<String> convertToString =
                n -> "Number: " + n;

        IntSupplier randomNumber =
                () -> 100;

        System.out.println(positiveNumber.test(10));
        printNumber.accept(50);
        System.out.println(convertToString.apply(10));
        System.out.println(randomNumber.getAsInt());


        // ========================================================
        // 35. LAMBDA WITH MAP
        // ========================================================

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
    }
}