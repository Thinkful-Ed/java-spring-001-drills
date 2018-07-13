# Drill 6.2 Game Character - Composition

Consider the Game Character exercise again. Suppose each character may possess a single weapon. Modify the code to implement this feature. How can that association be expressed in JPA and what table structure would be produced in the database? Refactor teh JPA annotations and examine the database schema produced.

## Solution
- Add an instance variable for a weapon to the Character class and add necessry getter and setter
- Annotate the field with **@ManyToOne** and **@JoinColumn**