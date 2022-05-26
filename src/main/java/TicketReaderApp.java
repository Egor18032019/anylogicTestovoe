import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TicketReaderApp {


    public static void main(String[] args) {
        boolean isRightPathTOFileAndICanRead = false;
        while (!isRightPathTOFileAndICanRead) {
            System.out.println("Введите путь к файлу tickets.json");
            String nameJsonFile = null;
            // поучили путь
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                nameJsonFile = reader.readLine();
            } catch (IOException e) {
                System.out.println(" Что пошло не так ");
            }
            isRightPathTOFileAndICanRead = true;
            // прочитали файл и сложили в List
            List<Ticket> tickets = JsonParserFileForTIckets.getParseListTicket(nameJsonFile).getTickets();
            System.out.println(tickets.toString());
            // разбиваем файл
//        считаем по тз
        }
    }

    public long getAverageTime() {
        long answer = 0;
        return answer;
    }

    public long getProcentil(double procentil) {
        return 0;
    }

    public long getProcentil() {
        return 0;
    }

}
