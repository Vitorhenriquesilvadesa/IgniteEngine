package org.ignite.core.macros.memory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import org.ignite.core.macros.debug.Destructible;
import org.ignite.system.meta.Define;

/**
 * The SharedPointer class defines a shared pointer that holds a reference to an
 * object
 * of type T. Shared pointers allow multiple pointers to share ownership of the
 * referenced
 * object and automatically manage the object's lifetime.
 *
 * @param <T> The type of object that this SharedPointer holds a reference to.
 */

@Define("IGNITE_API")
public class SharedPointer<T extends Destructible> extends RawPointer<T> {

    private final AtomicInteger refCount;

    public SharedPointer() {
        super(() -> null);
        this.refCount = new AtomicInteger(1); // Initialize reference count to 1
    }

    /**
     * Constructs a new SharedPointer with lazy initialization using a Supplier.
     *
     * @param lazyInitializer The Supplier used for lazy initialization of the
     *                        referenced object.
     */
    public SharedPointer(Supplier<T> lazyInitializer) {
        super(lazyInitializer);
        this.refCount = new AtomicInteger(1); // Initialize reference count to 1
    }

    /**
     * Constructs a new SharedPointer with a direct reference to an object.
     *
     * @param ref The object to be referenced by this SharedPointer.
     */
    public SharedPointer(T ref) {
        super(ref);
        this.refCount = new AtomicInteger(1); // Initialize reference count to 1
    }

    /**
     * Copy constructor for creating a new SharedPointer that shares ownership with
     * another
     * SharedPointer.
     *
     * @param other The SharedPointer to share ownership with.
     */
    public SharedPointer(SharedPointer<T> other) {
        super(other.getReference());
        this.refCount = other.refCount;
        this.refCount.incrementAndGet(); // Increment the reference count
    }

    /**
     * Decrements the reference count and releases ownership of the referenced
     * object
     * when the reference count reaches zero. The referenced object will be eligible
     * for
     * garbage collection.
     */
    public void release() {
        if (refCount.decrementAndGet() == 0) {
            super.getReference().destroy();
            super.setReference(null);
        }
    }

    /**
     * Creates a copy of the SharedPointer. The newly created SharedPointer shares
     * ownership of the same object with the original SharedPointer.
     *
     * @return A new SharedPointer that shares ownership of the same object.
     */
    public SharedPointer<T> copy() {
        refCount.incrementAndGet(); // Increment the reference count
        return new SharedPointer<>(this);
    }

    /**
     * Transfers ownership from another SharedPointer to this SharedPointer.
     * The other SharedPointer will be left in an invalid state after the transfer.
     *
     * @param other The SharedPointer from which ownership will be transferred.
     */
    public void transferOwnership(SharedPointer<T> other) {
        this.release(); // Release ownership of the current object
        super.setReference(other.getReference());
        this.refCount.set(other.refCount.get()); // Transfer reference count
    }

    public void reset(T ref) {

        if (this.getReference() != null) {
            this.getReference().destroy();
            this.setReference(null);
        }
        this.setReference(ref);
    }
}
