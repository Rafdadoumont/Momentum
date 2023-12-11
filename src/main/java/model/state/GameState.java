package model.state;

import model.exceptions.StateException;

public interface GameState {
    default void start(StateContext context) throws StateException {
        throw new StateException("Could not start game. State: " + context.getState());
    }

    default void finish(StateContext context) throws StateException {
        throw new StateException("Could not finish game. State: " + context.getState());
    }

    default void finishEarly(StateContext context) throws StateException {
        throw new StateException("Could not finish game early. State: " + context.getState());
    }

    default void reset(StateContext context) throws StateException {
        throw new StateException("Could not reset game. State: " + context.getState());
    }
}