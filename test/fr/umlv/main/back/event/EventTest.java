package fr.umlv.main.back.event;

import fr.umlv.main.DateDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class EventTest {
    @Test
    public void shouldThrowNPECreatedEventWithNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> Event.createEvent(null));
    }

    @Test
    public void shouldThrowNPECreateEventSaveDTOWithNullParameters() {
        Assertions.assertThrows(NullPointerException.class, () -> new EventSaveDTO(null, null, null, "", null));
    }

    @Test
    public void shouldReturnCorrectEventNoPeriodString() {
        var start = new DateDetails(2000, 0, 1, 0, 0);
        var event = Event.createEvent(new EventSaveDTO("hello", start, "no info", null));
        Assertions.assertEquals("hello:20000101T0000:no info", event.toString());
    }

    @Test
    public void shouldReturnCorrectEventWithPeriodString() {
        var start = new DateDetails(2000, 0, 1, 0, 0);
        var end = new DateDetails(2001, 0, 1, 0, 0);
        var event = Event.createEvent(new EventSaveDTO("hello", start, end, "no info", null));
        Assertions.assertEquals("hello:20000101T0000/20010101T0000:no info", event.toString());
    }
}
