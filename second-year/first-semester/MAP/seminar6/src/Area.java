import java.util.List;

@FunctionalInterface
public interface Area<E> {
    double area(E entity);
    //public static <E> void  printArie(List<E> l, Arie<E> f) {};
}
