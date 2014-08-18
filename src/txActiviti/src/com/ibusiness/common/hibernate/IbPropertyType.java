package com.ibusiness.common.hibernate;

import java.util.Date;

/**
 * property type.
 * 
 * @author Lingo
 */
public enum IbPropertyType {
    /** String. */
    S(String.class),
    /** Integer. */
    I(Integer.class),
    /** Long. */
    L(Long.class),
    /** Double. */
    N(Double.class),
    /** Date. */
    D(Date.class),
    /** Boolean. */
    B(Boolean.class);
    /** class. */
    private Class<?> clazz;

    /**
     * constructor.
     * 
     * @param clazz
     *            Class
     */
    private IbPropertyType(Class<?> clazz) {
        this.clazz = clazz;
    }

    /** @return get value. */
    public Class<?> getValue() {
        return clazz;
    }
}
