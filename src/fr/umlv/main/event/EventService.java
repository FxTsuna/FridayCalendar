package fr.umlv.main.event;

import fr.umlv.main.event.Event;
import fr.umlv.main.event.EventRepo;
import fr.umlv.main.event.EventResponseDTO;
import fr.umlv.main.user.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepository;

    public ResponseEntity<EventResponseDTO> addEvent(String date, String heure, String info) {
        var event = new Event(date, heure, info);
        var createdEvent = eventRepository.save(event);
        return ResponseEntity
                .created(URI.create("event/save/" + createdEvent.getId()))
                .body(new EventResponseDTO(createdEvent.getId()));
    }

    public void updateEvent(UUID uid, Event event) {
        eventRepository.save(event);
    }

    public ResponseEntity<?> removeEvent(UUID id, User userId) {
        var event = eventRepository.findById(id);
        if (event.isEmpty()) return ResponseEntity.notFound().build();
        eventRepository.delete(event.get());
        return ResponseEntity.ok().build();
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}