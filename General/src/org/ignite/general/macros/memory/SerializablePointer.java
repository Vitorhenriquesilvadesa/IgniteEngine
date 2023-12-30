package org.ignite.general.macros.memory;

/**
 * File name: SerializablePointer.java
 * Creator: Lord Vtko
 * Creation Date: July 21, 2023
 * Version 1.0.0
 *
 * Description: This class defines a SerializablePointer that can be used as a pointer
 *              to a serializable object. It provides methods for obtaining references,
 *              creating copies, and other operations related to manipulating the referenced object.
 *              The class extends the Pointer class and implements the Serializable interface.
 *
 * Copyright (c) 2023 Lord Vtko. All rights reserved.
 *
 * License: Free to use, but give credits for creator.
 */

import java.io.*;
import java.util.function.Supplier;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * A SerializablePointer is a type of pointer that can hold a reference to a
 * Serializable object.
 * It extends the Pointer class and implements the Serializable interface,
 * allowing it to be serialized and deserialized.
 * The SerializablePointer can lazily initialize its reference using a Supplier
 * or directly with an object of type T.
 * It also provides methods for copying the referenced object and its fields.
 *
 * @param <T> The type of object that this SerializablePointer holds a reference
 *            to, which must implement the Serializable interface.
 */
public class SerializablePointer<T extends Serializable> extends Pointer<T> implements Serializable {

    /**
     * Constructs a new SerializablePointer with lazy initialization using a
     * Supplier.
     *
     * @param lazyInitializer The Supplier used for lazy initialization of the
     *                        referenced object.
     */
    public SerializablePointer(Supplier<T> lazyInitializer) {
        super(lazyInitializer);
    }

    /**
     * Constructs a new SerializablePointer with a direct reference to an object.
     *
     * @param ref The object to be referenced by this SerializablePointer.
     */
    public SerializablePointer(T ref) {
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
     * @param newRef The new object to be referenced by this SerializablePointer.
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

        T newObject;
        try {
            newObject = (super.getReference() != null)
                    ? (T) super.getReference().getClass().getDeclaredConstructor().newInstance()
                    : null;

            copyFields(super.getReference(), newObject);
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

    /**
     * Custom serialization method to write the SerializablePointer to an
     * ObjectOutputStream.
     *
     * @param out The ObjectOutputStream to which the SerializablePointer will be
     *            written.
     * @throws IOException If an I/O error occurs during the serialization process.
     */
    public void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * Custom deserialization method to read the SerializablePointer from an
     * ObjectInputStream.
     *
     * @param in The ObjectInputStream from which the SerializablePointer will be
     *           read.
     * @throws IOException            If an I/O error occurs during the
     *                                deserialization process.
     * @throws ClassNotFoundException If the class of the SerializablePointer or its
     *                                components cannot be found.
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    @Override
    public void modifyAttribute(String attributeName, Object value) {
        throw new UnsupportedOperationException("Unimplemented method 'modifyAttribute'");
    }
}
