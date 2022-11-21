package api.apiVotacao.models.detail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidatosDetail {


    public Integer IdCandidato;

    public String NomeCandidato;

    public String Partido;

    public String Uf;

    public Integer getIdCandidato() {
        return IdCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        IdCandidato = idCandidato;
    }

    public String getNomeCandidato() {
        return NomeCandidato;
    }

    public void setNomeCandidato(String nomeCandidato) {
        NomeCandidato = nomeCandidato;
    }

    public String getPartido() {
        return Partido;
    }

    public void setPartido(String partido) {
        Partido = partido;
    }

    public String getUf() {
        return Uf;
    }

    public void setUf(String uf) {
        Uf = uf;
    }
}
