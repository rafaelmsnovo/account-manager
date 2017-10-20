package br.com.accountmanager.entity;

import javax.persistence.Column;
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
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CNPJ", length = 14)
    private String cnpj;

    @Column(name = "COMPANY_NAME", length = 255)
    private String companyName;

    @Column(name = "FANTASY_NAME", length = 255)
    private String fantasyName;

    @Column(name = "CPF", length = 11)
    private String cpf;

    @Column(name = "FULL_NAME", length = 255)
    private String fullName;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    public PersonEntity() {
    }

    public PersonEntity(Long id, String cnpj, String companyName, String fantasyName, String cpf, String fullName, Date birthDate) {
        this.id = id;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.fantasyName = fantasyName;
        this.cpf = cpf;
        this.fullName = fullName;
        this.birthDate = birthDate;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override public String toString() {
        return "PersonEntity{" +
            "id=" + id +
            ", cnpj='" + cnpj + '\'' +
            ", companyName='" + companyName + '\'' +
            ", fantasyName='" + fantasyName + '\'' +
            ", cpf='" + cpf + '\'' +
            ", fullName='" + fullName + '\'' +
            ", birthDate=" + birthDate +
            '}';
    }
}
