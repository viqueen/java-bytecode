[![CircleCI](https://circleci.com/gh/viqueen/java-bytecode.svg?style=svg)](https://circleci.com/gh/viqueen/java-bytecode)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=viqueen_java-bytecode&metric=alert_status)](https://sonarcloud.io/dashboard?id=viqueen_java-bytecode)

## Java Bytecode

I am reviving my old messy Java decompiler that I started writing back when boredom kicked
in at university (a decade or so ago ?)

It was working fine, but obviously, adjusting it to accommodate the latest `ClassFile` format is 
just ... uff ... no way.

This is a rewrite of [Java-Bytecode-Analysis](https://github.com/viqueen/Java-Bytecode-Analysis) and everything
that is wrong with it ... so basically ... everything.

### gotchas

<details>
<summary>setting up sonar</summary>
<p>

need to setup a new profile in ~/.m2/settings.xml

```xml
<profile>
    <id>java-bytecode-sonar</id>
    <properties>
        <sonar.projectKey></sonar.projectKey>
        <sonar.moduleKey>${project.artifactId}</sonar.moduleKey>
        <sonar.organization></sonar.organization>
        <sonar.host.url>https://sonarcloud.io</sonar.host.url>
        <sonar.login></sonar.login>
    </properties>
</profile>
```

and publish report by running

```bash
mvn verify sonar:sonar -P java-bytecode-sonar
```

</p>
</details>