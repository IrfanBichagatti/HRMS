package com.hrms.Hrmsbackend.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
    @Data
    @Table(name = "professionalDetails")
    public class ProfessionalDetails {
        @Id
        @Column(name = "employee_id")
        private  String email;
        @Column(name = "degree")
        private String degree;
        @Column(name = "specification")
        private String specification;
        @Column(name = "collage")

        private String collage;

        @Column(name = "duration")
        private String duration;
        @Column(name = "enrollment_no")
        private String enrollment_no;
        @Column(name = "employee_code")
        private String employee_Code;
        @Column(name = "fresher")
        private String fresher;
        @Column(name = "experience")
        private String experience;
        @Column(name = "primary_skills")
        private String primary_skills;
        @Column(name = "secondary_skills")
        private String secondary_skills;
        @Column(name = "organization_name")
        private String organization_name;
        @Column(name = "reason_leaving")
        private String reason_leaving;
        @Column(name = " location")
        private String location;
        @Column(name = "manager_name")
        private String manager_name;
        @Column(name = "manager_emailid")
        private String manager_emailid;
        @Column(name = "gross_salary")
        private String gross_salary;
        @Column(name = "employee_code_org")
        private String employee_code_org;



        public ProfessionalDetails(){
            super();
        }

        public ProfessionalDetails(String email, String degree, String specification, String collage, String duration, String enrollment_no, String employee_Code, String fresher, String experience, String primary_skills, String secondary_skills, String organization_name, String reason_leaving, String location, String manager_name, String manager_emailid, String gross_salary, String employee_code_org) {
            this.email = email;
            this.degree = degree;
            this.specification = specification;
            this.collage = collage;
            this.duration = duration;
            this.enrollment_no = enrollment_no;
            this.employee_Code = employee_Code;
            this.fresher = fresher;
            this.experience = experience;
            this.primary_skills = primary_skills;
            this.secondary_skills = secondary_skills;
            this.organization_name = organization_name;
            this.reason_leaving = reason_leaving;
            this.location = location;
            this.manager_name = manager_name;
            this.manager_emailid = manager_emailid;
            this.gross_salary = gross_salary;
            this.employee_code_org = employee_code_org;
        }


        public String getemail() {
            return email;
        }

        public void setemail(String email) {
            this.email = email;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getCollage() {
            return collage;
        }

        public void setCollage(String collage) {
            this.collage = collage;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getEnrollment_no() {
            return enrollment_no;
        }

        public void setEnrollment_no(String enrollment_no) {
            this.enrollment_no = enrollment_no;
        }

        public String getEmployee_Code() {
            return employee_Code;
        }

        public void setEmployee_Code(String employee_Code) {
            this.employee_Code = employee_Code;
        }

        public String getFresher() {
            return fresher;
        }

        public void setFresher(String fresher) {
            this.fresher = fresher;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getPrimary_skills() {
            return primary_skills;
        }

        public void setPrimary_skills(String primary_skills) {
            this.primary_skills = primary_skills;
        }

        public String getSecondary_skills() {
            return secondary_skills;
        }

        public void setSecondary_skills(String secondary_skills) {
            this.secondary_skills = secondary_skills;
        }

        public String getOrganization_name() {
            return organization_name;
        }

        public void setOrganization_name(String organization_name) {
            this.organization_name = organization_name;
        }

        public String getReason_leaving() {
            return reason_leaving;
        }

        public void setReason_leaving(String reason_leaving) {
            this.reason_leaving = reason_leaving;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getManager_name() {
            return manager_name;
        }

        public void setManager_name(String manager_name) {
            this.manager_name = manager_name;
        }

        public String getManager_emailid() {
            return manager_emailid;
        }

        public void setManager_emailid(String manager_emailid) {
            this.manager_emailid = manager_emailid;
        }

        public String getGross_salary() {
            return gross_salary;
        }

        public void setGross_salary(String gross_salary) {
            this.gross_salary = gross_salary;
        }

        public String getEmployee_code_org() {
            return employee_code_org;
        }

        public void setEmployee_code_org(String employee_code_org) {
            this.employee_code_org = employee_code_org;
        }
    }



