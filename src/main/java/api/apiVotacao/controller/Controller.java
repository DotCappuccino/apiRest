package api.apiVotacao.controller;

import api.apiVotacao.models.CandidatoModel;
import api.apiVotacao.models.CandidatoModelBuilder;
import api.apiVotacao.models.adapter.NullAdapter;
import api.apiVotacao.models.detail.DeputadosDetail;
import api.apiVotacao.repository.CandidatoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
public class Controller {
    private ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private CandidatoRepository candidatoRepository;

    /*
     * VERIFICA SE A APLICAÇÃO ESTÁ ATIVA
     * */
    @GetMapping(path = "/api/status")
    public String validaConexao() {
        return "API online !!";
    }

    /*
     * VALIDA O TAMANHO DO CPF E RETORNA TRUE OU FALSE
     * */
    @GetMapping(path = "api/validarCpf/{cpf}")
    public Boolean validarCpf(@PathVariable String cpf) throws Exception {

        Integer cpfTamanho = pegarNumerosCpf(cpf).length();

        return cpfTamanho == 11 ? true : false;
    }

    public String pegarNumerosCpf(String cpf) {
        //REMOVE A MASCARA DO CPF
        String cpfNumeros = cpf.replaceAll("[\\.-]", "");

        return cpfNumeros;
    }

    /*
     * CONSULTA A API DA CAMARA E RETORNA OS CANDIDATOS (Apenas Deputados)
     * */
    public String cadastrarCandidatos() throws JsonProcessingException {
        //FAZ COM QUE O MAPPER POSSA SE TORNAR UM ARRAY
        MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        String Uri = "https://dadosabertos.camara.leg.br/api/v2/deputados?itens=2";
        String msg = "";

        //NOVA REQUISIÇÃO
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(Uri, String.class);

        List<DeputadosDetail> deputadosModel = MAPPER.readValue(response,
                MAPPER.getTypeFactory().constructCollectionType(List.class, DeputadosDetail.class));

        for (int i = 1; i <= deputadosModel.size(); i++) {
            //CRIANDO NOVO CANDIDATO
            CandidatoModel candidatoModel = CandidatoModelBuilder.builder()
                    .NomeCandidato(deputadosModel.get(i).nome)
                    .Partido(deputadosModel.get(i).siglaPartido)
                    .Uf(deputadosModel.get(i).siglaUf)
                    .build();
            //EXECUTA O INSERT
            candidatoRepository.save(candidatoModel);
        }

        return "INSERT CANDIDATOS, FINALIZADO";
    }

    //ADAPTER PARA VALIDAR CAMPOS NULOS
    private static class NullProcessStr {

        Boolean validaNullStr(NullAdapter adapter) {
            return Objects.isNull(adapter.getRegStr()) ? true : false;
        }

        Boolean validaNullInt(NullAdapter adapter) {
            return Objects.isNull(adapter.getRegInt()) ? true : false;
        }
    }


}
