package model.state;

public class RunningState implements GameState {
    @Override
    public void finish(StateContext context) {
        context.setState(new FinishedState());
    }

    @Override
    public void finishEarly(StateContext context) {
        context.setState(new FinishedEarlyState());
    }
}