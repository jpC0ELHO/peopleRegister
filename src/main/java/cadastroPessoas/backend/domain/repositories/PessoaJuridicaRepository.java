package cadastroPessoas.backend.domain.repositories;

import cadastroPessoas.backend.domain.entities.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, UUID> {
    Optional<PessoaJuridica> findById(UUID uuid);
    Optional<PessoaJuridica>findByCnpj(String cnpj);
}
