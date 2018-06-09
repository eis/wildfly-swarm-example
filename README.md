Simple Wildfly Swarm app
========================

Inspired by my other example app, https://github.com/eis/spring-boot-example.

Answers to http://localhost:8080/ (GET) and http://localhost:8080/add (POST, async).

This branch is to demonstrate issue here: https://github.com/eis/wildfly-swarm-example/issues/2

To reproduce the problem, run

`mvn clean wildfly-swarm:run -Dmaven.repo.local=/tmp/maven-temp-repo-whatever`

And you should observe

```
org.jboss.modules.xml.XmlPullParserException: Failed to resolve artifact 'xalan:serializer:2.7.1.jbossorg-4'
(position: END_TAG seen ...esources>\n    <artifact name="xalan:serializer:2.7.1.jbossorg-4"/>... @5:57)
```

then, by adding

```
<execution>
  <goals>
    <goal>package</goal>
  </goals>
</execution>
```

to swarm plugin and retrying the issue goes away.