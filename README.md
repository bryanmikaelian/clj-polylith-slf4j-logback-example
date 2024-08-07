# clj-polylith-slf4j-logback-example
A small demo with Clojure and Polylith showing how to configure SLF4J + Logback


## Background

This polylith workspace has one base: `server`. This base has logging configured with the following libraries:

- `io.pedestal/pedestal.log`
- `org.slf4j/slf4j-api`
- `ch.qos.logback/logback-classic`

It also configures a `development` project and `server` project that generates a `jar` artifact.

In the development project, logback will use `resources/logback-test.xml` but in the jar file, we will use `bases/server/resources/logback.xml`

The most important take away from all this:

**logback.xml must at the top of the resources folder. It cannot be nested under something like bases/server/resources/server/logback.xml`


## Requirements

- Clojure 1.11
- Java 21 (although it probably works with other LTS versions)
- A working REPL (this project used nrepl + conjure)


## Demo

### JAR File

Using `clojure.tools.build`, we can generate a `jar` file like so


```
clj -T:build uberjar :project server
```

You should see the following output:

```
Compiling com.example.server.core...
Building uberjar target/server.jar...
Uberjar is built.
```

Run the jar like so and note how the `l/debug` statement is not printed based [on our configuration](https://github.com/bryanmikaelian/clj-polylith-slf4j-logback-example/blob/main/bases/server/resources/logback.xml#L9)

```
java -jar projects/server/target/server.jar
````

```
INFO  com.example.server.core - {:msg "hello. I show up in production.", :line 10}
ERROR com.example.server.core - {:msg "hello. I show up in production.", :line 11}
WARN  com.example.server.core - {:msg "hello. I show up in production.", :line 12}
```


### REPL
Start a repl

```
clj -A:dev
```

You should see the following log output immediately. Note the `DEBUG` statement because our [development configuration allows for debug logs](https://github.com/bryanmikaelian/clj-polylith-slf4j-logback-example/blob/main/development/resources/logback-test.xml)

```
INFO  com.example.server.core - {:msg "hello. I show up in production.", :line 10}
ERROR com.example.server.core - {:msg "hello. I show up in production.", :line 11}
WARN  com.example.server.core - {:msg "hello. I show up in production.", :line 12}
DEBUG com.example.server.core - {:msg "hello. I show up in development.", :line 13}
```

