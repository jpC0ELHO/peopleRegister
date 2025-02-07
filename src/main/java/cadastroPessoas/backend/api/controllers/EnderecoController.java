package cadastroPessoas.backend.api.controllers;

import cadastroPessoas.backend.api.dtos.EnderecoRequest;
import cadastroPessoas.backend.api.dtos.EnderecoResponse;
import cadastroPessoas.backend.api.services.endereco.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping(value = "/api/endereco/v1")
@RestController
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnderecoResponse>>findEnderecoList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(enderecoService.findEnderecoList());
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<EnderecoResponse>>findEnderecoCep(@PathVariable String cep){
        return ResponseEntity.status(HttpStatus.FOUND).body(enderecoService.findEnderecoCep(cep));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEndereco(@RequestBody EnderecoRequest enderecoRequest){
        enderecoService.createEndereco(enderecoRequest);
    }

    @PutMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateEndereco(@PathVariable String cep, @RequestBody EnderecoRequest enderecoRequest){
        enderecoService.deleteEndereco(cep);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEndereco(@PathVariable String cep){
        enderecoService.deleteEndereco(cep);
    }

}
