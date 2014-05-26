package gr.teicm.mp.thefmanager.controllers.history;

public interface IHistory<E> {
    void add(E item);

    E back();
    E forward();

    boolean hasBack();
    boolean hasCurrent();
    boolean hasForward();
}
