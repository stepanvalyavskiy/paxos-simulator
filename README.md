# How to build

You need java >= 11 and Maven >= 3. Run:
```bash
mvn package -Passembly
```
to produce assembly jar at `./target`.

# How to run

Run it with `java -jar ./target/paxos-based-strong-consistent-cluster-jar-with-dependencies.jar <nUpdaters> <nReplicas>`,
All args are documented in Config class
`java -jar ./target/spaxos-with-dependencies.jar 2 3`.
