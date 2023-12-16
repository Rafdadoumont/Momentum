package model;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SingleThreadExecutor implements Executor {
    private final Executor executor;

    public SingleThreadExecutor() {
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
