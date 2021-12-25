package fr.umlv.main.back.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.stream.Stream;

@JsonSerialize(using = FrequencySerializer.class)
@JsonDeserialize(using = FrequencyDeserializer.class)
@Embeddable
public record Frequency(int tag, int frequency) implements Serializable {

    enum FrequencyTag implements Serializable {
        NONE(0),
        HOURLY(1),
        DAILY(2),
        WEEKLY(3),
        MONTHLY(4),
        YEARLY(5);

        private final int value;

        FrequencyTag(int value) {
            this.value = value;
        }

        public static FrequencyTag decode(int value) {
            return Stream.of(FrequencyTag.values())
                    .filter(targetEnum -> targetEnum.value == value)
                    .findFirst().orElse(null);
        }

        public int getCode() {
            return value;
        }
    }

    public Frequency() {
        this(0, 0);
    }

     public Frequency {
         if (frequency < 0) {
            throw new IllegalArgumentException("frequency should be positive or equal to 0");
        }
    }
}