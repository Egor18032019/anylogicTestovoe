import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketReaderApp {
    static double PERCENTIL = 90.0;

    public static void main(String[] args) {
        boolean isRightPathTOFileAndICanRead = false;
        String nameJsonFile = null;
        List<Ticket> tickets = new ArrayList<>();

        while (!isRightPathTOFileAndICanRead) {
            System.out.println("Введите путь к файлу tickets.json");
            // получили путь
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                nameJsonFile = reader.readLine();
            } catch (IOException e) {
                System.out.println(" Что то пошло не так ( ");
            }
            TicketsWrapper ticketsWrapper = JsonParserFileForTIckets.getParseListTicket(nameJsonFile);

            if (ticketsWrapper != null) {
                isRightPathTOFileAndICanRead = true;
                tickets = ticketsWrapper.getTickets();
            }
        }

        Long averageDurationTime = getAverageTimeFlight(tickets);
        System.out.println("Среднее время полета между городами Владивосток  и Тель-Авив = " + averageDurationTime + "мин.");

        System.out.println(" 90-й процентиль времени полета между городами  Владивосток и Тель-Авив равен = " +
                getProcentil(tickets) + "мин.");

    }

    public static long getAverageTimeFlight(List<Ticket> tickets) {
        List<Long> durationsList = getDurationsList(tickets);
        long sum = durationsList.stream()
                .mapToLong(a -> a)
                .sum();
        return sum / durationsList.size();
    }

    /*
        Процентиль это определенная часть выборки данных
     */
    public static long getProcentil(List<Ticket> tickets) {
        double avrgnumber = PERCENTIL * (tickets.size() - 1) / 100;
        int firstElement = (int) Math.floor(avrgnumber);
        int secondElement = (int) Math.ceil(avrgnumber);
        List<Long> durations = getDurationsList(tickets);
        Collections.sort(durations);
        long durationFirstElement = durations.get(firstElement);
        long durationSecondElement = durations.get(secondElement);
        return (long) (durationFirstElement + (durationSecondElement - durationFirstElement) * PERCENTIL / 100);
    }

    private static List<Long> getDurationsList(List<Ticket> tickets) {
        List<Long> durationsList = new ArrayList<>();
        for (Ticket ticket : tickets) {
            LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDeparture_date(), ticket.getDeparture_time());
            LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrival_date(), ticket.getArrival_time());
            long duration = Math.abs(ChronoUnit.MINUTES.between(arrivalDateTime, departureDateTime));
            durationsList.add(duration);
        }
        return durationsList;
    }

}

//    C:\Users\Teacher\Downloads\tickets.json
//    C:\Users\Teacher\Documents\GitHub\AnylogicTesotovoe\src\main\resources\tickets.json