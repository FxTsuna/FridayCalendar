package fr.umlv.main.back.event;
import fr.umlv.main.DateDetails;

import java.util.Objects;

/**
 * This class is responsible of containing the information transmitted during a
 * request mapping
 */
public record EventSaveDTO(String title, DateDetails dateStart, DateDetails dateEnd, String info, Frequency frequency) {

	/**
	 * Compact constructor that verify the validity of the arguments
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public EventSaveDTO {
        Objects.requireNonNull(title);
        Objects.requireNonNull(dateStart);
        Objects.requireNonNull(dateEnd);
        Objects.requireNonNull(info);
		Objects.requireNonNull(frequency);
    }

	/**
	 * Contructs the object with a default ending date (dateEnd field)
	 *
	 * @throws NullPointerException if one the specified argument is null
	 */
    public EventSaveDTO(String title, DateDetails dateStart, String info, Frequency frequency) {
        this(title, dateStart, dateStart, info, frequency);
    }
}
