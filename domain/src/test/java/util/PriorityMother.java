package util;

import com.test.valueObject.Priority;

public final class PriorityMother {
    public static Priority create(Integer value) {
        return Priority.create(value);
    }

    public static Priority random() {
        final int[] possibleValues = { 0, 1, 2, 3 };
        return Priority.create(possibleValues[(int) (Math.random() * possibleValues.length)]);
    }
}
