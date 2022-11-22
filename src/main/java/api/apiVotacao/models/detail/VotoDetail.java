package api.apiVotacao.models.detail;

public class VotoDetail {

    public String NomeEleitor;
    public String CpfEleitor;
    public String IdCandidato;


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

    public String getIdCandidato() {
        return IdCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        IdCandidato = idCandidato;
    }

}
