package cadastroPessoas.backend.domain.repositories;

import cadastroPessoas.backend.domain.entities.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaFisicaRepository extends JpaRepository< PessoaFisica, UUID> {
    Optional<PessoaFisica> findPessoaById(UUID uuid);
    Optional<PessoaFisica>findPessoaByCpf(String cpf);
}
