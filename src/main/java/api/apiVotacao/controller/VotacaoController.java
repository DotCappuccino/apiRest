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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VotacaoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EleitorRepository eleitorRepository;

    @GetMapping(path = "api/todosCandidatos")
    public Iterable<CandidatoModel> todosCandidatos() {
        return candidatoRepository.findAll();
    }

    @GetMapping(path = "api/todosVotos")
    public List<Map<String, String>> todosVotos() {

        List<Object[]> query = entityManager.createQuery("SELECT C.NomeCandidato, COUNT(C.NomeCandidato) AS Quantidade " +
                "FROM Votos V " +
                "INNER JOIN FETCH Candidatos C ON C.IdCandidato = V.IdCandidato " +
                "GROUP BY C.NomeCandidato").getResultList();

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();


        for (Object[] var : query) {

            Map<String, String> auxR = new HashMap<String, String>();

            System.out.println("NomeCandidato: " + var[0]);
            auxR.put("NomeCandidato", (String) var[0]);


            System.out.println("Quantidade: " + var[1]);
            auxR.put("Quantidade", Long.toString((Long) var[1]));
            result.add(auxR);

        }

        return result;
    }

    @PostMapping(path = "api/votar/post")
    public String votar(@RequestBody VotoDetail voto) {

        EleitorModel eleitorModel = EleitorModelBuilder.builder()
                .NomeEleitor(voto.getNomeEleitor())
                .CpfEleitor(voto.getCpfEleitor())
                .build();

        eleitorRepository.save(eleitorModel);

        Object id = entityManager.createQuery("SELECT MAX(IdEleitor) AS IdEleitor " +
                                                   "FROM Eleitores V ").getSingleResult();

        System.out.println("id: " + id.toString() + " class: " + id.getClass());


        VotoModel votoModel = new VotoModel();
        votoModel.IdCandidato = voto.getIdCandidato();
        votoModel.IdEleitor = (Integer) id;
//        VotoModel votoModel = VotoModelBuilder.builder()
//                .IdCandidato(voto.getIdCandidato())
//                .IdEleitor((Integer) id)
//                .build();
        votoRepository.save(votoModel);

        return "VOTO REALIZADO COM SUCESSO !";
    }


}
