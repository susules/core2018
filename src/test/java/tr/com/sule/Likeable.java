package tr.com.sule;

/**
 * @author scinkir 5.10.2018
 */
public interface Likeable extends Moveable{
    default void move(String name) {
        System.out.println("I'm Like "+name);
    }
}
