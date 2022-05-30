package jsondeserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.io.IOException;
import java.time.LocalTime;

public class JsonDeserializeTimeTickets extends LocalTimeDeserializer {
    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.isExpectedStartArrayToken()) {
            return parseArray(parser, context);
        }
        return super.deserialize(parser, context);
    }

    private LocalTime parseArray(JsonParser parser, DeserializationContext context)
            throws IOException {
        int hour = getNextValue(parser);
        int minute = getNextValue(parser);
        if (parser.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new JsonMappingException(parser, "Not an array!");
        }
        if (parser.nextToken() != JsonToken.END_ARRAY) {
            throw new JsonMappingException(parser, "after LocalTime ints");
        }
        return LocalTime.of(hour, minute);
     }

    private int getNextValue(JsonParser jp) throws IOException {
        jp.nextToken();
        return Integer.parseInt(jp.getText());
    }
}