package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AdminHelpdesk;
import com.hrms.Hrmsbackend.models.HelpdeskTicketDetails;
import com.hrms.Hrmsbackend.service.AdminService;
import com.hrms.Hrmsbackend.service.HelpdeskService;
import com.hrms.Hrmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class HelpdeskController {

   @Autowired
   private HelpdeskService helpdeskService;

     /*   User
     Create User Helpdesk Ticket*/

    @PostMapping("/createTicket")
    public HelpdeskTicketDetails createTicket(@RequestBody HelpdeskTicketDetails helpdeskTicketDetails) {

        return helpdeskService.createTicket(helpdeskTicketDetails);
    }

 /*   Employee
    get User Ticket Details*/

    @GetMapping("/getUserTicket")
    public List<HelpdeskTicketDetails> getUserTicket(@RequestParam("email") String Username) {

        return helpdeskService.getUserTicket(Username);
    }

   /*  admin
     get admin ticket details*/

    @GetMapping("/getAdminTicketDetails")
    public List<HelpdeskTicketDetails> getAdminTicketDetails() {

        return helpdeskService.getAdminTicketDetails();
    }

    //admin ticket resolved
    @PostMapping("/ticketResolved")
    public Map<String,String> ticketResolved(@RequestBody Map<String,String> ticket_no) {

        return helpdeskService.ticketResolved(ticket_no);
    }

    //admin wrong ticket
    @PostMapping("/wrongTicket")
    public Map<String,String> wrongTicket(@RequestBody Map<String,String> ticket_no) {

        return helpdeskService.wrongTicket(ticket_no);
    }

    /* Admin
     * get the categorywise raised pending closed count
     * */
    @GetMapping("/getAdminHelpdesk")
    public  List<AdminHelpdesk> getAdminHelpdesk() {

        return helpdeskService.getAdminHelpdesk();
    }

    //helpdeskticketdetails
    //admin get  ticket details by ticketno
    @GetMapping("/getTicketDetailsByTicketNo/{ticket_no}")
    public Optional<HelpdeskTicketDetails> getTicketDetailsByTicketNo(@PathVariable String ticket_no ) {

        return helpdeskService.getTicketDetailsByTicketNo(ticket_no);
    }
    /** User helpdeskticketdetails
     User  get  ticket details by Priority
     */

    @GetMapping("/getTicketDetailsByPriority")
    public List<HelpdeskTicketDetails> getTicketDetailsByPriority(@RequestParam("priority")  String priority ,@RequestParam("username")  String username ) {

        return helpdeskService.getTicketDetailsByPriority(priority,username);
    }

    /** ADMIN helpdeskticketdetails
     ADMIN  get  ticket details by Priority and pending
     */

    @GetMapping("/getAdminTicketDetailsByPriority/{priority}")
    public List<HelpdeskTicketDetails> getAdminTicketDetailsByPriority(@PathVariable String priority ) {

        return helpdeskService.getAdminTicketDetailsByPriority(priority);
    }

    //admin HelpDesk reply
    @PostMapping("/sendReply")
    public String sendReplyOnEmail(@RequestBody Map<String, String> data) {
        return helpdeskService.sendReplyOnEmail(data);
    }

}
