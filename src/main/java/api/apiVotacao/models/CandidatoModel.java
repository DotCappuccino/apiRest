package api.apiVotacao.models;

import javax.persistence.*;

@Table(name = "Candidatos")
@Entity(name = "Candidatos")
public class CandidatoModel {

    @Id
    @JoinColumn(name = "IdCandidato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdCandidato;

    @Column(name = "NomeCandidato", nullable = false, length = 150)
    public String NomeCandidato;

    @Column(name = "Partido", nullable = false, length = 20)
    public String Partido;

    @Column(name = "Uf", nullable = false, length = 3)
    public String Uf;

    protected CandidatoModel() {
    }

    public static CandidatoModel builder() {
        return new CandidatoModel();
    }

    protected void setNomeCandidato(String nomeCandidato) {
        this.NomeCandidato = nomeCandidato;
    }

    protected void setPartido(String partido) {
        this.Partido = partido;
    }

    protected void setUf(String uf) {
        this.Uf = uf;
    }


}
