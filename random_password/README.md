# Drill 5.6 Random Password Generator

Recall drill 2.7 as follows:

> Random password generator: write a program that generates a password using a combination of letters, digits and the punctuations !,@,#,$,%,*,? and _. A password must follow these rules:

>    1. passwords are between 6 and 70 characters long inclusively
>    2. passwords must have at least one upper case letter and at least one lower case letter
>    3. passwords must contain at least one digit and one punctuation

Create a new Spring Boot application using either the Spring Initializr or the Spring Boot CLI. Create a single controller to implement the random password generator. Provide appropriate exception handling and tests for your code.

## Solution
Initialize a new Spring Boot project with [Spring Initializr](https://start.spring.io) or with the CLI using the following command:

```
spring init --build=gradle --java-version=1.8 --dependencies=web --packaging=jar --group=com.thinkful.drills --artifact=randompassword --name=RandomPassword randompassword.zip
```

This creates a file named *randompassword.zip* in your current working directory. Unzip that file and optionally delete it. The following directory structure is created:

```
|_gradle/
|_src/
|  |_main/
|  | |_java/
|  | | |_com/
|  | |   |_thinkful/
|  | |     |_drills/
|  | |       |_randompassword/
|  | |         |_RandomPasswordApplication.java
|  | |_resources/
|  |   |_application.properties
|  |_test/
|    |_java/
|      |_com/
|        |_thinkful/
|          |_drills/
|            |_randompassword/
|              |_RandomPasswordApplicationTests.java
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
java -jar build/libs/randompassword-0.0.1-SNAPSHOT.jar
```

## How the solution works
This program relies on random numbers and uses the *java.util.Random* class to generate random integers in a range where necessary.

It also relies on the ASCII table where valid characaters fall within 33 and 126. Within that range:

 - digits are from 48 to 57
 - upper case letters are from 65 to 90 and
 - lower case letters are from 97 to 122

All other values are punctuations.

To ensure that at least one uppercase letter, one lowercase letter, one punctuation and one digit is found one of each of those groups are randomly generated first. Next the rest of the password is randomly generated. Finally the first 4 characters are shuffled into the array to ensure randomness. That is, the first character of the password should not always be an uppercase letter.

## Testing
Two test files are provided, the *RandomPasswordApplicationTests.java* provides a smoke test to ensure
that the code runs within the environment and *RandomPasswordControllerTests.java* unit tests the
controller itself.

To run the tests:

```
gradle test
```
