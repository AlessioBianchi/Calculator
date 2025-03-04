package observer;

public interface Subject {
    void registerObserver (Observer o);
    @SuppressWarnings("unused")
    void unregisterObserver (Observer o);
    void notifyObservers ();
}
