# **LDTS T14G01 - TENEBRIS**

## **Game Description**

> A character named Dylan Macron finds himself jumping from arena to arena fighting for its life. Each arena is full of monsters of various types and all of them want to kill him.
> Starting out relatively powerless, his experience allows him to get stronger with each fight. He can find weapons spread around the arena. There are 3 types of weapons: Simple shot, Explosion shot, and Death Ray.
> These weapons can level up. Dylan has a limited life per level or per game in the case of the hardest difficulty. Each shot costs an amount of energy which slowly regenerates and can be dropped by the monsters.
This project was developed by Cláudio Meireles (up202306618@up.pt), Dinis Silva (up202306207@up.pt) and Miguel Pereira (up202304387@up.pt).

## **Features**

### Already Implemented
    
#### Main Menu
First menu of the game. It will show up when opening the game and from here you can select one of the following options:

- **New Game** : Allows you to create a new game and select the difficulty.
- **Load Game** : Allows you to access and continue a previously saved game session.
- **Levels** : This option just appears if you have already some saved game session. It is used to select one of the unlocked levels to play next.
- **Settings** : Allows you to personalize various aspects of the game experience.
- **How to Play** : Explains you how you can play the game.
- **Credits** : Shows the credits of the game 
- **Exit** : Exits game.

#### Pause Menu

This menu can be opened while in-game. It lets you pause your game and gives you some options such as:

- **Continue** : Unpauses the game.
  
- **Restart Level** : Reloads the current level.
  
- **Statistics** : A menu that displays informations such as kills, deaths, levels completed, etc. of the current session.
  
- **Back to Main Menu** : Goes to Main Menu.

#### Difficulties
- **Easy**:     The player’s HP resets at the beginning of every arena; 
            The player has a bigger starting Health Pool;
            Every monster drops a bit of energy, energy regenerates at a normal level.	

- **Normal**:   The player’s HP resets at the beginning of every arena;
            Every monster drops a bit of energy, energy regeneration slightly reduzed.

- **Champion**: The player's HP resets at the beginning of every level;
            Only bigger monsters drop energy.

- **Heartless**: Champion difficulty level;
            If you die, game over!


### Planned to be Implemented

#### Attributes of entities (main character and monsters)

- **Health Points** (HP) : the amount of damage an Entity can take before dying.
- **Damage** (DMG) : the amount of HP that will be taken away from an Entity in the interaction.
- **Energy** (EN) : a resource the Player uses to fire his weapons (Main Character only).

#### Map Elements
- **Standard Wall**:    Has collision all around;
                    No Entity can pass through;
                    Bullets can’t pass through.

- **Destructible Wall/Crates**: Has HP, and when it’s HP is depleted it is destroyed, otherwise acts like a standard wall.

- **Spike** :           Same as a Standard wall but deals some amount of Damage to Entities that collide with it.
            
- **Sand Bag** :        Has collision all around;
                    No Entity can pass through;
                    Bullets go over it.
        
#### Dylan Macron (Main Character)

- **Starting Health Pool** :    150 hp for easy difficulty;
                            100 hp for all others.

- **Starting Energy Pool** :    100 en for every difficulty;
    
- **Starting Energy Regen** :   1 en per second for easy;
                            0,5 en per second for hard;

#### Weapons Available
- **Simple Weapon** :       Fire Rate - 2 bullets per second;
                        Energy Cost to Shot - 10 en per bullet;
                        Damage - 5 hp;

- **Explosion Weapon** :    Fire Rate - 0,25 bullets per second;
                        Energy Cost to shot - 25 en per bullet;
                        Ability - when reaches a wall or a Monster it deals damage to all enemies in a radius of 4 tiles;
                        Damage - (15 - 3,75 * radius) hp.

- **Death Ray (Laser)** :   Energy Cost to shot - 20en per second active;
                        Ability - gives damage to all enemies in that direction;
                        Max Laser duration - 5 seconds;
                        Cooldown - 1 minute;
                        Damage - 20 hp / sec.

#### Monsters
- **Tenebris Peon**:          Health Points : 20 hp; 
                          Speed : Normal;
                          Damage : 15 hp;
                          Range : 1 tile.

- **Tenebris Heavy**:         Health Points : 50 hp;
                          Speed : Slow;       
                          Damage : 10 hp;
                          Range : 1 tile.
            
- **Tenebris Spiked Scout**:  Health Points : 15 hp;
                          Speed : Fast;
                          Damage : 35 hp;     
                          Range : 1 tile.

- **Tenebris Harbinger** :    Health Points : 20 hp;
                          Speed : Normal;
                          Damage: 35hp;
                          Range : 5 tiles.

- **Tenebris Warden** :       Health Points : 75 hp;     
                          Speed : Normal;
                          Damage : 45 hp;
                          Range : 1 tile.

#### Bosses

- **Black Sun Emissary**
  - **Description**: An ancient being empowered by the power of the Black Sun. The Archon is a master of dark energy, unleashing beams of corrupting power and summoning tentacles from below to trap players.
  - **Arena Type**: Boss sits on the top half of the screen (immobile ASCII art) with a clean walkable arena below it.
  - **Attack Patters**: In certain intervals, coordinates around the arena will light up in warning, showing the Pattern for the coming attack; 
                                After another interval if the player is standing in any of the highlighted spots, he will take heavy Damage.
  - **How to damage**: The Boss sits above the player, so simply shooting upwards will hit the Boss and reduce its HP.

- **Abyssal Archon**
  - **Description**: An ancient being empowered by the depths of the Abyss. The Archon is a master of dark energy, unleashing the power of Abyssal Bulbs and his armies of Abissal Tenebris Warriors, be careful not to destroy it’s gems or it’s full rage will be awakened and destroy the players where they stand.
  - **Arena Type**: a clean walkable arena with the boss sat in an unreachable spot in the middle, 2 other spots will be covered by an impassable yet destructible object.
  - **Attack Patterns**: Divided in 2 phases, 1 that sends out enemies to protect the 2 spots, and another where the boss itself attacks with a set of attacks predefined.
  - **How to damage**: at first the Boss is invulnerable this phase 1, only when both “Abyssal Bulbs” are destroyed will the boss become vulnerable, this also marks the beginning of the boss’ phase 2.
  - **“Abyssal Bulb”**: the mentioned impassable yet destructible object, has HP, marks phase 1 of the Abyssal Archon, deals no damage to the player upon colision

- **Nightmare Sovereign**
  - **Description**: A malevolent ruler who twists reality around itself, bringing players into a nightmare realm. The Sovereign manipulates illusions, creating clones and warping the arena to confuse players.
  - **Arena Type**: the only Boss(of the original 3) to have his battle happen through different rooms in the arena, each with an experience for the player be it a maze, a bullet hell, enemy gauntlet, or another boss’ room.
  - **Attack Patterns**: Each room will mean different attacks, only when face to face with the boss is a pattern defined, though the rooms and teleporting is also an atack from the boss.
  - **How to damage**: beat the challenge room the boss teleports you into, or when face to face with it, attacking it directly.

### Design Patterns


###  Unit tests
