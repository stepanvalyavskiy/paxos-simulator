package wtf.g4s8.examples.spaxos;


import java.util.Random;

public class TimeoutAcceptor<T> implements Acceptor<T> {
    private static final Random RNG = new Random();

    private final int toms;
    private final Acceptor<T> acc;

    public TimeoutAcceptor(int toms, Acceptor<T> acc) {
        this.toms = toms;
        this.acc = acc;
    }

    @Override
    public T state() {
        return acc.state();
    }

    @Override
    public void prepare(Proposal prop, PrepareCallback<T> callback) {
        sleep();
        this.acc.prepare(prop, new PrepareCallback<T>() {
            @Override
            public void promise(Proposal prop, String metadata) {
                sleep();
                callback.promise(prop, metadata);
            }

            @Override
            public void promise(Proposal prop, T val, String metadata) {
                sleep();
                callback.promise(prop, val, metadata);
            }
        });
    }

    @Override
    public void accept(Proposal prop, T value, AcceptCallback<T> callback) {
        sleep();
        this.acc.accept(prop, value, new AcceptCallback<T>() {
            @Override
            public void accepted(Proposal prop, T value, String metadata) {
                sleep();
                callback.accepted(prop, value, metadata);
            }
        });
    }

    private void sleep() {
        try {
            Thread.sleep(RNG.nextInt(toms));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
