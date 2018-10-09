package tr.com.sule;

/**
 * @author scinkir 5.10.2018
 */
public interface Moveable {
    default void move(String name) {
        System.out.println("I'm move "+name);
    }
}
