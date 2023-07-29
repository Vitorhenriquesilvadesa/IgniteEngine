# Ignite Engine

Ignite Engine is an early-stage game engine developed in Java using the LWJGL (Lightweight Java Game Library).

## References
- The Cherno Game Engine Series
- Mathematics for 3D Game Programming and Computer Graphics
- Game Engine Architecture
- Game Programming Patterns-Genever Benning
- Real-Time Rendering

## Main Features

**12/07/2023**
- Callbacks for key presses.
- Mouse events.
- Window property changes.

**13/07/2023**
- Creation of the generic class for GUI Layers.

**15/07/2023**
- Creation of the specific class for Layers implementing JImGui.

**16/07/2023**
- Handling of events on the ImGuiLayer class.

**26/07/2023**
- Creation of a class for pointer manipulation.
- Files: Pointer.java (abstract); RawPointer.java; SerializablePointer.java;

## Requirements

- Java Development Kit (JDK) 8 or higher installed.
- LWJGL installed.

## Setup

1. Clone the Ignite Engine repository.
2. Import the project into your preferred IDE.
3. Make sure the LWJGL dependencies are configured correctly.
4. Compile and run the project.

## Download
You can download the latest version of the Ignite Engine JAR file from the [Release Page](https://github.com/Vitorhenriquesilvadesa/Java/tree/main/IgniteEngine/releases).

# Future Implementations

## Node System

The Ignite Engine features a Node system that allows users to create games without dealing with complicated code. With this system, you can take advantage of the following features:

### Trigger Events

Trigger events are activated only once when a certain condition becomes true. This allows you to create specific events for key moments in your game. For example:

* Player OnCollisionWith(Enemy) -> Player.life -= 1

### Tick Events

Tick events occur continuously while a specific condition is true. These events are useful for actions that need to occur at regular intervals or during certain game states.

### Custom Class Creation or Procedural Programming

With the Node system, you can create custom classes or use procedural programming to add logic and behavior to game objects.

### Animations and Sprite Editing

The Ignite Engine provides features for handling animations and sprite editing, allowing you to bring your game to life with smooth movements and attractive visuals.