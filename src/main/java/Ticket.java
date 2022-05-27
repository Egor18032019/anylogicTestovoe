import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jsondeserialize.JsonDeserializeDateTickets;
import jsondeserialize.JsonDeserializeTimeTickets;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import utils.Const;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    @JsonDeserialize(using = JsonDeserializeDateTickets.class)
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate departure_date;
    @JsonDeserialize(using = JsonDeserializeTimeTickets.class)
    @JsonFormat(pattern = "H:mm")
    private LocalTime departure_time;
    @JsonDeserialize(using = JsonDeserializeDateTickets.class)
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate arrival_date;
    @JsonDeserialize(using = JsonDeserializeTimeTickets.class)
    @JsonFormat(pattern = "H:mm")
    private LocalTime arrival_time;
    private String carrier;
    private int stops;
    private int price;

    @Override
    public String toString() {
        return "Ticket{" +
                "origin='" + origin + '\'' +
                ", origin_name='" + origin_name + '\'' +
                ", destination='" + destination + '\'' +
                ", destination_name='" + destination_name + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}