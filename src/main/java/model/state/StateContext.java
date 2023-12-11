package model.state;

public class StateContext {
    private GameState state;

    public StateContext() {
         state = new IdleState();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}