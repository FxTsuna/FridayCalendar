package fr.umlv.main.back.event;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import java.io.IOException;

@JsonComponent
public class FrequencySerializer extends JsonSerializer<Frequency> {
    @Override
    public void serialize(Frequency frequency, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeString("frequency");
        jsonGenerator.writeNumber(frequency.frequency());
        jsonGenerator.writeString("tag");
        jsonGenerator.writeNumber(frequency.tag());
        jsonGenerator.writeEndObject();
    }
}
