package api.apiVotacao.models;

import api.apiVotacao.models.adapter.NullAdapter;

import javax.persistence.*;

@Entity(name = "Eleitores")
public class EleitorModel implements NullAdapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdEleitor;

    @Column(nullable = false, length = 150)
    public String NomeEleitor;

    @Column(nullable = false, length = 14)
    public String CpfEleitor;

    protected EleitorModel(){
    }

    public static EleitorModel builder(){
        return new EleitorModel();
    }

    protected void setNomeEleitor(String nomeEleitor){
        this.NomeEleitor = nomeEleitor;
    }

    protected void setCpfEleitor(String cpfEleitor){
        this.CpfEleitor = cpfEleitor;
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
