package cadastroPessoas.backend.api.services.telefone;

import cadastroPessoas.backend.api.dtos.TelefoneRequest;
import cadastroPessoas.backend.api.dtos.TelefoneResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelefoneService {
    List<TelefoneResponse>findeTelefoneList();
    Optional<TelefoneResponse>findTelefoneId(UUID uuid);
    void createTelefone(TelefoneRequest telefoneRequest);
    void updateTelefoe(UUID uuid, TelefoneRequest telefoneRequest);
    void deleteTelefone(UUID uuid);
}
