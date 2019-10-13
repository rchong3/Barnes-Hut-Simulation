# Barnes-Hut-Simulation

Animated simulation of n bodies using the Barnes-Hut algorithm based on data from .txt files.

Each .txt file starts with an integer representing the number of bodies on the first line.
The second line gives the simulated "universe's" radius.
After that, each line contains x and y coordinates in meters, initial x and y velocities
in meters per second, and masses of each body, in that order.

To see the simulation in action, compile and run Galaxy.java. Then, enter a rate of calculation and the name of the file you wish to take data from and simulate.
Recommended rate of calculation (milliseconds per frame): 1-10

N-body systems taken from:
https://www.cs.princeton.edu/courses/archive/spr17/cos126/assignments/nbody.html
