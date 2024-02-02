
![Static Badge](https://img.shields.io/badge/Java-r?logo=oracle&color=darkred)&nbsp;
![Static Badge](https://img.shields.io/badge/gradle-gradle?logo=gradle&color=%2302303A)&nbsp;
![Static Badge](https://img.shields.io/badge/Apache%20Groovy-groovy?logo=Apache%20Groovy&logoColor=white&color=%234298B8
)&nbsp;
![Static Badge](https://img.shields.io/badge/JUnit5-JUnit5?logo=JUnit5&logoColor=white&color=%2325A162)&nbsp;
![Static Badge](https://img.shields.io/badge/Mockito-Mockito?logo=Mockito&logoColor=white&color=%23FFC837)&nbsp;
![Static Badge](https://img.shields.io/badge/OpenJDK-OpenJDK?logo=OpenJDK&logoColor=white&color=%23437291)

[//]: # (![Static Badge]&#40;https://img.shields.io/badge/Swing-Swing?logo=Java&logoColor=white&color=%23E60012&#41;&nbsp;)

![Static Badge](https://img.shields.io/badge/CircleCI%20stautus:-circleci?logo=circleci&color=%23343434)
[![CircleCI](https://dl.circleci.com/status-badge/img/circleci/Nnx7eettKAjZjgLqohmuHD/82KDGxEoAw8hcLPNtEJmox/tree/main.svg?style=svg&circle-token=c19e80ed0f5747a0dd7dc3d7f326b2ff245cd5cf)](https://dl.circleci.com/status-badge/redirect/circleci/Nnx7eettKAjZjgLqohmuHD/82KDGxEoAw8hcLPNtEJmox/tree/main)&nbsp;


# Reversi-game

This repo was made for the "Software Development Methods" exam at the University of Trieste that I took during my
studies in Data Science and Scientific Computing.

The project assignment consists in implementing a game, practicing Continuous integration and using automated tests and Test Driven
Development to grow the code, and using the Agile methodology to manage the project in a group work setting (The project was done with [Davide Rossi](https://github.com/DavideRossi1)).

The implemented game is the classic [Reversi](https://en.wikipedia.org/wiki/Reversi) game, also known as Othello.


The project is built with [Gradle](https://gradle.org/), and programed in `Java 21`.

## Project structure

The project is divided into tree main modules:

- `core`: contains the game logic and the model of the game and serves as a library for the other two modules.
- `terminal`: contains the terminal version of the game.
- `desktop`: contains the desktop version of the game.

## Requirements

The project requires `Java 21` to be installed on your system. check the [troubleshooting](#troubleshooting) section for more information.

Gradle is not required to be installed on your system, as the project uses the gradle wrapper.


## How to run the project

The easiest way to run the project is to download the latest release from the [releases page](not available yet) and run the `reversi.jar` file:

```
$ java -jar reversi.jar
```

Since the project is built with gradle, you can also rely on the gradle wrapper to build, test and run the project from the source code.  In particular, for each module listed [above](#project-structure), you can run the following commands:

- `./gradlew <terminal|desktop>:run`: to play the game.
- `./gradlew <module>:build`: to build the module.
- `./gradlew <module>:test`: to run the tests of the module.
- `./gradlew <module>:dist`: to create a distributable package of the module in the `./readyToDistribute` directory.

Moreover, you can run: 
- `./gradlew test` to run all the tests of the project.
- `./gradlew build` to build the whole project.
- `./gradlew dist` to create a distributable package of both the terminal and the desktop version of the game in the `./readyToDistribute` directory.
- `./gradlew clean` to clean the project.

Just for example, to run the graphical version of the game, you can run the following command:

```
$ ./gradlew desktop:run
```

*Note*: We suggest to run the terminal version of the game with `./gradlew terminal:run --console=plain` to avoid some issues with the gradle output.



## Troubleshooting

#### 1. How to check the version of Java installed on your system:
    
This project strictly requires `Java 21` to be installed on your system. Be sure that the output of the `java --version` command is something like: 
```
$ java --version
openjdk 21.0.2 2024-01-16
OpenJDK Runtime Environment (Red_Hat-21.0.2.0.13-1) (build 21.0.2+13)
OpenJDK 64-Bit Server VM (Red_Hat-21.0.2.0.13-1) (build 21.0.2+13, mixed mode, sharing)
```
#### 2. Gradle wrapper and Java 21:

Apparently in some contitions the gradle wrapper ignores the `targetCompatibility` and `sourceCompatibility` settings in the `build.gradle` when more than one version of Java is installed on the system.

At least for Unix systems (we didn't have the chance to test it on Windows), a workaround is to manually set the `JAVA_HOME` environment variable: 
```
$ export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```
where `/usr/lib/jvm/java-21-openjdk-amd64` is the path to the JDK 21 installation on your system.

#### 3. Wayland and xwayland

If you are a linux user and use [`wayland`](https://wayland.freedesktop.org/) as display server, to run the desktop version of the application you will need [`xwayland`](https://wayland.freedesktop.org/xserver.html) installed on your system.


## Credits and attributions

The complete list of all the credits, attributions and licenses can be found in the file [`ATTRIBUTIONS.md`](./ATTRIBUTIONS.md). file. 


## TODO 

- [ ] Add the content to the releases page.
- [ ] Add the link to the releases page.
- [ ] Add a file which explains the technical details of the implementation. (I've seen that in other exam projects, so I think it's a good idea to add it here too).
- [ ] Think about the transition from ArrayList to Array in the previousStep context.
- [ ] Create the javaDoc for the project (again, not requested, but I think it's a good idea to have it).