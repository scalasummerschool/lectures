[![License: CC BY-NC 4.0](https://licensebuttons.net/l/by-nc/4.0/80x15.png)](https://creativecommons.org/licenses/by-nc/4.0/)

# Scala Summer School Content
Here you find the lectures and exercises for the [Scala Summer School](http://scalasummerschool.github.io). It is still Work In Progress (WIP) and only a subset of all lectures and exercises are available.

## Preparations
### Mandatory
Before you can start you have to install the following tools:
 1. JDK 8 or newer (I think the latest stable version is 10)
 2. [Scala](https://www.scala-lang.org/download/)
 3. [SBT](https://www.scala-sbt.org/download.html) - it is the build-tool we will use
 
 You have to install it in that order because each tool relies on the tools installed before.
 
 ### Optional
  - [Ammonite](http://ammonite.io/#Ammonite-REPL) - this is an enhanced REPL which makes it easier to fiddle around with some code. But you don't need it. Scala comes already with a REPL. But it isn't that great :).
 
Also make sure you compiled the material **before** the workshop takes place. SBT needs to download "the internet" on its first start and this may take some time.

```bash
cd <path to this repo>
sbt "compile"
```

## SBT
Here, you find a list of useful SBT commands:

```bash
# start SBT in the repo root and keep it running
sbt

# show all sub-projects
sbt> projects

# switch to a sub-project
sbt> project <name of sub-project>

# compile
sbt> compile

# compile on every file change
sbt> ~compile

# run test
sbt> test

# run a certain test
sbt> test:testOnly <name of test>
```
