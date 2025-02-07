package cadastroPessoas.backend.api.services.endereco;

import cadastroPessoas.backend.api.dtos.EnderecoRequest;
import cadastroPessoas.backend.api.dtos.EnderecoResponse;


import java.util.List;
import java.util.Optional;

public interface EnderecoService {
    List<EnderecoResponse>findEnderecoList();
    Optional<EnderecoResponse>findEnderecoCep(String cep);
    void createEndereco(EnderecoRequest enderecoRequest);
    void updateEndereco(String cep,EnderecoRequest enderecoRequest);
    void deleteEndereco(String cep);
}
