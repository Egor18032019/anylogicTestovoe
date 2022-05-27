import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParserFileForTIckets {
    public static TicketsWrapper getParseListTicket(String pathToFileJson) {
        ObjectMapper mapper = new ObjectMapper();
        TicketsWrapper tickets = null;
        try {
            File src = new File(pathToFileJson);
            tickets = mapper.readValue(src, TicketsWrapper.class);
        } catch (IOException e) {
            System.out.println("Файл не найден или не правильно заполнен.");
            // TODO сделать разграничения ошибок
        }
        return tickets;


    }

}