package fr.umlv.main.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/event/add")
    public ResponseEntity<EventResponseDTO> addEvent(@RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        return eventService.addEvent(event);
    }

    @DeleteMapping("/event/delete")
    public ResponseEntity<EventResponseDTO> removeEvent(@RequestBody EventCredentialDTO event) {
        Objects.requireNonNull(event);
        return eventService.removeEvent(event.id(), event.user());
    }

    @PutMapping("/event/update/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable UUID id,
            @RequestBody EventSaveDTO event) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(id);
        return eventService.updateEvent(id, event);
    }

    @GetMapping("/event/getAll")
    public ResponseEntity<List<EventResponseDTO>> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/event/get/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable UUID id) {
        Objects.requireNonNull(id);
        return eventService.getEventById(id);
    }
}
