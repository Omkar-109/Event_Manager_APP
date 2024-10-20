package com.examples.planit.internals;

import java.util.UUID;

public class UniqueIDProvider<T extends UniqueIDProvider<T>> {
    private UUID UID;

    public UniqueIDProvider() {
        this.UID = UUID.randomUUID();
    }

    public UniqueIDProvider(String uid) {
        this.UID = UUID.fromString(uid);
    }

    public final UUID getUID() {
        return UID;
    }

    public final void setUID(UUID uid){
        this.UID = uid;
    }

    public final boolean isEqualTo(T o) {
        if (this == o) return true;
        return this.UID.getMostSignificantBits() == o.getUID().getMostSignificantBits() && this.UID.getLeastSignificantBits() == o.getUID().getLeastSignificantBits();
    }
}
