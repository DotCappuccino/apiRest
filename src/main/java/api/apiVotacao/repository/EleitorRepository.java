package api.apiVotacao.repository;


import api.apiVotacao.models.EleitorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EleitorRepository extends CrudRepository<EleitorModel, Integer> {
}
