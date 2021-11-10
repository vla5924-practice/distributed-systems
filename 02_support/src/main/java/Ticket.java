public class Ticket {
    private static int lastId = 0;

    public int id;
    public TicketStatus status = TicketStatus.OPENED;
    public String date = "";
    public String description = "";
    public String userName = "";
    public String userEmail = "";
    public String whyReopened = "";
    public String whyCompleted = "";

    private Ticket(int id_) {
        id = id_;
    }

    public static Ticket create() {
        return new Ticket(lastId++);
    }
}
