package org.jaesay.chapter01;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Chapter01Example {

    public static void main(String[] args) {
        TicketOffice ticketOffice = new TicketOffice(0L, createTickets(10));
        TicketSeller ticketSeller = new TicketSeller(ticketOffice);
        Theater theater = new Theater(ticketSeller);

        Audience audience1 = new Audience(new Bag(100_000L));
        Audience audience2 = new Audience(new Bag(new Invitation(LocalDateTime.now()), 0L));

        theater.enter(audience1);
        theater.enter(audience2);
    }

    public static List<Ticket> createTickets(int numberOfTickets) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i=0; i< numberOfTickets; i++) {
            tickets.add(new Ticket(10_000L));
        }
        return tickets;
    }
}
