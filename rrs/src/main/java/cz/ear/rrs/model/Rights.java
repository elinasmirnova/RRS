package cz.ear.rrs.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum Rights {
    ADMIN(0), TIER1(1), TIER2(2), TIER3(3), TIER4(4);

//    @Enumerated(value= EnumType.STRING)
    private final int right;

    Rights(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return Integer.toString(right);
    }
}
