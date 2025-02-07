package cadastroPessoas.backend.api.services.pessoajuridica;

import cadastroPessoas.backend.api.dtos.PessoaJuridicaRequest;
import cadastroPessoas.backend.api.dtos.PessoaJuridicaResponse;
import cadastroPessoas.backend.domain.exceptions.ModelIntegrityViolationException;
import cadastroPessoas.backend.domain.exceptions.PessoaJuridicaNotFoundException;
import cadastroPessoas.backend.domain.repositories.PessoaJuridicaRepository;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static cadastroPessoas.backend.api.dtos.PessoaJuridicaRequest.toEntidade;


@Service
@Log4j2
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class iPessoaJuridicaService implements PessoaJuridicaService{
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    @Override
    public List<PessoaJuridicaResponse> findCnpjList() {
        try {
            var findCnpjList=pessoaJuridicaRepository.findAll();
            if (findCnpjList.isEmpty()){
                log.error("List not found!");
                throw new PessoaJuridicaNotFoundException("List not found!");
            }
            return findCnpjList.stream().map(PessoaJuridicaResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.error("Error: {} .",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<PessoaJuridicaResponse> findById(UUID uuid) {
        var findCnpj=pessoaJuridicaRepository.findById(uuid);
        if (findCnpj.isEmpty()){
            log.warn("ID: {} not found!",uuid);
            throw new PessoaJuridicaNotFoundException("ID not found!");
        }
        return findCnpj.map(PessoaJuridicaResponse::toResponse);
    }

    @Override
    public void creatCnpj(PessoaJuridicaRequest pessoaJuridicaRequest) {
        var findCnpj=pessoaJuridicaRepository.findByCnpj(pessoaJuridicaRequest.cnpj());
        if (findCnpj.isPresent()){
            log.warn("CNPJ:{} already exists!",pessoaJuridicaRequest.cnpj());
            throw new ModelIntegrityViolationException("CNPJ already exists!");
        }
        pessoaJuridicaRepository.save(toEntidade(pessoaJuridicaRequest));
    }

    @Override
    public void updateCnpj(UUID uuid, PessoaJuridicaRequest pessoaJuridicaRequest) {
        var findId=pessoaJuridicaRepository.findById(uuid);
        if (findId.isEmpty()||findId==null){
            log.warn("ID: {} not found!",uuid);
            throw new PessoaJuridicaNotFoundException("ID not found!");
        }
        findId.map(pessoaJuridica -> {
            pessoaJuridica.setCnpj(pessoaJuridicaRequest.cnpj());
            pessoaJuridica.setRazaoSocial(pessoaJuridicaRequest.razaoSocial());
            pessoaJuridica.setNomeFantasia(pessoaJuridicaRequest.nomeFantasia());
            pessoaJuridica.setResponsavel(pessoaJuridicaRequest.responsavel());
            pessoaJuridica.setEmail(pessoaJuridicaRequest.email());
            pessoaJuridica.setTelefone(pessoaJuridicaRequest.telefone());
            pessoaJuridica.setEndereco(pessoaJuridicaRequest.endereco());
            return pessoaJuridicaRepository.save(pessoaJuridica);
        });
    }

    @Override
    public void deleteCnpj(UUID uuid) {
        try {
            var findId=pessoaJuridicaRepository
                    .findById(uuid)
                    .orElseThrow(()-> new PessoaJuridicaNotFoundException("ID not found!"));
            pessoaJuridicaRepository.delete(findId);
        }catch (RuntimeException e){
            log.error("Error: {}!",e.getMessage());
        }
    }
}