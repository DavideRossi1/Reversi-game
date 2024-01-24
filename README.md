
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

The project consists in implementing a game, practicing Continuous integration and using automated tests and Test Driven
Development to grow the code.

## TODO List

- [x] Define the tests for TTD
    - [x] test the board
        - [x] **TODO**: add a test to be sure that the board is able to flip lines in more than one direction if it's
          necessary
    - [x] Test the aspects related to player:
        - [x] Is the program handling correctly a human player?
        - [x] Is the program handling correctly the computer player?
    - [x] test the mechanics of the game
- [x] Implement the base game in terminal
    - [x] Define the rules
    - [x] Define the board
    - [x] Define the player and the computer mechanics
    - [x] Check the end of the game
    - [x] Check the validity of the moves
    - [x] Update the score ad each move (and print maybe?)
- [ ] **_IMPORTANT_**: Since I had some external libraries, I had to check credits and licenses for them.
    - [ ]  JUnit 5 --> Eclipse Public License 2.0
    - [ ]  Mockito --> MIT License
    - [ ]  Swing --> GNU General Public License v2.0
    - [ ]  openJDK --> GNU General Public License v2.0
- [ ] Add the README
- [ ] (Optional) Implement the game in a GUI

## Terminal Game

### How to run the game:

1. Clone the repo
2. Open the terminal and go to the folder where you cloned the repo
3. Run the command:

  ```
  ./gradlew terminal:run --console=plain
  ```

Otherwise, you can obtain the `.jar`  file with:

```
./gradlew terminal:dist
```

which will create the `.jar` file in the folder `/readyToDistribute`. Then you can `cd` into the folder and run the
command:

```
java -jar ReversiGame-terminal-1.0.jar
```

Note that the `./gradlew clean` command will take care of destroying the `.jar` file and the
folder `/readyToDistribute`. 


## Troubleshooting

Apparently gradle in some case ignores the `targetCompatibility` and `sourceCompatibility` settings in the `build.gradle`.
We have tested the project with `Java 21`. 

If your Gradle wrapper is not working, check the version of Gradle you are using with `./gradlew -v` and make sure it is
recent enough.

- A workaround in Unix systems is to set the `JAVA_HOME` environment variable to the path of the JDK you want to use.
for example: `export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64`.
- The other workaround we tested is to use a container with the correct version of Java. We've tested the [amazoncorretto:21.0.2](https://hub.docker.com/_/amazoncorretto).  Note that with the containerized solution you will need to download gradle and libraries every time you run the container.
    - to run directly the game:
        ```
        podman run -v$PWD:/reversi -w /reversi -it amazoncorretto:21.0.2 /bin/bash -c "./gradlew terminal:run --console=plain"
        ```
    - to obtain the `.jar` file:
        ```
        podman run -v$PWD:/reversi -w /reversi amazoncorretto:21.0.2 /bin/bash -c "./gradlew terminal:dist"
        ```
    - to run the `.jar` file:
        ```
        podman run -v$PWD:/reversi -w /reversi -it amazoncorretto:21.0.2 /bin/bash -c "cd readyToDistribute && java -jar ReversiGame-terminal-1.0.jar"
        ```
    - to build the project:
        ```
      podman run -v$PWD:/reversi -w /reversi amazoncorretto:21.0.2 /bin/bash -c "./gradlew build"
        ```
    - to run the tests:
        ```
        podman run -v$PWD:/reversi -w /reversi amazoncorretto:21.0.2 /bin/bash -c "./gradlew test"
        ```
    - to clean the project:
        ```
        podman run -v$PWD:/reversi -w /reversi amazoncorretto:21.0.2 /bin/bash -c "./gradlew clean"
        ```
    Those commands work also with `docker` instead of `podman`.