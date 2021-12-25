package fr.umlv.main.back.event;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class FrequencyDeserializer extends JsonDeserializer<Frequency> {
    @Override
    public Frequency deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        var node = deserializationContext.readTree(jsonParser);
        var tag = node.get("tag").traverse().getIntValue();
        var frequency = node.get("frequency").traverse().getIntValue();
        return new Frequency(tag, frequency);
    }
}
