package com.event.hostevent.vo;

import java.util.List;

public class Event {
    private String organizer_email;
    private List<Invitee> inviteeList;

    public Event(){}

    public Event(String organizer_email, List<Invitee> listOfInvitee){
        this.organizer_email = organizer_email;
        this.inviteeList= listOfInvitee;
    }

    public String getOrganizer_email() {
        return organizer_email;
    }

    public void setOrganizer_email(String organizer_email) {
        this.organizer_email = organizer_email;
    }

    public List<Invitee> getInviteeList() {
        return inviteeList;
    }

    public void setInviteeList(List<Invitee> inviteeList) {
        this.inviteeList = inviteeList;
    }
}
