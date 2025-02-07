package cadastroPessoas.backend.api.services.endereco;

import cadastroPessoas.backend.api.dtos.EnderecoRequest;
import cadastroPessoas.backend.api.dtos.EnderecoResponse;
import cadastroPessoas.backend.domain.exceptions.EnderecoNotFoundException;
import cadastroPessoas.backend.domain.exceptions.ModelIntegrityViolationException;
import cadastroPessoas.backend.domain.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static cadastroPessoas.backend.api.dtos.EnderecoRequest.toEntidade;

@Service
@Log4j2
@AllArgsConstructor
public class iEnderecoService implements EnderecoService{
    private final EnderecoRepository enderecoRepository;

    @Override
    public List<EnderecoResponse> findEnderecoList() {
        try {
            var findEnderecoList=enderecoRepository.findAll();
            if (findEnderecoList.isEmpty()){
                log.error("List not found!");
                throw new EnderecoNotFoundException("List not found!");
            }
            return findEnderecoList.stream().map(EnderecoResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error :{} !",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<EnderecoResponse> findEnderecoCep(String cep) {
        var findCep=enderecoRepository.findEnderecoByCep(cep);
        if (findCep.isEmpty()){
            log.error("CEP: {} not found!",cep);
            throw new EnderecoNotFoundException("CEP not found!");
        }
        return findCep.map(EnderecoResponse::toResponse);
    }

    @Override
    public void createEndereco(EnderecoRequest enderecoRequest) {
        var findEnderecoCep=enderecoRepository.findEnderecoByCep(enderecoRequest.cep());
        if (findEnderecoCep.isPresent()){
            log.info("CEP:{} already",enderecoRequest.cep());
            throw new ModelIntegrityViolationException("Integrity violation!");
        }
        enderecoRepository.save(toEntidade(enderecoRequest));
    }

    @Override
    public void updateEndereco(String cep, EnderecoRequest enderecoRequest) {
        var findEnderecoCep=enderecoRepository.findEnderecoByCep(enderecoRequest.cep());
        if (findEnderecoCep.isEmpty()){
            log.info("CEP:{} not found!",cep);
        }
        findEnderecoCep.map(endereco -> {
            endereco.setLogradouro(enderecoRequest.logradouro());
            endereco.setNumero(enderecoRequest.numero());
            endereco.setComplemento(enderecoRequest.complemento());
            endereco.setBairro(enderecoRequest.bairro());
            endereco.setCidade(enderecoRequest.cidade());
            endereco.setEstado(enderecoRequest.estado());
            endereco.setCep(enderecoRequest.cep());
            return enderecoRepository.save(endereco);
        });
    }

    @Override
    public void deleteEndereco(String cep) {
        try {
            var findEnderecoByCep=enderecoRepository
                    .findEnderecoByCep(cep)
                    .orElseThrow(()-> new EnderecoNotFoundException("CEP not found!"));

            enderecoRepository.delete(findEnderecoByCep);
        }catch (RuntimeException e){
            log.error("Error:{} !",e.getMessage());
        }
    }
}

