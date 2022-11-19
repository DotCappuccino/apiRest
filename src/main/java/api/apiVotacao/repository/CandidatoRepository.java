package api.apiVotacao.repository;

import api.apiVotacao.models.CandidatoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends CrudRepository<CandidatoModel, Integer> {

}
