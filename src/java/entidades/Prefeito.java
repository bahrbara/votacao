/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jricm
 */
@Entity
@Table(name = "PREFEITO")
@NamedQueries({
    @NamedQuery(name = "Prefeito.findAll", query = "SELECT p FROM Prefeito p")})
public class Prefeito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME")
    private String nome;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPF")
    private Integer cpf;
    @Column(name = "VOTOS")
    private Integer votos;
    @Size(max = 50)
    @Column(name = "PARTIDO")
    private String partido;
    @Size(max = 50)
    @Column(name = "VICE")
    private String vice;
    @Size(max = 50)
    @Column(name = "SLOGAN")
    private String slogan;
    @Size(max = 3)
    @Column(name = "NUMERO")
    private String numero;
    @Size(max = 250)
    @Column(name = "IMAGEM")
    private String imagem;

    public Prefeito() {
    }

    public Prefeito(Integer cpf) {
        this.cpf = cpf;
    }

    public Prefeito(Integer cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos() {
        this.votos++;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getVice() {
        return vice;
    }

    public void setVice(String vice) {
        this.vice = vice;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Prefeito{" + "nome=" + nome + ", cpf=" + cpf + ", votos=" + votos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.cpf);
        hash = 43 * hash + Objects.hashCode(this.votos);
        hash = 43 * hash + Objects.hashCode(this.partido);
        hash = 43 * hash + Objects.hashCode(this.vice);
        hash = 43 * hash + Objects.hashCode(this.slogan);
        hash = 43 * hash + Objects.hashCode(this.numero);
        hash = 43 * hash + Objects.hashCode(this.imagem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prefeito other = (Prefeito) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.partido, other.partido)) {
            return false;
        }
        if (!Objects.equals(this.vice, other.vice)) {
            return false;
        }
        if (!Objects.equals(this.slogan, other.slogan)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.imagem, other.imagem)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.votos, other.votos)) {
            return false;
        }
        return true;
    }

    
}
