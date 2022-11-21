package api.apiVotacao.repository;

import api.apiVotacao.models.VotoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends CrudRepository<VotoModel, Integer> {


}
