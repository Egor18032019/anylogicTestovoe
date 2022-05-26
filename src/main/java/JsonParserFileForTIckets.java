
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserFileForTIckets {

//    C:\Users\Teacher\Downloads\tickets.json

    public static TicketsWrapper getParseListTicket(String pathToFileJson) {
        ObjectMapper mapper = new ObjectMapper();
        TicketsWrapper tickets = null;
        try {
            tickets = mapper.readValue(new File(pathToFileJson), TicketsWrapper.class);
        } catch (IOException e) {
            System.out.println("File not find ! ");
        }
        return tickets;


    }

}