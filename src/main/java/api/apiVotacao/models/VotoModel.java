package api.apiVotacao.models;

import api.apiVotacao.models.adapter.NullAdapter;

import javax.persistence.*;

@Entity(name = "Votos")
public class VotoModel implements NullAdapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdVoto;

    @Column(nullable = false)
    public Integer IdCandidato;

    @Column(nullable = false)
    public Integer IdEleitor;

    public static VotoModel builder(){
        return new VotoModel();
    }

    protected void setIdCandidato(Integer idCandidato){
        this.IdCandidato = idCandidato;
    }

    protected void setIdEleitor(Integer idEleitor){
        this.IdEleitor = idEleitor;
    }

    @Override
    public String getRegStr() {
        return null;
    }

    @Override
    public Integer getRegInt() {
        return null;
    }
}
