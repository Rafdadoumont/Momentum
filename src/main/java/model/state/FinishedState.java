package model.state;

public class FinishedState implements GameState{
    @Override
    public void reset(StateContext context) {
        context.setState(new IdleState());
    }
}