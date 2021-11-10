
package SupportService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the SupportService package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AcceptTicket_QNAME = new QName("Support", "acceptTicket");
    private final static QName _AcceptTicketResponse_QNAME = new QName("Support", "acceptTicketResponse");
    private final static QName _CreateTicket_QNAME = new QName("Support", "createTicket");
    private final static QName _CreateTicketResponse_QNAME = new QName("Support", "createTicketResponse");
    private final static QName _GetTicketStatus_QNAME = new QName("Support", "getTicketStatus");
    private final static QName _GetTicketStatusResponse_QNAME = new QName("Support", "getTicketStatusResponse");
    private final static QName _GetTickets_QNAME = new QName("Support", "getTickets");
    private final static QName _GetTicketsResponse_QNAME = new QName("Support", "getTicketsResponse");
    private final static QName _ReopenTicket_QNAME = new QName("Support", "reopenTicket");
    private final static QName _ReopenTicketResponse_QNAME = new QName("Support", "reopenTicketResponse");
    private final static QName _SetTicketCompleted_QNAME = new QName("Support", "setTicketCompleted");
    private final static QName _SetTicketCompletedResponse_QNAME = new QName("Support", "setTicketCompletedResponse");
    private final static QName _SetTicketInProgress_QNAME = new QName("Support", "setTicketInProgress");
    private final static QName _SetTicketInProgressResponse_QNAME = new QName("Support", "setTicketInProgressResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: SupportService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcceptTicket }
     * 
     */
    public AcceptTicket createAcceptTicket() {
        return new AcceptTicket();
    }

    /**
     * Create an instance of {@link AcceptTicketResponse }
     * 
     */
    public AcceptTicketResponse createAcceptTicketResponse() {
        return new AcceptTicketResponse();
    }

    /**
     * Create an instance of {@link CreateTicket }
     * 
     */
    public CreateTicket createCreateTicket() {
        return new CreateTicket();
    }

    /**
     * Create an instance of {@link CreateTicketResponse }
     * 
     */
    public CreateTicketResponse createCreateTicketResponse() {
        return new CreateTicketResponse();
    }

    /**
     * Create an instance of {@link GetTicketStatus }
     * 
     */
    public GetTicketStatus createGetTicketStatus() {
        return new GetTicketStatus();
    }

    /**
     * Create an instance of {@link GetTicketStatusResponse }
     * 
     */
    public GetTicketStatusResponse createGetTicketStatusResponse() {
        return new GetTicketStatusResponse();
    }

    /**
     * Create an instance of {@link GetTickets }
     * 
     */
    public GetTickets createGetTickets() {
        return new GetTickets();
    }

    /**
     * Create an instance of {@link GetTicketsResponse }
     * 
     */
    public GetTicketsResponse createGetTicketsResponse() {
        return new GetTicketsResponse();
    }

    /**
     * Create an instance of {@link ReopenTicket }
     * 
     */
    public ReopenTicket createReopenTicket() {
        return new ReopenTicket();
    }

    /**
     * Create an instance of {@link ReopenTicketResponse }
     * 
     */
    public ReopenTicketResponse createReopenTicketResponse() {
        return new ReopenTicketResponse();
    }

    /**
     * Create an instance of {@link SetTicketCompleted }
     * 
     */
    public SetTicketCompleted createSetTicketCompleted() {
        return new SetTicketCompleted();
    }

    /**
     * Create an instance of {@link SetTicketCompletedResponse }
     * 
     */
    public SetTicketCompletedResponse createSetTicketCompletedResponse() {
        return new SetTicketCompletedResponse();
    }

    /**
     * Create an instance of {@link SetTicketInProgress }
     * 
     */
    public SetTicketInProgress createSetTicketInProgress() {
        return new SetTicketInProgress();
    }

    /**
     * Create an instance of {@link SetTicketInProgressResponse }
     * 
     */
    public SetTicketInProgressResponse createSetTicketInProgressResponse() {
        return new SetTicketInProgressResponse();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcceptTicket }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "acceptTicket")
    public JAXBElement<AcceptTicket> createAcceptTicket(AcceptTicket value) {
        return new JAXBElement<AcceptTicket>(_AcceptTicket_QNAME, AcceptTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcceptTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "acceptTicketResponse")
    public JAXBElement<AcceptTicketResponse> createAcceptTicketResponse(AcceptTicketResponse value) {
        return new JAXBElement<AcceptTicketResponse>(_AcceptTicketResponse_QNAME, AcceptTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateTicket }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "createTicket")
    public JAXBElement<CreateTicket> createCreateTicket(CreateTicket value) {
        return new JAXBElement<CreateTicket>(_CreateTicket_QNAME, CreateTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CreateTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "createTicketResponse")
    public JAXBElement<CreateTicketResponse> createCreateTicketResponse(CreateTicketResponse value) {
        return new JAXBElement<CreateTicketResponse>(_CreateTicketResponse_QNAME, CreateTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketStatus }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketStatus }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "getTicketStatus")
    public JAXBElement<GetTicketStatus> createGetTicketStatus(GetTicketStatus value) {
        return new JAXBElement<GetTicketStatus>(_GetTicketStatus_QNAME, GetTicketStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketStatusResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketStatusResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "getTicketStatusResponse")
    public JAXBElement<GetTicketStatusResponse> createGetTicketStatusResponse(GetTicketStatusResponse value) {
        return new JAXBElement<GetTicketStatusResponse>(_GetTicketStatusResponse_QNAME, GetTicketStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTickets }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTickets }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "getTickets")
    public JAXBElement<GetTickets> createGetTickets(GetTickets value) {
        return new JAXBElement<GetTickets>(_GetTickets_QNAME, GetTickets.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTicketsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTicketsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "getTicketsResponse")
    public JAXBElement<GetTicketsResponse> createGetTicketsResponse(GetTicketsResponse value) {
        return new JAXBElement<GetTicketsResponse>(_GetTicketsResponse_QNAME, GetTicketsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReopenTicket }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReopenTicket }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "reopenTicket")
    public JAXBElement<ReopenTicket> createReopenTicket(ReopenTicket value) {
        return new JAXBElement<ReopenTicket>(_ReopenTicket_QNAME, ReopenTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReopenTicketResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReopenTicketResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "reopenTicketResponse")
    public JAXBElement<ReopenTicketResponse> createReopenTicketResponse(ReopenTicketResponse value) {
        return new JAXBElement<ReopenTicketResponse>(_ReopenTicketResponse_QNAME, ReopenTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTicketCompleted }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetTicketCompleted }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "setTicketCompleted")
    public JAXBElement<SetTicketCompleted> createSetTicketCompleted(SetTicketCompleted value) {
        return new JAXBElement<SetTicketCompleted>(_SetTicketCompleted_QNAME, SetTicketCompleted.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTicketCompletedResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetTicketCompletedResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "setTicketCompletedResponse")
    public JAXBElement<SetTicketCompletedResponse> createSetTicketCompletedResponse(SetTicketCompletedResponse value) {
        return new JAXBElement<SetTicketCompletedResponse>(_SetTicketCompletedResponse_QNAME, SetTicketCompletedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTicketInProgress }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetTicketInProgress }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "setTicketInProgress")
    public JAXBElement<SetTicketInProgress> createSetTicketInProgress(SetTicketInProgress value) {
        return new JAXBElement<SetTicketInProgress>(_SetTicketInProgress_QNAME, SetTicketInProgress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTicketInProgressResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetTicketInProgressResponse }{@code >}
     */
    @XmlElementDecl(namespace = "Support", name = "setTicketInProgressResponse")
    public JAXBElement<SetTicketInProgressResponse> createSetTicketInProgressResponse(SetTicketInProgressResponse value) {
        return new JAXBElement<SetTicketInProgressResponse>(_SetTicketInProgressResponse_QNAME, SetTicketInProgressResponse.class, null, value);
    }

}
