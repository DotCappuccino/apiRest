package api.apiVotacao.controller;

import api.apiVotacao.models.*;
import api.apiVotacao.models.detail.VotoDetail;
import api.apiVotacao.repository.CandidatoRepository;
import api.apiVotacao.repository.EleitorRepository;
import api.apiVotacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VotacaoController {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EleitorRepository eleitorRepository;

    @GetMapping(path = "api/todosCandidatos")
    public Iterable<CandidatoModel> todosCandidatos(){
        return candidatoRepository.findAll();
    }

    @PostMapping(path = "api/votar/post")
    public String votar(@RequestBody VotoDetail voto){

        EleitorModel eleitorModel = EleitorModelBuilder.builder()
                .NomeEleitor(voto.getNomeEleitor())
                .CpfEleitor(voto.getCpfEleitor())
                .build();
        eleitorRepository.save(eleitorModel);

        VotoModel votoModel = VotoModelBuilder.builder()
                .IdCandidato(voto.getIdCandidato())
                .IdEleitor(voto.getIdEleitor())
                .build();
        votoRepository.save(votoModel);

        return "VOTO REALIZADO COM SUCESSO !";
    }

}
