package org.ignite.core.macros.memory;

import java.util.function.Supplier;

import org.ignite.core.macros.debug.Destructible;

/**
 * The UniquePointer class defines a unique pointer that holds a reference to an
 * object
 * of type T. Unlike RawPointer, UniquePointer ensures exclusive ownership of
 * the referenced object.
 *
 * @param <T> The type of object that this UniquePointer holds a reference to.
 */
public class UniquePointer<T extends Destructible> extends RawPointer<T> {

    public UniquePointer() {
        super(() -> null);
    }

    /**
     * Constructs a new UniquePointer with lazy initialization using a Supplier.
     *
     * @param lazyInitializer The Supplier used for lazy initialization of the
     *                        referenced object.
     */
    public UniquePointer(Supplier<T> lazyInitializer) {
        super(lazyInitializer);
    }

    /**
     * Constructs a new UniquePointer with a direct reference to an object.
     *
     * @param ref The object to be referenced by this UniquePointer.
     */
    public UniquePointer(T ref) {
        super(ref);
    }

    /**
     * Releases ownership of the referenced object, setting the reference to null.
     * The referenced object will be eligible for garbage collection.
     */
    public void release() {
        this.getReference().destroy();
        super.setReference(null);
    }

    /**
     * Transfers ownership from another UniquePointer to this UniquePointer.
     * The other UniquePointer will be left in an invalid state after the transfer.
     *
     * @param other The UniquePointer from which ownership will be transferred.
     */
    public void transferOwnership(UniquePointer<T> other) {
        this.setReference(other.getReference());
        other.release();
    }

    public void reset(T ref) {

        if (this.getReference() != null) {
            this.getReference().destroy();
            this.setReference(null);
        }
        this.setReference(ref);
    }
}
