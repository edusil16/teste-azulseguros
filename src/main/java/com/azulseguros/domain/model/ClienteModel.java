package com.azulseguros.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TB_CLIENTE")
@NamedQueries({
        @NamedQuery(name="ClienteModel.findAll", query="SELECT c FROM ClienteModel c"),
        @NamedQuery(name="ClienteModel.findByEmail", query="SELECT c FROM ClienteModel c WHERE c.email =:email")
})
public class ClienteModel implements Serializable {
    @Id
    @GeneratedValue
    @Column(unique=true, nullable=false)
    private Long id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String cpf;
    @OneToOne
    @MapsId
    EnderecoModel endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
