package wtf.g4s8.examples;


import wtf.g4s8.examples.configuration.Config;
import wtf.g4s8.examples.configuration.TransactionTest;

public class Main {

    public static void main(String[] args) throws Exception {
        Config.nUpdaters = Integer.parseInt(args[0]);
        Config.nReplicas = Integer.parseInt(args[1]);
        Config.paxosProposerTimeOutMilliseconds = 300;
        Config.nTransactions = Config.nUpdaters;
        Config.withDrops = false;
        Config.dropRate = 0;
        Config.withTimeout = false;
        Config.timeoutMilliseconds = 0;
        Config.async = true;
        Config.rmCrashRate = 0;
        Config.syncDelayInSeconds = 3;
        Config.nRetries = 1;
        Config.retryUpdateMinTimeOutInSeconds = 0;
        Config.retryUpdateMaxTimeOutInSeconds = 20;
        new TransactionTest().test();
    }
}
