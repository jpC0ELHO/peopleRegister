package cadastroPessoas.backend.api.controllers;

import cadastroPessoas.backend.api.dtos.PessoaFisicaRequest;
import cadastroPessoas.backend.api.dtos.PessoaFisicaResponse;
import cadastroPessoas.backend.api.services.pessoafisica.PessoaFisicaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/api/pessoaFisica/v1")
@RestController
@AllArgsConstructor
public class PessoaFisicaController {
    private final PessoaFisicaService pessoaFisicaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaFisicaResponse>>findCpfList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaFisicaService.findCpfList());
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<PessoaFisicaResponse>>findCpfById(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.FOUND).body(pessoaFisicaService.findById(uuid));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCpf(@RequestBody PessoaFisicaRequest pessoaFisicaRequest){
        pessoaFisicaService.createCpf(pessoaFisicaRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCpf(@PathVariable UUID uuid,@RequestBody PessoaFisicaRequest pessoaFisicaRequest){
        pessoaFisicaService.updateCpf(uuid,pessoaFisicaRequest);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCpf(@PathVariable UUID uuid){
        pessoaFisicaService.deletCpf(uuid);
    }

}

