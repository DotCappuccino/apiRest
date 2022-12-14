package api.apiVotacao.controller;

import api.apiVotacao.models.detail.CandidatosDetail;
import api.apiVotacao.models.detail.VotoDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControllerWeb {

    private ObjectMapper MAPPER = new ObjectMapper();

    /*
     * ROTAS
     * */
    @GetMapping(path = "/")
    public String indexPrimario() {
        return "index";
    }

    @GetMapping(path = "/index")
    public String indexSecundario() {
        return "index";
    }

    @GetMapping(path = "/votacao")
    public ModelAndView votacaoGet() throws JsonProcessingException {
        //FAZ COM QUE O MAPPER POSSA SE TORNAR UM ARRAY
        MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        /*
         * DADOS DA CONEXÃO
         * */
        String Uri = "http://localhost:8080/api/todosCandidatos";

        //NOVA REQUISIÇÃO
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(Uri, String.class);

        List<CandidatosDetail> candidatosModel = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, CandidatosDetail.class));

        ModelAndView mv = new ModelAndView("votacao");
        mv.addObject("candidatos", candidatosModel);

        return mv;
    }

    @GetMapping(path = "/consultar")
    public String consultarGet() {
        return "consultar";
    }

    @GetMapping(path = "/parcial")
    public ModelAndView parcialGet() throws JsonProcessingException {
        //FAZ COM QUE O MAPPER POSSA SE TORNAR UM ARRAY
        MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        /*
         * DADOS DA CONEXÃO
         * */
        String Uri = "http://localhost:8080/api/todosVotos";
        String msg = "";

        //NOVA REQUISIÇÃO
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(Uri, String.class);

        List<Map<String, String>> votos = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Map.class));

        ModelAndView mv = new ModelAndView("Parcial_Votos");
        mv.addObject("votos", votos);

        return mv;
    }

    /*
     * REDIRECTS
     * */
    @PostMapping(path = "/")
    public String indexPost(String btn) {

        String retorno = "redirect:/";

        if (btn.equalsIgnoreCase("btnVotar")) retorno += "votacao";
        if (btn.equalsIgnoreCase("btnConsultar")) retorno += "consultar";
        if (btn.equalsIgnoreCase("btnParcial")) retorno += "parcial";

        System.out.println("\nRetorno(/): " + retorno);

        return retorno;
    }

    @PostMapping(path = "/votacao")
    public String votacaoPost(String btn, VotoDetail voto) throws MinhaException {

        ObjectMapper mapper = new ObjectMapper();
        String retorno = "redirect:/";

        if (btn.equalsIgnoreCase("Inserir")) {

            Map<String, String> json = new HashMap<String, String>();
            String votoJson = "";

            json.put("NomeEleitor", voto.getNomeEleitor());
            json.put("CpfEleitor", voto.getCpfEleitor());
            json.put("IdCandidato", voto.getIdCandidato());

            try {
                votoJson = mapper.writeValueAsString(json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            sendPost("http://localhost:8080/api/votar/post", votoJson);
        }
        return retorno;
    }

    @PostMapping(path = "/parcial")
    public String votacaoPost(String btn) {

        String retorno = "redirect:/";

        retorno += btn.equalsIgnoreCase("Voltar") ? "" : "parcial";

        return retorno;
    }

    @PostMapping(path = "/consultar")
    public String consultarPost(String btn) {

        String retorno = "redirect:/";

        retorno += btn.equalsIgnoreCase("Consultar") ? "consultar" : "";

        return retorno;
    }

    public String sendPost(String url, String json) throws MinhaException {

        try {

            HttpURLConnection request = (HttpURLConnection) new URL(url).openConnection();

            try {
                //DEFINE QUE A CONEXÃO VAI TER ENVIO E RETORNO
                request.setDoOutput(true);
                request.setDoInput(true);

                request.setRequestProperty("Content-Type", "application/json");

                //DEFINE QUE A REQUISIÇÃO SERÁ POST
                request.setRequestMethod("POST");

                //REALIZA A CONEXÃO
                request.connect();

                // Escreve o objeto JSON usando o OutputStream
                try (OutputStream outputStream = request.getOutputStream()) {
                    outputStream.write(json.getBytes("UTF-8"));
                }

                // Caso você queira usar o código HTTP para fazer alguma coisa, descomente esta linha.
                //int response = request.getResponseCode();

                return readResponse(request);

            } finally {
                request.disconnect();
            }

        } catch (IOException ex) {
            throw new MinhaException(ex);
        }

    }

    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }

    public static class MinhaException extends Exception {
        private static final long serialVersionUID = 1L;

        public MinhaException(Throwable cause) {
            super(cause);
        }
    }

}
