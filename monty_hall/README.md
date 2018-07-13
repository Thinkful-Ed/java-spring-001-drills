# Monty Hall Simulator

Recall drill 2.11 as follows:

> The Monty Hall problem is a famous probability brain teaser based on a game show hosted by Monty Hall. You can read more details here; https://en.wikipedia.org/wiki/Monty_Hall_problem. The basic premise is the player is presented with 3 doors. Behind one of them is a grand prize like a car, and behind the other 2 doors are joke prizes like goats. The player picks a door, say door number 1, but before revealing what's behind it, the host — who knows which door has the grand prize —  will open one of the other doors, say door number 2, revealing a goat. The player then has an opportunity to keep their original door or switch to the other unopened door, door number 3. The question is this: is there an advantage to switching the door?

> Write a program that will simulate the game and run your simulation a number of times keeping track of how many times the player wins by switching doors. Calculate the probability of winning by switching doors. Run your simulation 100 times, 1,000 times, and 10,000 times, and calculate the probability each time. Does the answer change as the number of simulations increase?

Create a new Spring Boot application with a single controller that implements the Monty Hall Simulator program as described. The controller should accept input as Path Variables and return a simple String result. Provide basic Exception Handling support in your controller. Provide tests for your solution.

## Solution
Initialize a new Spring Boot project with [Spring Initializr](https://start.spring.io) or with the CLI using the following command:

```
spring init --build=gradle --java-version=1.8 --dependencies=web --packaging=jar --group=com.thinkful.drills --artifact=monty --name=Monty monty.zip
```

This creates a file named *monty.zip* in your current working directory. Unzip that file and optionally delete it. The following directory structure is created:

```
 |_gradle/
 |_src/
 |  |_main/
 |  | |_java/
 |  | | |_com/
 |  | |   |_thinkful/
 |  | |     |_drills/
 |  | |       |_monty/
 |  | |         |_MontyApplication.java
 |  | |_resources/
 |  |   |_application.properties
 |  |_test/
 |    |_java/
 |      |_com/
 |        |_thinkful/
 |          |_drills/
 |            |_monty/
 |              |_MontyApplicationTests.java
 |_.gitignore
 |_build.gradle
 |_gradlew
 |_gradlew.bat
 |_settings.gradle              
```
## Running the Application
First build the project.

```
gradle build
```

Then either:

```
gradle bootRun
```

or

```
java -jar build/libs/monty-0.0.1-SNAPSHOT.jar
```

## How the solution works
This program accepts two optional parameters:

- n the number of times to run the simulation, defaults to 1000
- change true - always change, false - never change

To invoke the application use Postman to make a GET request to http://localhost:8080/<n>/<change>.

For example, a call to `http://localhost:8080/100/false` will result in 

```
After 100 games, if player does not change, player wins 33 times and looses 67 times with a probability of 0.33 of winning
```
## Testing
Two test files are provided, the *MontyApplicationTests.java* provides a smoke test to ensure
that the code runs within the environment and *MontyControllerTests.java* unit tests the 
controller itself.

To run the tests:

```
gradle test
```