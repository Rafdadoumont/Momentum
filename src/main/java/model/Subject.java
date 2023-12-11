package model;

public interface Subject {
    void addObserver(Observer observer, GameEventEnum event);
    void notifyObservers(GameEventEnum event);
}
