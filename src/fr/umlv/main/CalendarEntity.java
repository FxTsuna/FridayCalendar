package fr.umlv.main;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Calendar")
public class CalendarEntity {
    @Column(nullable = false)
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String pwd;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private CalendarEntity date;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private CalendarEntity heure;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private CalendarEntity info;

    public CalendarEntity getInfo() {
        return info;
    }

    public CalendarEntity getHeure() {
        return heure;
    }

    public CalendarEntity getDate() {
        return date;
    }

    public String getPwd() {
        return pwd;
    }

    public UUID getId() {
        return id;
    }


}
