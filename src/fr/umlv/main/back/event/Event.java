package fr.umlv.main.back.event;

import fr.umlv.main.DateDetails;
import fr.umlv.main.back.user.User;

import javax.persistence.*;
import java.util.*;

@Entity(name = "EVENT_DB")
public class Event {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private Date dateStart;

    @Column(nullable = false)
    private Date dateEnd;

    @Column(nullable = false)
    private String info;

    @ManyToOne
    private User user;

    public Event() {
    }

    private Event(Date dateStart, Date dateEnd, String info) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.info = info;
    }

    private static Date createDateFromDateDetails(DateDetails details) {
        var calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(details.year(), details.month(), details.day(), details.hour(), details.minute());
        return calendar.getTime();
    }

    public static Event createEvent(EventSaveDTO eventDetails) {
        Objects.requireNonNull(eventDetails);
        var start = createDateFromDateDetails(eventDetails.dateStart());
        var end = createDateFromDateDetails(eventDetails.dateEnd());
        return new Event(start, end, eventDetails.info());
    }

    public UUID getId() {
        return id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public String getInfo() {
        return info;
    }

    public void setDateStart(DateDetails dateStartDetails) {
        this.dateStart = createDateFromDateDetails(dateStartDetails);
    }

    public void setDateEnd(DateDetails dateEndDetails) {
        this.dateEnd = createDateFromDateDetails(dateEndDetails);
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        var messages = new StringJoiner(",\n", "Event {\n", "}");
        messages.add("id=" + id);
        messages.add("starting date=" + dateStart.toString());
        messages.add("ending date=" + dateEnd.toString());
        messages.add("informations=" + info);
        return messages.toString();
    }
}