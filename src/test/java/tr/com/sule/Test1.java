package tr.com.sule;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.context.transaction.TestTransaction.start;

/**
 * @author scinkir 5.10.2018
 */
public class Test1 {

    @Test
    public void test1() {
        new Thread(
                () -> {System.out.println("My Runnable");}).start();

        Employee[] employees  = {
                new Employee("David"),
                new Employee("Naveen"),
                new Employee("Alex"),
                new Employee("Richard")};
        System.out.println("Before Sorting Names: "+ Arrays.toString(employees));
        Arrays.sort(employees, Employee::nameCompare);
        System.out.println("After Sorting Names "+Arrays.toString(employees));
        List a = Arrays.stream(employees).sorted((s1,s2)-> { int i=1; return (s1.name.length() > s2.name.length())?1:0;}).collect(Collectors.toList());
        System.out.println(a);
        Set<Employee> y = Arrays.stream(employees).sorted((s1, s2)-> { int i=1; return s1.name.length() < s2.name.length()?1:0;}).collect(Collectors.toSet());
        System.out.println(y);
        Arrays.stream(employees).sorted((s1, s2)-> { int i=1; return s1.name.length()> s2.name.length()?1:0;});
        System.out.println("zzzzz "+Arrays.toString(employees));

        List c = IntStream.range(1,50).boxed().collect(Collectors.toCollection(ArrayList::new));

        c.forEach(System.out::print);


        //Iterator code reduced to one line
        y.forEach((Moveable p) -> p.move("m"));

    }

}

class Employee implements Comparable,Moveable,Likeable{
    String name;

    Employee(String name) {
        this.name = name;
    }

    public static int nameCompare(Employee a1, Employee a2) {
        return a1.name.compareTo(a2.name);
    }

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }


}
