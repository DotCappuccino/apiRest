package api.apiVotacao.models;

import javax.persistence.*;

@Table(name = "Eleitores")
@Entity(name = "Eleitores")
public class EleitorModel {

    @Id
    @JoinColumn(name = "IdEleitor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer IdEleitor;

    @Column(nullable = false, length = 150)
    public String NomeEleitor;

    @Column(nullable = false, length = 14)
    public String CpfEleitor;

    protected EleitorModel() {
    }

    public static EleitorModel builder() {
        return new EleitorModel();
    }

    protected void setNomeEleitor(String nomeEleitor) {
        this.NomeEleitor = nomeEleitor;
    }

    protected void setCpfEleitor(String cpfEleitor) {
        this.CpfEleitor = cpfEleitor;
    }

    public Integer getIdEleitor() {
        return IdEleitor;
    }

    public String getNomeEleitor() {
        return NomeEleitor;
    }

    public String getCpfEleitor() {
        return CpfEleitor;
    }
}
