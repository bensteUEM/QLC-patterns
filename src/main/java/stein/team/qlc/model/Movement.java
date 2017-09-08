package stein.team.qlc.model;

/**
 * The type of movement
 * either from Left to right
 * right to left
 * picking a random
 * doing something with all at the same time
 * or doing ping pong (from left to right then right to left ...)
 */
public enum Movement {
    LEFTtoRIGHT, RIGHTtoLEFT, RANDOM, ALL, PINGPONG
    // LEFTtoRIGHT & RIGHTtoLEFT => QLC Run Order Loop + Backward / Forward
    // ALL = no chaser - scene only?
    // PINGPONG => QLC Run Order PingPong
}

