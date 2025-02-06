package cadastroPessoas.backend.api.dtos;

import cadastroPessoas.backend.domain.entities.PessoaFisica;
import cadastroPessoas.backend.domain.entities.Telefone;
import cadastroPessoas.backend.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","cpf","rg","primeiroNome"
        ,"sobrenome","sexo","emails","telefones","dataNascimento"
        ,"createdBy","lastModifiedBy","createdAt","updateAt"})
public record PessoaFisicaResponse (
        UUID uuid,
        String cpf,
        String rg,
        String primeiroNome,
        String sobrenome,
        Sexo sexo,
        Set<String> emails,
        Set<Telefone> telefones,
        LocalDate dataNascimento,
        String cretedBy,
        String lastModifiedBy,
        LocalDateTime cretedAt,
        LocalDateTime updateAt

){
    public static PessoaFisicaResponse toResponse(PessoaFisica pessoaFisica){
        if (pessoaFisica==null){
            return null;
        }
        return new PessoaFisicaResponse(
                pessoaFisica.getUuid(),
                pessoaFisica.getCpf(),
                pessoaFisica.getRg(),
                pessoaFisica.getPrimeiroNome(),
                pessoaFisica.getSobrenome(),
                pessoaFisica.getSexo(),
                pessoaFisica.getEmails(),
                pessoaFisica.getTelefones(),
                pessoaFisica.getDataNascimento(),
                pessoaFisica.getCreatedBy(),
                pessoaFisica.getLastModifiedBy(),
                pessoaFisica.getCreatedAt(),
                pessoaFisica.getUpdateAt()
        );
    }
}