# Drill 5.4 Collatz Conjecture

Recall drill 2.5 as follows:

> Consider a sequence of numbers determined as follows: start with any positive integer `n`, if `n` is even then the next term is `n/2`, and if `n` is odd the next term is `3n + 1`.  

> The Collatz conjecture states that if you repeat these steps a sufficient number of times, you will always reach the number 1. 

 > For instance, let us start the sequence with `n = 5`. Since 5 is odd, the next term is `3 * 5 + 1 = 16`. 16 is even so the next term is `16/2 = 8`, 8 is even so the next term is `8/2 = 4` and since 4 is even the next term is `4/2 = 2`, and finally since 2 is even and `2/2 = 1` that ends the sequence. So the sequence that starts with 5 is [5, 16, 8, 4, 2, 1].

 > Write a program that accepts a positive integer and prints the sequence as defined above.

Create a new Spring Boot application with a single controller that implements the Collatz Conjecture program as described. The controller should accept input as a Path Variable and return a simple String result. Provide basic Exception Handling support in your controller.

 # Solution
 Initialize a new Spring application using the Spring CLI or https://start.spring.io. The CLI command is:

 ```bash
 spring init --build=gradle --java-version=1.8 --dependencies=web --packaging=jar --group=com.thinkful.drills --artifact=collatz --name=Collatz collatz.zip
 ```

 This creates a zip file named ~collatz.zip~ in the current directory. Unzip this file using your zip software of choice. You may delete the zip file once extracted.

 The following files are found in the generated project:
 
 ```
 |_gradle/
 |_src/
 |  |_main/
 |  | |_java/
 |  | | |_com/
 |  | |   |_thinkful/
 |  | |     |_drills/
 |  | |       |_collatz/
 |  | |         |_CollatzApplication.java
 |  | |_resources/
 |  |   |_application.properties
 |  |_test/
 |    |_java/
 |      |_com/
 |        |_thinkful/
 |          |_drills/
 |            |_collatz/
 |              |_CollatzApplicationTests.java
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
java -jar build/libs/collatz-0.0.1-SNAPSHOT.jar
```

To get invoke the application use Postman to make a GET request to http://localhost:8080/<n>, where <n> is an integer.

For example, a call to `http://localhost:8080/3` will result in 

```
3 -- 10 -- 5 -- 16 -- 8 -- 4 -- 2 -- 1
```
