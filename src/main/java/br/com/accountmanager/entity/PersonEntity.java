package br.com.accountmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String cnpj;

    private String company_name;

    private String fantasy_name;

    private String cpf;

    private String full_name;

    private Date birth_date;

    public PersonEntity() {
    }

    public PersonEntity(Long id, String cnpj, String company_name, String fantasy_name, String cpf, String full_name, Date birth_date) {
        this.id = id;
        this.cnpj = cnpj;
        this.company_name = company_name;
        this.fantasy_name = fantasy_name;
        this.cpf = cpf;
        this.full_name = full_name;
        this.birth_date = birth_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFantasy_name() {
        return fantasy_name;
    }

    public void setFantasy_name(String fantasy_name) {
        this.fantasy_name = fantasy_name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override public String toString() {
        return "PersonEntity{" +
            "id=" + id +
            ", cnpj='" + cnpj + '\'' +
            ", company_name='" + company_name + '\'' +
            ", fantasy_name='" + fantasy_name + '\'' +
            ", cpf='" + cpf + '\'' +
            ", full_name='" + full_name + '\'' +
            ", birth_date='" + birth_date + '\'' +
            '}';
    }
}
