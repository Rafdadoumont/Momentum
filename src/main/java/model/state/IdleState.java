package model.state;

public class IdleState implements GameState {
    @Override
    public void start(StateContext context) {
        context.setState(new RunningState());
    }
}