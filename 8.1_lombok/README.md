# Drill 8.1 Lombok
Refactor the codefrom the Game Character project in drill 7.1 to use Lombok.

## Solution
Starting with the drill 7.1 code edit the *build.gradle** file and add the Lombok dependency.

```
...
dependencies {
  ...
  	compileOnly('org.projectlombok:lombok')
  ...  
}
```

Next, to each of the entity classes add necesary Lombok annotations and remove getters and setters.