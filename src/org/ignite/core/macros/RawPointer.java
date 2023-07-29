package org.ignite.core.macros;

/**
 * File name: RawPointer.java
 * Creator: Lord Vtko
 * Creation Date: July 21, 2023
 * Version 1.0.0
 *
 * Description: This class defines a Basic Pointer. It provides methods for obtaining references,
 *              creating copies, and other operations related to manipulating the referenced object.
 *              The class extends the Pointer class.
 *
 * Copyright (c) 2023 Lord Vtko. All rights reserved.
 *
 * License: Free to use, but give credits for creator.
 */

import java.util.function.Supplier;
import java.lang.reflect.Field;

/**
 * The RawPointer class defines a pointer that can hold a reference to an object
 * of type T.
 * It extends the Pointer class, providing methods for obtaining references,
 * creating copies,
 * and other operations related to manipulating the referenced object.
 *
 * @param <T> The type of object that this RawPointer holds a reference to.
 */
public class RawPointer<T extends Object> extends Pointer<T> {

    /**
     * Constructs a new RawPointer with lazy initialization using a Supplier.
     *
     * @param lazyInitializer The Supplier used for lazy initialization of the
     *                        referenced object.
     */
    public RawPointer(Supplier<T> lazyInitializer) {
        super(lazyInitializer);
    }

    /**
     * Constructs a new RawPointer with a direct reference to an object.
     *
     * @param ref The object to be referenced by this RawPointer.
     */
    public RawPointer(T ref) {
        super(ref);
    }

    /**
     * Retrieves the referenced object. If the reference is null and a Supplier is
     * provided,
     * the reference is lazily initialized before being returned.
     *
     * @return The referenced object of type T.
     */
    public T getReference() {
        if (super.getReference() == null && super.getSupplier() != null) {
            super.setReference(getSupplier().get());
        }
        return super.getReference();
    }

    /**
     * Sets the reference to a new object.
     *
     * @param newRef The new object to be referenced by this RawPointer.
     */
    public void setReference(T newRef) {
        super.setReference(newRef);
    }

    /**
     * Sets the reference to a copy of the provided object by copying its fields.
     * If any of the fields cannot be accessed, an IllegalAccessException is thrown
     * and printed to the error stream.
     *
     * @param newRef The object from which the fields will be copied to the
     *               referenced object.
     */
    public void setReferenceToCopy(T newRef) {
        try {
            copyFields(newRef, super.getReference());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a copy of the referenced object and returns it.
     * If the referenced object is null, this method returns null.
     * If any error occurs during the copy process, an exception is caught, printed
     * to the error stream, and null is returned.
     *
     * @return A new object that is a copy of the referenced object, or null if the
     *         referenced object is null or an error occurs.
     */
    @SuppressWarnings("unchecked")
    public T getCopy() {
        try {
            T newObject = (super.getReference() != null)
                    ? (T) super.getReference().getClass().getDeclaredConstructor().newInstance()
                    : null;

            copyFields(super.getReference(), newObject);
            return newObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Copies the fields of the source object to the destination object using
     * reflection.
     * If either the source or destination object is null, the method does nothing.
     *
     * @param source      The object from which the fields will be copied.
     * @param destination The object to which the fields will be copied.
     * @throws IllegalAccessException If any field cannot be accessed during the
     *                                copy process.
     */
    private void copyFields(T source, T destination) throws IllegalAccessException {
        if (source == null || destination == null) {
            return;
        }
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);
            field.set(destination, value);
        }
    }
}
