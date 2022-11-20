package api.apiVotacao.models.detail;

public class VotoDetail {

    public String NomeEleitor;
    public String CpfEleitor;
    public Integer IdCandidato;
    public Integer IdEleitor;


    public String getNomeEleitor() {
        return NomeEleitor;
    }

    public void setNomeEleitor(String nomeEleitor) {
        NomeEleitor = nomeEleitor;
    }

    public String getCpfEleitor() {
        return CpfEleitor;
    }

    public void setCpfEleitor(String cpfEleitor) {
        CpfEleitor = cpfEleitor;
    }

    public Integer getIdCandidato() {
        return IdCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        IdCandidato = idCandidato;
    }

    public Integer getIdEleitor() {
        return IdEleitor;
    }

    public void setIdEleitor(Integer idEleitor) {
        IdEleitor = idEleitor;
    }
}
