package com.example.tickets;

public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        IncidentTicket.Builder b = copyOf(t);
        b.priority("CRITICAL");
        b.addTag("ESCALATED");
        return b.build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        IncidentTicket.Builder b = copyOf(t);
        b.assigneeEmail(assigneeEmail);
        return b.build();
    }

    private IncidentTicket.Builder copyOf(IncidentTicket t) {
        IncidentTicket.Builder b = new IncidentTicket.Builder()
                .id(t.getId())
                .reporterEmail(t.getReporterEmail())
                .title(t.getTitle())
                .description(t.getDescription())
                .priority(t.getPriority())
                .customerVisible(t.isCustomerVisible())
                .slaMinutes(t.getSlaMinutes())
                .source(t.getSource());

        for (String tag : t.getTags()) {
            b.addTag(tag);
        }
        return b;
    }
}