package jsondeserialize;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import utils.Const;

import java.io.IOException;
import java.time.LocalDate;

public class JsonDeserializeDateTickets extends LocalDateDeserializer {
    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // "12.05.18"
        if (parser.isExpectedStartArrayToken()) {
            return parseArray(parser, context);
        }
        return super.deserialize(parser, context);
    }

    private LocalDate parseArray(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        int day = getNextValue(jp);
        int month = getNextValue(jp);
        int year = getNextValue(jp);
        if (jp.getCurrentToken() != JsonToken.START_ARRAY) {
            throw new JsonMappingException(jp, "Not an array!");
        }
        if (jp.nextToken() != JsonToken.END_ARRAY) {
            throw new JsonMappingException(jp, "after LocalTime ints");
        }
        return LocalDate.of(year,month,day);
    }

    private int getNextValue(JsonParser jp) throws IOException, JsonParseException {
        jp.nextToken();
        return Integer.parseInt(jp.getText());
    }
}
