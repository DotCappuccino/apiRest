package api.apiVotacao.models;

import javax.persistence.*;

@Table(name = "Votos")
@Entity(name = "Votos")
public class VotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdVoto;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CandidatoModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdCandidato", nullable = false)
    public Integer IdCandidato;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, targetEntity = EleitorModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "IdEleitor", nullable = false, referencedColumnName = "id")
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
