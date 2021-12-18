package fr.umlv.main.back.event;

import org.springframework.context.annotation.Bean;

import java.util.Objects;

public record Frequences(FrequenceTag tag, int frequency) {
    enum FrequenceTag {
        NONE,
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

     public Frequences {
         Objects.requireNonNull(tag);
         if (frequency < 0) {
            throw new IllegalArgumentException("frequency should be positive");
        }
    }
}