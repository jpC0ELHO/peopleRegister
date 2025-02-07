package cadastroPessoas.backend.api.controllers;

import cadastroPessoas.backend.api.dtos.TelefoneRequest;
import cadastroPessoas.backend.api.dtos.TelefoneResponse;
import cadastroPessoas.backend.api.services.telefone.TelefoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/api/telefone/v1")
@RestController
@AllArgsConstructor
public class TelefoneController {
    private final TelefoneService telefoneService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TelefoneResponse>>findTelefoneList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(telefoneService.findeTelefoneList());
    }

    @GetMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<TelefoneResponse>>findTelefoneId(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.FOUND).body(telefoneService.findTelefoneId(uuid));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createTelefone(@RequestBody TelefoneRequest telefoneRequest){
        telefoneService.createTelefone(telefoneRequest);
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateTelefone(@PathVariable UUID uuid, @RequestBody TelefoneRequest telefoneRequest){
        telefoneService.updateTelefoe(uuid,telefoneRequest);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTelefone(@PathVariable UUID uuid){
        telefoneService.deleteTelefone(uuid);
    }
}
