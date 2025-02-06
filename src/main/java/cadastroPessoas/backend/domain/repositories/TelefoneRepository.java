package cadastroPessoas.backend.domain.repositories;

import cadastroPessoas.backend.domain.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TelefoneRepository extends JpaRepository<Telefone, UUID> {
    Optional<Telefone> findByid(UUID uuid);
    Optional<Telefone>findByNumber(String number);
}