import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@JsonAutoDetect
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketsWrapper {

    List<Ticket> tickets;

    @JsonCreator
    public TicketsWrapper(
            @JsonProperty("tickets") List<Ticket> tickets) {
        this.tickets = tickets;
    }
}