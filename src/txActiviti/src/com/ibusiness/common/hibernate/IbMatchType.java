package com.ibusiness.common.hibernate;

/**
 * MatchType.
 * 
 * @author Lingo
 */
public enum IbMatchType {
    /** equals. */
    EQ,
    /** like. */
    LIKE,
    /** less than. */
    LT,
    /** greater than. */
    GT,
    /** less equals. */
    LE,
    /** greater equals. */
    GE,
    /** in. */
    IN,
    /** unknown. */
    UNKNOWN;
}
