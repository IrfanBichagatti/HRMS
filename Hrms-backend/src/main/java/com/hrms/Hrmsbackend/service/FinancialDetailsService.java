package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.FinancialDetails;
import com.hrms.Hrmsbackend.repo.FinancialDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinancialDetailsService {
    @Autowired
    private FinancialDetailsRepository financialDetailsRepository;

    public Optional<FinancialDetails> getFinancialByID(String id) {
        return financialDetailsRepository.findById(id);
    }


    //public FinancialDetails getFinancialDetails(){

    //    return financialDetailsRepository.findAll();
    //}

    public FinancialDetails saveFinancialDetails(FinancialDetails financialDetails) {
        return financialDetailsRepository.save(financialDetails);
    }


    public FinancialDetails UpdateFinancialDetailsbyId(String id , FinancialDetails financialDetails) {
        FinancialDetails existingDetails = financialDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with email :" + id));

        existingDetails.setname_as_pan(financialDetails.getname_as_pan());
        existingDetails.setaadhar_card_number(financialDetails.getaadhar_card_number());
        existingDetails.setbank_account_number(financialDetails.getbank_account_number());
        existingDetails.setbank_branch(financialDetails.getbank_branch());
        existingDetails.setesic_number(financialDetails.getesic_number());
        existingDetails.setbank_name(financialDetails.getbank_name());

        existingDetails.setuannumbere(financialDetails.getuannumbere());
        existingDetails.setpassport_number(financialDetails.getpassport_number());
        existingDetails.settype_of_account(financialDetails.gettype_of_account());
        existingDetails.setpassport_issue_date(financialDetails.getpassport_issue_date());
        existingDetails.setpassport_expiry_date(financialDetails.getpassport_expiry_date());
        existingDetails.setifsc_code(financialDetails.getifsc_code());
        existingDetails.setpan_number(financialDetails.getpan_number());
        existingDetails.setname_as_peraadhar(financialDetails.getname_as_peraadhar());
        existingDetails.setpfnumber(financialDetails.getpfnumber());
        existingDetails.setname_as_per_bank_records(financialDetails.getname_as_per_bank_records());

        return financialDetailsRepository.save(existingDetails);

    }

}
