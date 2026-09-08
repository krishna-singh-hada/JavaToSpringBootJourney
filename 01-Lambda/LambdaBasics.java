import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
class Main {
    public static void BasicLambda(String[] args) {

        // --------------------------------------------------------
        // PREDICATE
        // T → boolean
        // Usage: Check / condition
        // Method: test()
        // --------------------------------------------------------

        Predicate<Integer> isEven = (n) -> n % 2 == 0;

        System.out.println(isEven.test(3));


        // --------------------------------------------------------
        // CONSUMER
        // T → nothing
        // Usage: Perform an action
        // Method: accept()
        // --------------------------------------------------------

        Consumer<String> print =
                (name) -> System.out.println("Hello " + name + " !!");

        print.accept("krishna");


        // --------------------------------------------------------
        // FUNCTION
        // T → R
        // Usage: Transform one value into another
        // Method: apply()
        // --------------------------------------------------------

        Function<Integer, Integer> square = (n) -> n * n;

        int n = 9;

        System.out.println(square.apply(n));

        System.out.println(n);


        // --------------------------------------------------------
        // SUPPLIER
        // Nothing → T
        // Usage: Provide / generate a value
        // Method: get()
        // --------------------------------------------------------

        Supplier<String> msg = () -> "Hello There !!";

        System.out.println(msg.get());


        // --------------------------------------------------------
        // MORE PREDICATE EXAMPLES
        // --------------------------------------------------------

        Predicate<String> checkEmpty = (s) -> s.isEmpty();

        System.out.println(checkEmpty.test(" krishna "));


        // --------------------------------------------------------
        // MORE FUNCTION EXAMPLES
        // --------------------------------------------------------

        Function<String, Integer> checkSize = (s) -> s.length();

        int size = checkSize.apply("kr1znaIsTooGoated");

        System.out.println(size);


        // --------------------------------------------------------
        // MORE SUPPLIER EXAMPLES
        // --------------------------------------------------------

        Supplier<String> jav = () -> "java is fun ";

        System.out.println(jav.get());
    }
}