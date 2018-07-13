# Game Character - Multiple Weapons

Modify the Game Character design so that a character may possess an arbitrary number weapons. Refactor the code to reflect this change. Update the JPA annotations and compare the new database structure to the previous structure.

## Solution
Create a one to many relationship between Character and Weapon. That is, the Character class will have a collection of weapons and each Weapon will have a reference to the Character holding it.

In the Character class add the import:

```
import javax.persistence.OneToMany;
```

Then create a field for the weapons:

```
  @OneToMany(mappedBy = "character")
  private List<Weapon> weapons;
```

and add necessary getters and setters. Note that the **mappedBy** is necessary so that JPA knows that this is a bi-directional relationship rather than two separate relationships.

In the Weapon class ad the imports:

```
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;  
```

and add the field for the Character:

```
  @ManyToOne
  @JoinColumn(name = "character")
  private Character character;
```

with necessary getters and setters.

Observe the generated database. The weapon table should now have a foreign key column to the character table.  