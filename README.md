# FRC2019 Code

This is Team 1701's code for FIRST Deep Space. The code is written in java using WPILIB and compiled using GradleRIO, a gradle library for FRC.

All basic constants and princable classes are found in the root directory. All other classes are divided by function and status. Each part of the robot has its own [subsystem](/src/main/java/com/team1701/frc2019/subsystems) found in the root directory.


## Getting Started

### Gradle

Here is our not-so-famous 2-Step Setup~~®~~ (three, if you count IDE setup):

* Clone this repo, or download as a zip
* Run `./gradlew` to download WPILIB and other frc dependencies
* Run `./gradlew` tasks for more commands

#### IntelliJ

* Run `./gradlew idea`
* Open `2019RobotCode.ipr` with IntelliJ

### Building/Deploying

* Run `./gradlew build` to build the target, and download any new dependencies.
* Run `./gradlew build deploy` to build the target and deploy it to the roboRIO.
* Run `./gradlew --offline build deploy` to build the target and deploy it to the roboRIO *without checking for updates*. This is very useful at competition.

## Code Features

* Gradle

We moved to GradleRIO, which is a Gradle plugin designed for FRC. It includes WPILib, CTRE Toolsuite, and can grab other libraries quickly.

## Contact

* There is no guarantee of assistance, but if you need help, file an issue.
* This project is licensed under a 3-Clause BSD license (SPDX identifier BSD-3-Clause).
