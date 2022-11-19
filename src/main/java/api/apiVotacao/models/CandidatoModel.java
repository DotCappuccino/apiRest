package api.apiVotacao.models;

import api.apiVotacao.models.adapter.NullAdapter;

import javax.persistence.*;

@Entity(name = "Candidatos")
public class CandidatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdCandidato;

    @Column(nullable = false, length = 150)
    public String NomeCandidato;

    @Column(nullable = false, length = 20)
    public String Partido;

    @Column(nullable = false, length = 3)
    public String Uf;

    protected CandidatoModel(){
    }

    public static CandidatoModel builder(){
        return new CandidatoModel();
    }

    protected void setNomeCandidato(String nomeCandidato){
        this.NomeCandidato = nomeCandidato;
    }

    protected void setPartido(String partido){
        this.Partido = partido;
    }

    protected void setUf(String uf){
        this.Uf = uf;
    }


}
