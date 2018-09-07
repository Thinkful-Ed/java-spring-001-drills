# Game Character Entities - Create a data layer

Revisit the game character classes from the "Dry Up with Inheritance" drill from the Programming fundamentals in Java module. You can  find the solution to the original drill [here](https://github.com/Thinkful-Ed/java-001-drills/blob/master/dry_up_with_inheritance/README.md).

For this drill:

 - Create a new Spring Boot project and add the classes that were created in the earlier drill.
 - Create a new database named **game** and configure the project to connect to this database. 
 - Annotate the classes as Entities and build the system. 
 - Modify the tests to include the new data layer created.

Use the pgAdmin tool to examine the database schema that was created.


## Solution

### Initialize
Generate a new Spring Boot project with the command:
```
 spring init --build=gradle --java-version=1.8 --dependencies=web,data-jpa,postgresql --packaging=jar --group=com.thinkful.drills --artifact=game --name=GameCharacters game.zip
```

Unzip the *game.zip* file and optionally delete it.


### Copy Existing classes

Copy the classes from the solutions to drill 4.5, including the tests into this project making necessary adjustments to package names.

### Set up database
Using pgAdmin, create a new database named **game** and a user for your database. Then add the database configuration properties to the **application.properties** file.

```
# Details for our datasource
spring.datasource.url = jdbc:postgresql://localhost:5432/game
spring.datasource.username = game_user
spring.datasource.password = game_password

# Hibernate properties
spring.jpa.database-platform = org.hibernate.dialect.PostgreSQL94Dialect
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = create-drop
spring.jpa.hibernate.naming.implicit-strategy = org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl
spring.jpa.properties.hibernate.format_sql=true
```

*At this point you should be able to run `gradle test` and see all the tests passing*

### Create Entities
Add annotations to the various classes. 

- Each entity class needs an **@Entity**.
- Each entity class needs an id field annotated with **@Id** and configured GenerationType
- Each super class needs an **@Inheritance**. For this solution we are using the **single table** inheritance strategy so **@DiscriminatorColumn** is needed for each super class.
- Each sub class needs a **@DiscriminatorValue**

For example,
```
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance
@DiscriminatorColumn(name = "weapon_type")
public class Weapon {
  // declared fields

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  //constructors, getters and setters, methods

}
```

and 

```
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("knife")
public class Knife extends Weapon {

  // declared fields

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
    
  //constructors, getters and setters and other methods
}
```

### Database Schema
With the above configuration two relations should be defined in the database. The SQL should look similar to this:

```
create table character (
       character_race varchar(31) not null,
        id int8 not null,
        description varchar(255),
        health int4 not null,
        name varchar(255),
        strength float8 not null,
        jump_height float8,
        magic_rating float8,
        primary key (id)
    )

    create table weapon (
       weapon_type varchar(31) not null,
        id int8 not null,
        cost float8 not null,
        damage int4 not null,
        name varchar(255),
        strength int4 not null,
        weight float8 not null,
        sharpness float8,
        throwing_distance float8,
        primary key (id)
    )
```
Note the discriminator column in each table.    

### Repositories
Finally add JpaRepository interfaces for the super classes and create tests to ensure that these repositories work as expected.