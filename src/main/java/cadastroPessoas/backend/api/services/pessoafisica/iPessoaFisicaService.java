package cadastroPessoas.backend.api.services.pessoafisica;

import cadastroPessoas.backend.api.dtos.PessoaFisicaRequest;
import cadastroPessoas.backend.api.dtos.PessoaFisicaResponse;
import cadastroPessoas.backend.domain.exceptions.ModelIntegrityViolationException;
import cadastroPessoas.backend.domain.exceptions.PessoaFisicaNotFoundException;
import cadastroPessoas.backend.domain.repositories.PessoaFisicaRepository;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static cadastroPessoas.backend.api.dtos.PessoaFisicaRequest.toEntidade;


@Service
@Log4j2
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@EntityListeners(AuditingEntityListener.class)
public class iPessoaFisicaService implements PessoaFisicaService{
    private final PessoaFisicaRepository pessoaFisicaRepository;
    @Override
    public List<PessoaFisicaResponse> findCpfList() {
        try{
            var findListCpf=pessoaFisicaRepository.findAll();
            if (findListCpf.isEmpty()){
                log.info("List not found!");
            }
            return findListCpf.stream().map(PessoaFisicaResponse::toResponse).toList();
        }catch (RuntimeException e){
            log.warn("Error: {} ",e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Optional<PessoaFisicaResponse> findById(UUID uuid) {
        var findListCpf=pessoaFisicaRepository.findById(uuid);
        if (findListCpf.isEmpty()){
            log.info("ID:{} not found!",uuid);
            throw new PessoaFisicaNotFoundException("ID not found!");
        }
        return findListCpf.map(PessoaFisicaResponse::toResponse);
    }

    @Override
    public void createCpf(PessoaFisicaRequest pessoaFisicaRequest) {
        var findCpf=pessoaFisicaRepository.findPessoaByCpf(pessoaFisicaRequest.cpf());
        if (findCpf.isPresent()){
            log.info("CPF: {} already exists!",pessoaFisicaRequest.cpf());
            throw new ModelIntegrityViolationException("CPF already exists!");
        }
        pessoaFisicaRepository.save(toEntidade(pessoaFisicaRequest));
    }

    @Override
    public void updateCpf(UUID uuid, PessoaFisicaRequest pessoaFisicaRequest) {
        var findCpfId=pessoaFisicaRepository.findPessoaById(uuid);
        if (findCpfId.isEmpty()){
            log.info("ID {} not found!",uuid);
            throw new PessoaFisicaNotFoundException("ID not found!");
        }
        findCpfId.map(pessoaFisica -> {
            pessoaFisica.setCpf(pessoaFisicaRequest.cpf());
            pessoaFisica.setRg(pessoaFisica.getRg());
            pessoaFisica.setPrimeiroNome(pessoaFisica.getPrimeiroNome());
            pessoaFisica.setSobrenome(pessoaFisica.getSobrenome());
            pessoaFisica.setSexo(pessoaFisica.getSexo());
            pessoaFisica.setEmails(pessoaFisica.getEmails());
            pessoaFisica.setTelefones(pessoaFisica.getTelefones());
            pessoaFisica.setDataNascimento(pessoaFisica.getDataNascimento());
            pessoaFisica.setEndereco(pessoaFisica.getEndereco());
            return pessoaFisicaRepository.save(pessoaFisica);
        });
    }

    @Override
    public void deletCpf(UUID uuid) {
        try {
            var findCpfId=pessoaFisicaRepository
                    .findPessoaById(uuid)
                    .orElseThrow(()-> new PessoaFisicaNotFoundException("ID not found!"));
            pessoaFisicaRepository.delete(findCpfId);

        }catch (RuntimeException e){
            log.error("Error: {} ;",e.getMessage());
        }

    }
}