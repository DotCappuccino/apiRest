package api.apiVotacao.models;

public class VotoModelBuilder {

    private VotoModel votoModel;

    public VotoModelBuilder() {
        votoModel = new VotoModel();
    }

    public static VotoModelBuilder builder() {
        return new VotoModelBuilder();
    }

    public VotoModelBuilder IdCandidato(Integer idCandidato) {
        this.votoModel.setIdCandidato(idCandidato);
        return this;
    }

    public VotoModelBuilder IdEleitor(Integer idEleitor) {
        this.votoModel.setIdEleitor(idEleitor);
        return this;
    }

    public VotoModel build() {
        return this.votoModel;
    }


}
