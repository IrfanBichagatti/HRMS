package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.Exception.ResourceNotFoundException;
import com.hrms.Hrmsbackend.models.AdminApproveProfessionalEntity;
import com.hrms.Hrmsbackend.models.ProfessionalDetails;
import com.hrms.Hrmsbackend.repo.AdminProfessionalRepo;
import com.hrms.Hrmsbackend.repo.ProfessionalDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessionalDetailsService {
    @Autowired
    private ProfessionalDetailsRepository professionalDetailsRepository;
    @Autowired
    private AdminProfessionalRepo adminProfessionalRepo;




    public ProfessionalDetails saveprofessionalDetails(ProfessionalDetails professionalDetails) {
        return professionalDetailsRepository.save(professionalDetails);
    }
    public ProfessionalDetails UpdateProfessionalDetailsbyId(String id, ProfessionalDetails professionalDetails) {
        ProfessionalDetails existingDetails = professionalDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        existingDetails.setemail(professionalDetails.getemail());


        existingDetails.setDegree(professionalDetails.getDegree());
        existingDetails.setCollage(professionalDetails.getCollage());
        existingDetails.setDuration(professionalDetails.getDuration());
        existingDetails.setExperience(professionalDetails.getExperience());
        existingDetails.setEmployee_Code(professionalDetails.getEmployee_Code());
        existingDetails.setFresher(professionalDetails.getFresher());
        existingDetails.setEnrollment_no(professionalDetails.getEnrollment_no());
        existingDetails.setGross_salary(professionalDetails.getGross_salary());
        existingDetails.setManager_emailid(professionalDetails.getManager_emailid());
        existingDetails.setManager_name(professionalDetails.getManager_name());
        existingDetails.setOrganization_name(professionalDetails.getOrganization_name());
        existingDetails.setPrimary_skills(professionalDetails.getPrimary_skills());
        existingDetails.setReason_leaving(professionalDetails.getReason_leaving());
        existingDetails.setSecondary_skills(professionalDetails.getSecondary_skills());
        existingDetails.setSpecification(professionalDetails.getSpecification());
        existingDetails.setLocation(professionalDetails.getLocation());
        existingDetails.setEmployee_code_org(professionalDetails.getEmployee_code_org());

        return professionalDetailsRepository.save(existingDetails);

    }
    public ProfessionalDetails UpdateProfessionalDetailsbyId(AdminApproveProfessionalEntity adminApproveProfessionalEntity) {
        String email=adminApproveProfessionalEntity.getEmail();
        ProfessionalDetails existingDetails = professionalDetailsRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + email));
        existingDetails.setemail(adminApproveProfessionalEntity.getEmail());


        existingDetails.setDegree(adminApproveProfessionalEntity.getDegree());
        existingDetails.setCollage(adminApproveProfessionalEntity.getCollege());

        existingDetails.setOrganization_name(adminApproveProfessionalEntity.getOrganization_name());
        existingDetails.setPrimary_skills(adminApproveProfessionalEntity.getPrimary_skill());

        existingDetails.setSecondary_skills(adminApproveProfessionalEntity.getSecondary_skill());
        existingDetails.setSpecification(adminApproveProfessionalEntity.getSpecification());

        adminProfessionalRepo.deleteById(email);

        return professionalDetailsRepository.save(existingDetails);

    }
}
