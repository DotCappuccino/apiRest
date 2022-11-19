package api.apiVotacao.models;

public class CandidatoModelBuilder {

    private CandidatoModel candidatoModel;

    public CandidatoModelBuilder(){
        candidatoModel = new CandidatoModel();
    }

    public static CandidatoModelBuilder builder(){
        return new CandidatoModelBuilder();
    }

    public CandidatoModelBuilder NomeCandidato(String nomeCandidato){
        this.candidatoModel.setNomeCandidato(nomeCandidato);
        return this;
    }

    public CandidatoModelBuilder Partido(String partido){
        this.candidatoModel.setPartido(partido);
        return this;
    }

    public CandidatoModelBuilder Uf(String uf){
        this.candidatoModel.setUf(uf);
        return this;
    }

    public CandidatoModel build(){
        return this.candidatoModel;
    }


}


