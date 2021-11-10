import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@WebService(targetNamespace = "Support")
public class SupportWsdl {
    public static final int port = 5924;
    private LinkedList<Ticket> tickets = new LinkedList<>();

    @WebMethod
    public int createTicket(String description, String userName, String userEmail) {
        Ticket ticket = Ticket.create();
        ticket.date = Calendar.getInstance().getTime().toString();
        ticket.description = description;
        ticket.userName = userName;
        ticket.userEmail = userEmail;
        tickets.add(ticket);
        return ticket.id;
    }

    @WebMethod
    public TicketStatus getTicketStatus(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                return ticket.status;
            }
        }
        return TicketStatus.NOT_FOUND;
    }

    @WebMethod
    public TicketStatus acceptTicket(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                ticket.status = TicketStatus.ACCEPTED;
                return ticket.status;
            }
        }
        return TicketStatus.NOT_FOUND;
    }

    @WebMethod
    public TicketStatus reopenTicket(int id, String whyReopened) {
        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                ticket.status = TicketStatus.REOPENED;
                ticket.whyReopened = whyReopened;
                return ticket.status;
            }
        }
        return TicketStatus.NOT_FOUND;
    }

    @WebMethod
    public List<Ticket> getTickets() {
        return tickets;
    }

    @WebMethod
    public TicketStatus setTicketInProgress(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                ticket.status = TicketStatus.IN_PROGRESS;
                return ticket.status;
            }
        }
        return TicketStatus.NOT_FOUND;
    }

    @WebMethod
    public TicketStatus setTicketCompleted(int id, String whyCompleted) {
        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                ticket.status = TicketStatus.COMPLETED;
                ticket.whyCompleted = whyCompleted;
                return ticket.status;
            }
        }
        return TicketStatus.NOT_FOUND;
    }

    public static void main(String[] args) {
        SupportWsdl service = new SupportWsdl();
        String url = "http://localhost:%d/support".formatted(port);
        Endpoint.publish(url, service);
    }
}
