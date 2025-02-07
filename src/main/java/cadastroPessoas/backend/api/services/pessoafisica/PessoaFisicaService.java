package cadastroPessoas.backend.api.services.pessoafisica;

import cadastroPessoas.backend.api.dtos.PessoaFisicaRequest;
import cadastroPessoas.backend.api.dtos.PessoaFisicaResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaFisicaService {
    List<PessoaFisicaResponse> findCpfList();
    Optional<PessoaFisicaResponse>findById(UUID uuid);
    void createCpf(PessoaFisicaRequest pessoaFisicaRequest);
    void updateCpf(UUID uuid,PessoaFisicaRequest pessoaFisicaRequest);
    void deletCpf(UUID uuid);
}