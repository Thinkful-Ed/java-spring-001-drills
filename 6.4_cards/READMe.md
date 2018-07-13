# Drill 6.4: Playing Cards

Revisit the Playing Card exercise from drill 4.2. A Deck contains 52 playing cards. Create a new Spring Boot project with a Deck class and a Card class add JPA annotations such that there is a one to many relationship between Deck and Cards. Create a database named **cards** with a database user and connect your application to this database. Examine the generated database schema.

## Solution
Generate a new Spring Boot project with the command:
```
 spring init --build=gradle --java-version=1.8 --dependencies=web,data-jpa,postgresql --packaging=jar --group=com.thinkful.drills --artifact=card --name=Cards card.zip
```

Copy the Card and Deck classes into this project.

Create the two way association, that is the Deck has a List of Cards while each card has a single Deck.