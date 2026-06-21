package com.test.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.domain.DomainTestConfig;
import com.test.domain.util.PriorityMother;

@SpringBootTest(classes = DomainTestConfig.class)
public class PriorityTest {
    @Test
    void testPriorityShouldNotNull() {
        // Given
        Priority priority = PriorityMother.random();

        // Then
        assertNotNull(priority);
        assertNotNull(priority.getValue());
    }

    @Test
    void testPriorityShouldThrowExceptionWhenValueIsNull() {
        // Given ~ Priority value will be null

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            Priority.create(null);
        });

        // Then
        assertEquals("Priority value cannot be null", illegalArgumentException.getMessage());
    }

    @Test
    void testPriorityShouldThrowExceptionWhenValueIsNegative() {
        // Given ~ Priority value will be negative

        // When
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            Priority.create(-1);
        });

        // Then
        assertEquals("Priority value cannot be negative", illegalArgumentException.getMessage());
    }
}
