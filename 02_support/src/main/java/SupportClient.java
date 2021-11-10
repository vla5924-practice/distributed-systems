import SupportService.SupportWsdlService;
import SupportService.SupportWsdl;
import SupportService.Ticket;
import SupportService.TicketStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class SupportClient {
    public static final int port = 5924;
    public static final String url = "http://localhost:%d/support?wsdl";

    public static void main(String[] args) throws IOException {
        SupportWsdl service = new SupportWsdlService(new URL(String.format(url, port))).getSupportWsdlPort();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("=== SUPPORT SERVICE ===");
        System.out.print("Enter your role [0 - user, 1 - admin]: ");
        int role = -1;
        do {
            try {
                role = Integer.parseInt(br.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid format!");
            }
        } while (role != 0 && role != 1);
        if (role == 0) {
            System.out.println("USER INTERFACE");
            while (true) {
                System.out.print("Enter action number [1 - create ticket, 2 - get ticket status, 3 - accept ticket, 4 - reopen ticket]: ");
                int action = -1;
                do {
                    try {
                        action = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException nfe) {
                        System.err.println("Invalid format!");
                    }
                } while (action < 1 || action > 4);
                switch (action) {
                    case 1: {
                        System.out.print("Enter description: ");
                        String description = br.readLine();
                        System.out.print("Enter your name: ");
                        String userName = br.readLine();
                        System.out.print("Enter your email: ");
                        String userEmail = br.readLine();
                        int ticketId = service.createTicket(description, userName, userEmail);
                        System.out.println("Created ticket with ID %d".formatted(ticketId));
                        break;
                    }
                    case 2: {
                        System.out.print("Enter ticket ID: ");
                        int ticketId = Integer.parseInt(br.readLine());
                        TicketStatus status = service.getTicketStatus(ticketId);
                        System.out.println("Ticket with ID %d has status %s".formatted(ticketId, status));
                        break;
                    }
                    case 3: {
                        System.out.print("Enter ticket ID: ");
                        int ticketId = Integer.parseInt(br.readLine());
                        TicketStatus status = service.acceptTicket(ticketId);
                        System.out.println("Ticket with ID %d now has status %s".formatted(ticketId, status));
                        break;
                    }
                    case 4: {
                        System.out.print("Enter ticket ID: ");
                        int ticketId = Integer.parseInt(br.readLine());
                        System.out.print("Enter rejection justification: ");
                        String whyReopen = br.readLine();
                        TicketStatus status = service.reopenTicket(ticketId, whyReopen);
                        System.out.println("Ticket with ID %d now has status %s".formatted(ticketId, status));
                        break;
                    }
                    default:
                        System.out.println("Invalid action number, try again. Ctrl+C to exit.");
                }
            }
        } else {
            System.out.println("ADMIN INTERFACE");
            while (true) {
                System.out.print("Enter action number [1 - get all tickets, 2 - set ticket in progress, 3 - set ticket completed]: ");
                int action = -1;
                do {
                    try {
                        action = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException nfe) {
                        System.err.println("Invalid format!");
                    }
                } while (action < 1 || action > 3);
                switch (action) {
                    case 1: {
                        List<Ticket> tickets = service.getTickets();
                        System.out.println("--- Tickets ---");
                        for (Ticket ticket : tickets) {
                            System.out.println("%d (created at %s), STATUS: %s, AUTHOR: %s <%s>".formatted(ticket.getId(), ticket.getDate(), ticket.getStatus(), ticket.getUserName(), ticket.getUserEmail()));
                            if (ticket.getStatus() == TicketStatus.COMPLETED) {
                                System.out.println("Completion justification: %s".formatted(ticket.getWhyCompleted()));
                            }
                            if (ticket.getStatus() == TicketStatus.REOPENED) {
                                System.out.println("Rejection justification: %s".formatted(ticket.getWhyReopened()));
                            }
                            System.out.println(ticket.getDescription());
                            System.out.println("---");
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("Enter ticket ID: ");
                        int ticketId = Integer.parseInt(br.readLine());
                        TicketStatus status = service.setTicketInProgress(ticketId);
                        System.out.println("Ticket with ID %d now has status %s".formatted(ticketId, status));
                        break;
                    }
                    case 3: {
                        System.out.print("Enter ticket ID: ");
                        int ticketId = Integer.parseInt(br.readLine());
                        System.out.print("Enter completion justification: ");
                        String whyCompleted = br.readLine();
                        TicketStatus status = service.setTicketCompleted(ticketId, whyCompleted);
                        System.out.println("Ticket with ID %d now has status %s".formatted(ticketId, status));
                        break;
                    }
                    default:
                        System.out.println("Invalid action number, try again. Ctrl+C to exit.");
                }
            }
        }
    }
}
