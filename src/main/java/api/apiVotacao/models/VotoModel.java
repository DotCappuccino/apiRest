package api.apiVotacao.models;

import javax.persistence.*;

@Table(name = "Votos")
@Entity(name = "Votos")
public class VotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdVoto;


    @ManyToOne(targetEntity = CandidatoModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdCandidato", nullable = false)
    public Integer IdCandidato;

    public String NomeCandidato;

    @MapsId
    @OneToOne(targetEntity = EleitorModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdEleitor", nullable = false)
    public Integer IdEleitor;

    public static VotoModel builder() {
        return new VotoModel();
    }

    protected void setIdCandidato(Integer idCandidato) {
        this.IdCandidato = idCandidato;
    }

    protected void setIdEleitor(Integer idEleitor) {
        this.IdEleitor = idEleitor;
    }


}
