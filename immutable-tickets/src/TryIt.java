import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t =
                service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        t = service.assign(t, "agent@example.com");
        t = service.escalateToCritical(t);
        System.out.println("\nAfter service updates: " + t);

        // This will now throw UnsupportedOperationException
        List<String> tags = t.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (Exception e) {
            System.out.println("\nExternal mutation blocked ✔");
        }

        System.out.println("\nFinal ticket: " + t);
    }
}