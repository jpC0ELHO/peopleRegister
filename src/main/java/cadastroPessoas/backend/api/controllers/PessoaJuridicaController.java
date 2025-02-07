package cadastroPessoas.backend.api.controllers;

import cadastroPessoas.backend.api.dtos.PessoaJuridicaRequest;
import cadastroPessoas.backend.api.dtos.PessoaJuridicaResponse;
import cadastroPessoas.backend.api.services.pessoajuridica.PessoaJuridicaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pessoajuridica/v1")
public class PessoaJuridicaController {
    private final PessoaJuridicaService pessoaJuridicaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaJuridicaResponse>>findCnpjList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaJuridicaService.findCnpjList());
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PessoaJuridicaResponse>>findCpnjId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaJuridicaService.findById(uuid));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCnpj(@RequestBody PessoaJuridicaRequest pessoaJuridicaRequest){
        pessoaJuridicaService.creatCnpj(pessoaJuridicaRequest);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCnpj(@PathVariable UUID uuid, @RequestBody PessoaJuridicaRequest pessoaJuridicaRequest){
        pessoaJuridicaService.updateCnpj(uuid,pessoaJuridicaRequest);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCpnj(@PathVariable UUID uuid){
        pessoaJuridicaService.deleteCnpj(uuid);
    }
}

