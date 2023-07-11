package com.hrms.Hrmsbackend.models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdminHelpdesk {

   private String Ticket_Type;
   private String SubCategory;

   private int Raised;
   private int Pending;
   private int Closed;

   public AdminHelpdesk(String ticket_Type, String subCategory, int raised, int pending, int closed) {
      Ticket_Type = ticket_Type;
      SubCategory = subCategory;

      Raised = raised;
      Pending = pending;
      Closed = closed;
   }
   public AdminHelpdesk(){

   }
}
