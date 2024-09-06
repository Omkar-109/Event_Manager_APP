package com.examples.allnewownevent.gig;

import java.util.UUID;

public class UniqueIDProvider<T extends UniqueIDProvider<T>>{
    private final UUID UID;

    public UniqueIDProvider(){
            this.UID = UUID.randomUUID();}

    public final UUID getUID() {
        return UID;
    }

    public final boolean isEqualTo(T o) {
        if (this == o) return true;
        return this.UID.getMostSignificantBits() == o.getUID().getMostSignificantBits() && this.UID.getLeastSignificantBits() == o.getUID().getLeastSignificantBits();
    }
}
