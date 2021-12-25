package fr.umlv.main.back.user;

import fr.umlv.main.back.event.Event;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * This class is responsible of representing an user and its characteristic
 */
@Entity(name = "USER_DB")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Event> events;

	/**
	 * Contructs an Event according to the specified username and password
	 *
	 * @param username the specified username
	 * @param password the specified password
	 *
	 * @throws NullPointerException if one of the specified argument is null
	 */
    public User(String username, String password)  {
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
    }

    public User() {
    }

	/**
	 * Set the a new password according to the specified password
	 *
	 * @param password the specified password
	 *
	 * @throws NullPointerException if the specified argument is null
	 */
    public void setPassword(String password) {
        this.password = Objects.requireNonNull(password);
    }

	/**
	 * Retrieve the id of the user
	 *
	 * @return the id of the user
	 */
    public UUID getId() {
        return id;
    }

	/**
	 * Retrieve the username of the user
	 *
	 * @return the id of the user
	 */
    public String getUsername() {
        return username;
    }

	/**
	 * Retrieve the hashed password of the user
	 *
	 * @return the hashed password of the user
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		var messages = new StringJoiner(",\n", "{\n", "}");
		messages.add("username=" + username);
		messages.add("password=" + password);
		return messages.toString();
	}
}
