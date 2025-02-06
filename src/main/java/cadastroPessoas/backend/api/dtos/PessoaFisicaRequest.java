package cadastroPessoas.backend.api.dtos;

import cadastroPessoas.backend.domain.entities.Endereco;
import cadastroPessoas.backend.domain.entities.PessoaFisica;
import cadastroPessoas.backend.domain.entities.Telefone;
import cadastroPessoas.backend.domain.entities.enums.Sexo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PessoaFisicaRequest (
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotBlank
        String primeiroNome,
        @NotBlank
        String sobrenome,
        @NotNull
        @NotBlank
        Sexo sexo,
        @NotNull
        @NotBlank
        Set<String> emails,
        @NotNull
        Set<Telefone> telefones,
        @NotNull
        LocalDate dataNascimento,
        @Embedded
        @NotNull
        Endereco endereco
){
    public static PessoaFisica toEntidade(PessoaFisicaRequest pessoaFisicaRequest){
        if (pessoaFisicaRequest==null){
            return null;
        }
        return new PessoaFisica(
                pessoaFisicaRequest.cpf(),
                pessoaFisicaRequest.rg(),
                pessoaFisicaRequest.primeiroNome(),
                pessoaFisicaRequest.sobrenome(),
                pessoaFisicaRequest.sexo(),
                pessoaFisicaRequest.emails(),
                pessoaFisicaRequest.telefones(),
                pessoaFisicaRequest.dataNascimento(),
                pessoaFisicaRequest.endereco()
        );
    }
}