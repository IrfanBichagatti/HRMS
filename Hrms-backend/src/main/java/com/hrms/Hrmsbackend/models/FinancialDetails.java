package com.hrms.Hrmsbackend.models;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "FinanacialDetails")
public class FinancialDetails {
    @Id

    @Column(name = "Employee_ID")
    private String email;

    @Column(name = "name_as_pan")
    private String name_as_pan;
    @Column(name = "pan_number")
    private String pan_number;
    @Column(name = "name_as_peraadhar")

    private String name_as_peraadhar;

    @Column(name = "aadhar_card_number")
    private String aadhar_card_number;

    @Column(name = "passport_number")
    private String passport_number;
    @Column(name = "pf_number")
    private String pfnumber;
    @Column(name = "uan_number")
    private String uannumbere;
    @Column(name = "esic_number")
    private String esic_number;
    @Column(name = "passport_issue_date")
    private String passport_issue_date;
    @Column(name = "passport_expiry_date")
    private String passport_expiry_date;
    @Column(name = "bank_name")
    private String bank_name;
    @Column(name = " bank_branch")
    private String bank_branch;
    @Column(name = "name_as_per_bank_records")
    private String name_as_per_bank_records;
    @Column(name = "ifsc_code")
    private String ifsc_code;

    @Column(name = "bank_account_number")
    private String bank_account_number;
    @Column(name = "type_of_account")
    private String type_of_account;


    public FinancialDetails()
    {
        super();
    }

    public FinancialDetails(String email, String health_insurance_premium, String accidental_insurance_premium, String payment_type, String name_as_pan, String pan_number, String name_as_peraadhar, String aadhar_card_number, String passport_number, String pfnumber, String uannumbere, String esic_number, String passport_issue_date, String passport_expiry_date, String bank_name, String bank_branch, String name_as_per_bank_records, String ifsc_code, String beneficiary_name, String bank_account_number, String type_of_account) {
        this.email = email;

        this.name_as_pan = name_as_pan;
        this.pan_number = pan_number;
        this.name_as_peraadhar = name_as_peraadhar;
        this.aadhar_card_number = aadhar_card_number;
        this.passport_number = passport_number;
        this.pfnumber = pfnumber;
        this.uannumbere = uannumbere;
        this.esic_number = esic_number;
        this.passport_issue_date = passport_issue_date;
        this.passport_expiry_date = passport_expiry_date;
        this.bank_name = bank_name;
        this.bank_branch = bank_branch;
        this.name_as_per_bank_records = name_as_per_bank_records;
        this.ifsc_code = ifsc_code;

        this.bank_account_number = bank_account_number;
        this.type_of_account = type_of_account;
    }


    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email= email;
    }

    public String getname_as_pan() {
        return name_as_pan;
    }

    public void setname_as_pan(String name_as_pan) {
        this.name_as_pan = name_as_pan;
    }

    public String getpan_number() {
        return pan_number;
    }

    public void setpan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public String getname_as_peraadhar() {
        return name_as_peraadhar;
    }

    public void setname_as_peraadhar(String name_as_peraadhar) {
        this.name_as_peraadhar = name_as_peraadhar;
    }

    public String getaadhar_card_number() {
        return aadhar_card_number;
    }

    public void setaadhar_card_number(String aadhar_card_number) {
        this.aadhar_card_number = aadhar_card_number;
    }

    public String getpassport_number() {
        return passport_number;
    }

    public void setpassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getpfnumber() {
        return pfnumber;
    }

    public void setpfnumber(String pfnumber) {
        this.pfnumber = pfnumber;
    }

    public String getuannumbere() {
        return uannumbere;
    }

    public void setuannumbere(String uannumbere) {
        this.uannumbere = uannumbere;
    }

    public String getesic_number() {
        return esic_number;
    }

    public void setesic_number(String esic_number) {
        this.esic_number = esic_number;
    }

    public String getpassport_issue_date() {
        return passport_issue_date;
    }

    public void setpassport_issue_date(String passport_issue_date) {
        this.passport_issue_date = passport_issue_date;
    }

    public String getpassport_expiry_date() {
        return passport_expiry_date;
    }

    public void setpassport_expiry_date(String passport_expiry_date) {
        this.passport_expiry_date = passport_expiry_date;
    }

    public String getbank_name() {
        return bank_name;
    }

    public void setbank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getbank_branch() {
        return bank_branch;
    }

    public void setbank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getname_as_per_bank_records() {
        return name_as_per_bank_records;
    }

    public void setname_as_per_bank_records(String name_as_per_bank_records) {
        this.name_as_per_bank_records = name_as_per_bank_records;
    }

    public String getifsc_code() {
        return ifsc_code;
    }

    public void setifsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }





    public String getbank_account_number() {
        return bank_account_number;
    }

    public void setbank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }

    public String gettype_of_account() {
        return type_of_account;
    }

    public void settype_of_account(String type_of_account) {
        this.type_of_account = type_of_account;
    }

}


