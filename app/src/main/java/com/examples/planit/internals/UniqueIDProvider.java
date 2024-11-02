package com.examples.planit.internals;

import java.util.UUID;

/**
 * Provides a unique identifier (UUID) for objects that extend this class.
 *
 * @param <T> the type of the subclass extending this class
 * @author Ram Amoncar
 * @author Omkar Mhamal
 * @version 1.0
 */
public class UniqueIDProvider<T extends UniqueIDProvider<T>> {
    private UUID UID;

    /**
     * Constructs a UniqueIDProvider with a randomly generated UUID.
     */
    public UniqueIDProvider() {
        this.UID = UUID.randomUUID();
    }

    /**
     * Constructs a UniqueIDProvider with the specified UUID as a string.
     *
     * @param uid the string representation of the UUID
     */
    public UniqueIDProvider(String uid) {
        this.UID = UUID.fromString(uid);
    }

    /**
     * Gets the UUID of this object.
     *
     * @return the UUID of this object
     */
    public final UUID getUID() {
        return UID;
    }

    /**
     * Sets the UUID of this object.
     *
     * @param uid the UUID to set
     */
    public final void setUID(UUID uid) {
        this.UID = uid;
    }

    /**
     * Checks if this object is equal to another UniqueIDProvider object based on their UUIDs.
     *
     * @param o the object to compare to
     * @return true if both objects have the same UUID, false otherwise
     */
    public final boolean isEqualTo(T o) {
        if (this == o) return true;
        return this.UID.getMostSignificantBits() == o.getUID().getMostSignificantBits() &&
                this.UID.getLeastSignificantBits() == o.getUID().getLeastSignificantBits();
    }
}
