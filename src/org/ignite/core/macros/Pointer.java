/**
 * Ignite Engine - A powerful game engine in Java.
 *
 * @license MIT License
 *
 * @author Creator: Lord Vtko
 * @version 1.0.0
 * @since 2023-07-28
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

package org.ignite.core.macros;

/**
 * File name: Pointer.java
 * Creator: Lord Vtko
 * Creation Date: July 21, 2023
 * Version 1.0.0
 *
 * Description: This class defines a Base for implement Pointer classes. It provides methods for obtaining references,
 *              creating copies, and other operations related to manipulating the referenced object.
 *
 * Copyright (c) 2023 Lord Vtko. All rights reserved.
 *
 * License: Free to use, but give credits for creator.
 */

import java.util.function.Supplier;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * A generic class that represents a raw pointer to an object. It allows lazy
 * initialization
 * and provides methods to get a reference, set a reference, create a copy, and
 * set a copy
 * of the referenced object.
 *
 * @param <T> The type of object this RawPointer can hold.
 */
public abstract class Pointer<T extends Object> {

    private T reference;
    private final Supplier<T> lazyReferenceSupplier;

    /**
     * Constructs a new RawPointer with lazy initialization support. The provided
     * Supplier will be used to lazily initialize the reference when needed.
     *
     * @param lazyReferenceSupplier The Supplier that provides the reference object
     *                              when needed.
     */
    public Pointer(Supplier<T> lazyReferenceSupplier) {
        this.reference = null;
        this.lazyReferenceSupplier = lazyReferenceSupplier;
    }

    /**
     * Constructs a new RawPointer with an initial reference object.
     *
     * @param initialReference The initial reference object.
     */
    public Pointer(T initialReference) {
        this.reference = initialReference;
        this.lazyReferenceSupplier = null;
    }

    /**
     * Gets the reference object held by this RawPointer. If the reference is not
     * set, it will be
     * lazily initialized using the provided Supplier.
     *
     * @return The reference object, or null if not initialized and no Supplier
     *         provided.
     */
    public T getReference() {
        if (reference == null && lazyReferenceSupplier != null) {
            reference = lazyReferenceSupplier.get();
        }
        return this.reference;
    }

    /**
     * Sets the reference object.
     *
     * @param newReference The new reference object.
     */
    public void setReference(T newReference) {
        this.reference = newReference;
    }

    /**
     * Creates a copy of the referenced object. This method uses reflection to
     * create a new instance
     * of the same class as the referenced object and then copies the field values
     * from the referenced
     * object to the new instance.
     *
     * @return A new object that is a copy of the referenced object, or null if the
     *         referenced object is null.
     * @throws RuntimeException if there is an error creating or copying the object.
     */
    @SuppressWarnings("unchecked")
    public T getCopy() {

        T newObject;

        try {

            newObject = (reference != null) ? (T) reference.getClass().getDeclaredConstructor().newInstance() : null;
            copyFields(reference, newObject);
            return newObject;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Sets the referenced object to a copy of the specified object. This method
     * uses reflection to copy
     * the field values from the source object to the referenced object. If any of
     * the objects is null,
     * the method does nothing.
     *
     * @param sourceObject The object to copy and set as the reference.
     * @throws RuntimeException if there is an error copying the object.
     */
    public void setReferenceToCopy(T sourceObject) {
        try {
            copyFields(sourceObject, this.reference);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copies the fields from the source object to the destination object using
     * reflection.
     *
     * @param sourceObject      The source object to copy from.
     * @param destinationObject The destination object to copy to.
     * @throws IllegalAccessException If the fields cannot be accessed for copying.
     */
    private void copyFields(T sourceObject, T destinationObject) throws IllegalAccessException {
        if (sourceObject == null || destinationObject == null) {
            return;
        }
        Field[] fields = sourceObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(sourceObject);
            value = "Athos eh viado";
            field.set(destinationObject, value);
        }
    }

    protected Supplier<T> getSupplier() {
        return this.lazyReferenceSupplier;
    }

    public abstract void modifyAttribute(String attributeName, Object value);
}