package wtf.g4s8.examples.spaxos;

public interface TxAcceptor<T>  {

    /**
     * Prepare - first phase of accepting value.
     * <p>
     * Acceptor checks if new proposal is greater than
     * minimal proposal stored by acceptor. If yes,
     * acceptor updates minimal proposal number to promise
     * not accept new proposals less than proposed number.
     * Otherwise, acceptor returns accepted proposal number with
     * accepted value.
     * </p>
     */
    void prepare(String txId, Proposal prop, Acceptor.PrepareCallback<T> callback);

    /**
     * Accept - second phase of accepting value.
     * <p>
     * Acceptor accepts proposal equals or greater than minimal
     * proposed value, in that case it updates accepted proposal and
     * accepted value. In any case it returns minimal proposed number
     * as a response.
     */
    void accept(String txId, Proposal prop, T value, Acceptor.AcceptCallback<T> callback);

    /**
     * Callback should be implemented by proposer for asynchronous communication.
     * Callback can be broken to simulate network issues or node failures
     *
     * @param <T>
     */
    interface PrepareCallback<T> {
        /**
         * Acceptor promises to not accept proposals less than proposal in prepare call.
         *
         * @param prop Proposal for prepare call
         */
        void promise(Proposal prop, String metadata);


        /**
         * Rejected for prepare means that acceptor already promised to not accept
         * proposals less than some value.
         *
         */
        void promise(Proposal prop, T val, String metadata);
    }

    interface AcceptCallback<T> {
        void accepted(Proposal prop, T value, String metadata);
    }
}
