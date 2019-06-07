CS 3500 - Object Oriented Design - Summer 1 2019

John Lam and David Zhang

We decided to implement our model for our animator project using a few different classes and interfaces.

#### IModel
The IModel interface is used to represent the logic for animating different shapes. It guarantees that any implementing classes will be able to provide the following functions:
1. Returning a String representation of the animation
2. Adding motions via its parameters (as long as the motions don't conflict with each other - specific details below)
3. Adding shapes via its parameters (as long as no shape with the same id exists)
4. Removing a shape from the model given its id
5. Returning a new *read-only* list of the model's updated shapes by giving it an non-negative integer tick
value

#### IModelImpl
The IModelImpl class implements the IModel interface described above. It stores a list of shapes that represents what is currently being drawn/animated. Each individual shape will contain the information required for it to be animated, while the model class itself just contains the shapes with its state as what it was initialized as. All methods that the IModel interface requires are fully implemented, with the exception of the last method listed above. We are awaiting details on how the tweening function will work before we can properly update the model's shapes with a tick value. We will most likely end up delegating the details of updating the shapes to the shape class itself, where it would return a new read-only shape with the updated fields. Consequently, the controller and/or view would receive a new list of read-only shapes every tick they query for.

#### IReadOnlyShape
The IReadOnlyShape interface represents a shape for the model, but only provides methods that can read from the shape. In other words, it provides getter methods that return the values that the shape holds. It provides no other methods than the aforementioned getter methods, and provides no means to mutate the shape. This read-only shape will be passed into the controller and the view, so that they can read information from this shape, but not write any information to it.

#### IModelShape
The IModelShape interface also represents a shape for the model. It extends the IReadOnlyShape interface, meaning that it will provide all the functionalities required to read from a shape, but in addition to those, also provides methods that print a String representation of the shape, gets the state of the shape at a given tick, and adds motions to this shape. The model itself will interact with IModelShapes, and be able to receive more specific output and add motions in, while the controller and view will only be able to read from the same shapes as IReadOnlyShapes.

#### IModelShapeImpl and ShapeType
The IModelShapeImpl class implements the IModelShape interface above, and its purpose is to store data about the shape (and its characteristics). It implements all methods that IModelShape requires, except for getting this shape at a given tick (because we are waiting for details on the tweening function). All of its fields are final, and *cannot be mutated* (as in cannot be set to different values/references).

This class has a field of type ShapeType, which is an enum that represents what type of shape this instance of the class represents. At the moment, there are only two choices: Rectangle and Ellipse, but more types of shapes can easily be added to ShapeType. The ShapeType in a given shape will determine how that shape will be depicted on in the view. ShapeType also offers a public method that returns a string representation of itself (ex: "rectangle").

This class also has contains a list of motions (motions specified below), which represent the states of this shape at any two given "start" and "end" ticks. It enforces the invariant that this list will always be sorted by tick (i.e. in chronological order) by inserting incoming motions in order. It also enforces that any two adjacent motions (defined as two motions in the same list/same shape that have the same ending and starting tick values, with respect to their positions in the list) will have the same ending and starting states, with respect to their positions in the list. In other words, the motion that comes first in the list should have the same ending state as the starting state of the motion that comes *immediately after* it in the list, and vice versa. Additionally, it assumes and enforces that there are no overlapping intervals between any two motions in this list. In the future, if it is the case that motions can overlap as long as they perform different actions (ex: x-y movement vs color changing), we will enforce those rules, but for now, we disallow any overlapping motions based on tick. If there are gaps (the previous motion's end tick is not the same as the following motion's start tick) in the list, it is assumed that no changes/motions/animations occur between those two ticks. If there are gaps between motions, they will not be printed and will be omitted from the String representation of this shape. Consequently, our interpretation of this means that a shape could teleport, assuming that there is a gap in between. As details for birth and death of shapes have not been specified either, we have left it so that a shape is always on the screen until removed from the model's list.

#### IMotion
The IMotion interface provides various getter methods that retrieve information from this motion, and also provides a method that returns a string representation of this motion. An implementation of this interface represents the start state and end state of an animation at an arbitrary start and end tick (although the end tick cannot be before the start tick).

#### IMotionImpl
The IMotionImpl class implements the IMotion interface above, and implements most of its methods. Currently, it does not implement the methods related to the animation itself (as in methods that return values at a given tick), as we need more details about the tweening function before doing so. It holds the start and end state of shapes through individual fields. It does not hold a shape itself as we thought that Shapes should have knowledge of motions, but not the other way around. All of the fields in the IMotionImpl class are final, and thus *cannot be mutated*.

#### Misc.
More minute details of the implementation (rules for constructing a shape or model, when smaller exceptions are thrown, how processes are coded, etc) are documented appropriately in the actual code.
