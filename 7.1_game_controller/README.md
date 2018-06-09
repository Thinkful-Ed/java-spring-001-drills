# Drill 7.1 Game Character Controllers
Refactor the Game Character project from drill 6.3 to include Controllers for Characters and Weapons. Provide a complete set of CRUD operations with basic validation where possible. Write tests for your controllers.

## Solution
Starting from the code in drill 6.3 add a new **CharacterController** class.

```
@RestController
@RequestMapping("/api/characters")
public class CharacterController {

  @Autowired
  CharacterRepository repository;

}
```

The *@RestController* annotation allows Spring to mount this classes methods as endpoints. The *@RequestMapping* annotation sets the base endpoint for all controller methods. Finally the Character Repository created previously is injected to be used by the controller methods.

Create GET, POST, PUT and DELETE controllers.

Add tests for each controller.

To run the tests:

```
gradle test
```
