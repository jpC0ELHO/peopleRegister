package cadastroPessoas.backend.domain.repositories;

import cadastroPessoas.backend.domain.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EnderecoRepository extends JpaRepository<Endereco, String> {
    Optional<Endereco>findEnderecoByCep(String cep);
}
