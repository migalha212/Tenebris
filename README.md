# LDTS T14G01 - TENEBRIS  

## Game Description

> A character named Dylan Macron finds himself jumping from arena to arena fighting for its life. Each arena is full of monsters of various types and all of them want to kill him.
> Starting out relatively powerless, his experience allows him to get stronger with each fight. He can find weapons spread around the arena. There are 3 types of weapons: Simple shot, Explosion shot, and Death Ray.
> These weapons can level up. Dylan has a limited life per level or per game in the case of the hardest difficulty. Each shot costs an amount of energy which slowly regenerates and can be dropped by the monsters.

This project was developed by Cláudio Meireles (up202306618@up.pt), Dinis Silva (up202306207@up.pt) and Miguel Pereira (up202304387@up.pt).

For a more detailed version of this description click [here](./docs/README.md).

## Implemented Features
- **Main Menu Screen** - a basic menu screen that serves as the main interface when launching the game. Initially, the menu offers options such as New Game, How to Play, Credits, and Exit. However, once the player has at least one saved game, additional options appear: Load Game, Levels, and Statistics. The design dynamically adjusts to include these options when applicable.
- **New Game Menu Screen** - allows players to select their preferred difficulty level. Options include Easy, Normal, Champion and Heartless, each accompanied by a brief description to guide the player. Once a difficulty is selected, the game proceeds to start.
- **How to Play Menu Screen** - provides essential gameplay information, including navigation, controls, objective (of the game), the game basics and details about weapons, enemies, bosses, difficulty levels, and map elements. Navigation instructions are shown on the right, with options to move through the menu or go back.
- **Credits Menu Screen** - a simple text-based screen that highlights the individuals who contributed to the development of the game and the purpose behind its creation. It provides a straightforward way to acknowledge the team. The menu includes a single option to return to the Main Menu, ensuring easy navigation.
- **Screen Resizer** - at the game's launch, the code dynamically detects the user's screen resolution to ensure that the game's proportionality remains consistent, regardless of the screen size. This approach guarantees that the full game window is always displayed without being cropped or cut off, providing an optimal experience across various screen resolutions.
- **Screen Size Locker** - this feature prevents the user from resizing the game window, ensuring the game’s layout and functionality remain intact. If the user attempts to adjust the window size, the code detects this action, closes the current window, and reopens a new one with the predefined dimensions. The new window restores the exact state of the previous one, preserving all content and progress seamlessly.

## Game Screenshots and Mockups



## UML Class Structures and Overview

