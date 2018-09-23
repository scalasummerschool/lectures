[![License: CC BY-NC 4.0](https://licensebuttons.net/l/by-nc/4.0/80x15.png)](https://creativecommons.org/licenses/by-nc/4.0/)

# Scala Summer School Content
Here you find the lectures and exercises for the [Scala Summer School](http://scalasummerschool.github.io). It is still Work In Progress (WIP) and only a subset of all lectures and exercises are available.

## Table of Contents
 1. [Introduction](https://scalasummerschool.github.io/lectures/introduction)
 2. [Scala 101](https://scalasummerschool.github.io/lectures/lecture1_scala_101)
 3. [Functional Programming 101](https://scalasummerschool.github.io/lectures/lecture2_fp_101)
 4. [Standard Library](https://scalasummerschool.github.io/lectures/lecture3_std_lib)
 5. [Type Classes](https://scalasummerschool.github.io/lectures/lecture4_typeclasses_101)
 6. [Type Class Incarnations](https://scalasummerschool.github.io/lectures/lecture5_typeclasses_incarnations)
 
All lectures are based on [reveal.js]. Therefore, to navigate use your arrow keys. Chapters are organized in columns, slides in rows. To get an overview of all slides within a lecture press ESC.

## Preparations
### Mandatory
Before you can start you have to install the following tools:
 1. JDK 8 or newer (I think the latest stable version is 10)
 2. [Scala](https://www.scala-lang.org/download/)
 3. [SBT](https://www.scala-sbt.org/download.html) - it is the build-tool we will use
 4. `git clone` this repository
 
 You have to install it in that order because each tool relies on the tools installed before.
 
 ### Optional
  - [Ammonite](http://ammonite.io/#Ammonite-REPL) - this is an enhanced REPL which makes it easier to fiddle around with some code. But you don't need it. Scala comes already with a REPL. But it isn't that great :).

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

If you want some more information take a look into the [introduction slides](https://scalasummerschool.github.io/lectures/introduction/).

## Build the Lectures
To build a lecture go through the following steps:

```bash
$> cd /path/to/repo
$> sbt
# select one of the shown lecture
sbt> project
sbt> project scala101-lecture

# minimum JS optimizations
sbt> fastCompile

# maximum JS optimizations (Github page)
sbt> fullCompile
sbt> exit

# opens lecture in default browser
$> open lecture1_scala_101/index.html
```

## License
As stated in the [License file](https://github.com/scalasummerschool/lectures/blob/master/LICENSE) all lecture slides are provided under Creative Commons BY-NC 4.0. The exercise code is released under a MIT license.

The following people are authors of the content in this repository:
 - Paul Heymann
 - David Krentzlin
 - Christian Stein
 - Florian Sachse
