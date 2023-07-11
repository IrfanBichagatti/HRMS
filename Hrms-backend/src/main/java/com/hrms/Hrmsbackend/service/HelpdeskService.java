package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AdminHelpdesk;
import com.hrms.Hrmsbackend.models.AlertData;
import com.hrms.Hrmsbackend.models.HelpdeskTicketDetails;
import com.hrms.Hrmsbackend.repo.AlertRepo;
import com.hrms.Hrmsbackend.repo.HelpdeskTicketDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class HelpdeskService {


    @Autowired
    private HelpdeskTicketDetailsRepo helpdeskTicketDetailsRepo;
    @Autowired
    private AlertRepo alertRepo;
    @Autowired
    private JavaMailSender replymailSender;

    public HelpdeskTicketDetails createTicket(HelpdeskTicketDetails helpdeskTicketDetails) {

        return helpdeskTicketDetailsRepo.save(helpdeskTicketDetails);

    }

    public List<HelpdeskTicketDetails> getUserTicket(String username) {

        List<HelpdeskTicketDetails> tickets = helpdeskTicketDetailsRepo.findAll();
        List<HelpdeskTicketDetails> ticket = new ArrayList<HelpdeskTicketDetails>();

        Iterator<HelpdeskTicketDetails> iterator = tickets.iterator();
        while (iterator.hasNext()){
            HelpdeskTicketDetails ht = (HelpdeskTicketDetails) iterator.next();
            if (username.equals(ht.getEmail())) {
                System.out.print(ht);
                ticket.add(ht);
            }
        }
        Collections.sort(ticket,Collections.reverseOrder());
        return ticket;
    }

    public List<HelpdeskTicketDetails> getAdminTicketDetails() {


        List<HelpdeskTicketDetails> tickets = helpdeskTicketDetailsRepo.findAll();
        List<HelpdeskTicketDetails> ticket1 = new ArrayList<HelpdeskTicketDetails>();

        Iterator<HelpdeskTicketDetails> iterator = tickets.iterator();
        while (iterator.hasNext()){
            HelpdeskTicketDetails ht = (HelpdeskTicketDetails) iterator.next();
            if ("Pending".equals(ht.getStatus())) {
                System.out.print(ht);
                ticket1.add(ht);
            }
        }
        Collections.sort(ticket1,Collections.reverseOrder());
        return ticket1;
    }

    public Optional<HelpdeskTicketDetails> getTicketDetailsByTicketNo(String ticket_no) {

        Optional<HelpdeskTicketDetails> ticketDetails =  helpdeskTicketDetailsRepo.findById(Integer.parseInt(ticket_no));

        return ticketDetails;
    }

    public List<HelpdeskTicketDetails> getTicketDetailsByPriority(String priority,String username) {

        List<HelpdeskTicketDetails> ticketDetails =  helpdeskTicketDetailsRepo.findAll().stream()
                .filter(data-> data.getPriority().equalsIgnoreCase(priority)
                        &&data.getEmail().equalsIgnoreCase(username)).collect(Collectors.toList());

        return ticketDetails;
    }

    public List<HelpdeskTicketDetails> getAdminTicketDetailsByPriority(String priority) {

        List<HelpdeskTicketDetails> ticketDetails =  helpdeskTicketDetailsRepo.findAll().stream()
                .filter(data-> data.getPriority().equalsIgnoreCase(priority)
                        && data.getStatus().equalsIgnoreCase("Pending") ).collect(Collectors.toList());

        return ticketDetails;
    }

    public Map<String,String> ticketResolved(Map<String, String> ticket_no) {
        Map<String,String > map = new HashMap<>();
        HelpdeskTicketDetails details = helpdeskTicketDetailsRepo.findById(Integer.parseInt(ticket_no.get("ticket_no"))).orElseThrow();
        details.setStatus("Closed");
        helpdeskTicketDetailsRepo.save(details);
        map.put("status","resolved");
        return map;
    }

    public Map<String, String> wrongTicket(Map<String, String> ticket_no) {
        Map<String,String > map = new HashMap<>();
        HelpdeskTicketDetails details = helpdeskTicketDetailsRepo.findById(Integer.parseInt(ticket_no.get("ticket_no"))).orElseThrow();
        details.setStatus("Wrong Ticket");
        helpdeskTicketDetailsRepo.save(details);
        map.put("status","Wrong");
        return map;
    }

    public List<AdminHelpdesk> getAdminHelpdesk() {
        Map<String ,Integer> map = new HashMap<>();
        List<AdminHelpdesk> adminHelpdesks = new ArrayList<>();

        String[] subCategory = new String[]{"salary credit","salary/ctc quary/form 16/income tax query",
                "hr policy","insurance","pf esic","salary credit","asset/laptop problem","onboarding/profile",
                "attendance/leave","technical issue","others"
        };

        int length = subCategory.length;

        for(int i=0;i<length;i++) {
            int finalI = i;
            List<HelpdeskTicketDetails> details1 = helpdeskTicketDetailsRepo.findAll().stream().filter(
                    data -> data.getSubcategory().equals(subCategory[finalI])).collect(Collectors.toList());

            int size = details1.size();
            List<HelpdeskTicketDetails> pendings = details1.stream().
                    filter(data-> data.getStatus().equalsIgnoreCase("Pending")).collect(Collectors.toList());
            int pending = pendings.size();

            List<HelpdeskTicketDetails> closed = details1.stream().
                    filter(data-> data.getStatus().equalsIgnoreCase("Closed") ||  data.getStatus().equalsIgnoreCase("Wrong Ticket")  ).collect(Collectors.toList());
            int close = closed.size();


            if(size!=0) {
                AdminHelpdesk helpdesk = new AdminHelpdesk(details1.get(0).getCategory(), details1.get(0).getSubcategory(),
                        size, pending, close);
                adminHelpdesks.add(helpdesk);
            }
        }

        return adminHelpdesks;
    }

    public String sendReplyOnEmail(Map<String, String> data) {
        SimpleMailMessage emailmessage = new SimpleMailMessage();
        emailmessage.setFrom("tusharbk108@gmail.com");
        String helpdeskmsg="Dear "+data.get("name") + "  \n " +
                "Your Ticket No :"+ data.get("ticket_no") + "\n "+
                data.get("subcategory")+ "\n "+

                data.get("subject")+ "\n "+
                "Reply is :"+  data.get("msg");

        emailmessage.setTo(data.get("email"));
        emailmessage.setSubject("Acknowledgment for Helpdesk Ticket");

        emailmessage.setText(helpdeskmsg);
        AlertData alertData = new AlertData(data.get("email"),helpdeskmsg);
        alertRepo.save(alertData);
        replymailSender.send(emailmessage);
        return "success";
    }
}
