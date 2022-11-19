package api.apiVotacao.models;

public class EleitorModelBuilder {

    private EleitorModel eleitorModel;

    public EleitorModelBuilder(){
        eleitorModel = new EleitorModel();
    }

    public static EleitorModelBuilder builder(){
        return new EleitorModelBuilder();
    }

    public EleitorModelBuilder NomeEleitor(String nomeEleitor){
        this.eleitorModel.setNomeEleitor(nomeEleitor);
        return this;
    }

    public EleitorModelBuilder CpfEleitor(String cpfEleitor){
        this.eleitorModel.setCpfEleitor(cpfEleitor);
        return this;
    }

    public EleitorModel build(){
        return this.eleitorModel;
    }

}
