package com.hrms.Hrmsbackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "HelpdeskTicketDetails")
public class HelpdeskTicketDetails  implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ticket_no")
    int ticket_no;
    @Column(name = "category")
    String category;
    @Column(name = "subcategory")
    String subcategory;
    @Column(name = "priority")
    String priority;
    @Column(name = "subject")
    String subject;
    @Column(name = "issue")
    String issue;

    @Column(name = "email")
    String email;
    @Column(name = "status")
    String status;
    @Column(name="raised_date")
    String raised_date;
    @Column (name="owner")
    String owner;

    public HelpdeskTicketDetails(int ticket_no, String category, String subcategory, String priority, String subject, String issue, String email,String status,String raised_date,String owner) {
        this.ticket_no = ticket_no;
        this.category = category;
        this.subcategory = subcategory;
        this.priority = priority;
        this.subject = subject;
        this.issue = issue;
        this.email= email;
        this.status= status;
        this.raised_date=raised_date;
        this.owner=owner;


    }

    public HelpdeskTicketDetails() {
    }




    @Override
    public int compareTo(Object o) {
        int cmpsno=((HelpdeskTicketDetails)o).getTicket_no();
        /* For Ascending order*/
        return this.ticket_no-cmpsno;
    }
}
