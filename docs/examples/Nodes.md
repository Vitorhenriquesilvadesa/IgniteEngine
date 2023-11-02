# Node System Documentation

 - The Node System is a separated project to facility game or software developers make your own application. In this page, you find the full documentation to use Node System. Is a haavy subject to learn, so read carefully.

 <br>
 <br>

# Node Types

- The Node System contains different types of nodes that supply all needs to make logic of your game or application. Basically we starts with primary logic nodes.

## Conditional Nodes

- The Conditional Nodes contains logic expressions to activate one or more Action Nodes, based on the modifier as associated to it. The conditions can be boolean expressions, triggers, or events.

## Action Nodes

- The Action Nodes contains a kind of function that will be called when the correct Conditional Node dispare the output to destination node (Action Node). The actions can be objects interations, change of variables, object creation or destruction, and others.

## Special Conditional Nodes

<br>

### Contidion Modifiers

- Trigger Once: specifies a modification for logic Node, with this, the condition node will active the action node a single time when condition was true.

- Every Tick: contrary of Trigger Once modifier, the action event will be called for each frame during application Runtime.

- Repeat: A variation of Trigger Once modifier, but when the condition node are True, it call the action node N times.

- For: A variation of Every Tick modifier, specifies how many times the Action Event will be called for each frame during application Runtime.

- Foreach: A variation of For modifier, but catch one object type count to determines how many Action Node will be called, returning the object of the current foreach iteration.

### Logic Nodes

<br>

#### The Logic Nodes can change the behavior of Condition Nodes, are simple.

- And
- Or
- Not

### Class Nodes

#### Some times we want create our kind of object types, to this, exists the Class Nodes, they works exactly of classes in any programming language, the unique difference is the simplicity to make class and method using this.

#### The structure of Classes in Ignite Node System is:

- Class Node: to make connection with Variable Node, Condition Node, or Action Node (to constructor of class).

- Attribute Node: the Fields of Class Nodes are represented by Fields, it can to assume types how number, text, boolean or custom component.

- Methods: the methods in Ignite Node System are represented by set of Condition Node and Action Node. We will see some examples to understand this subject in the next parts.