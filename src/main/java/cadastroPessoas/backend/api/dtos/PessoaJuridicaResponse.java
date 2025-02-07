package cadastroPessoas.backend.api.dtos;

import cadastroPessoas.backend.domain.entities.Endereco;
import cadastroPessoas.backend.domain.entities.PessoaJuridica;
import cadastroPessoas.backend.domain.entities.Telefone;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","cnpj","razaoSocial","nomeFantasia"
        ,"responsavel","email","telefone","status","endereco"
        ,"createdBy","lastModifiedBy","createdAt","updateAt"})
public record PessoaJuridicaResponse(
        UUID uuid,
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        String responsavel,
        Set<String>email,
        Set<Telefone>telefone,
        String status,
        Endereco endereco,
        String cretedBy,
        String lastModifiedBy,
        LocalDateTime cretedAt,
        LocalDateTime updateAt
) {
    public static PessoaJuridicaResponse toResponse(PessoaJuridica pessoaJuridica){
        if (pessoaJuridica==null){
            return null;
        }
        return new PessoaJuridicaResponse(
                pessoaJuridica.getUuid(),
                pessoaJuridica.getCnpj(),
                pessoaJuridica.getRazaoSocial(),
                pessoaJuridica.getNomeFantasia(),
                pessoaJuridica.getResponsavel(),
                pessoaJuridica.getEmail(),
                pessoaJuridica.getTelefone(),
                pessoaJuridica.getStatus(),
                pessoaJuridica.getEndereco(),
                pessoaJuridica.getCreatedBy(),
                pessoaJuridica.getLastModifiedBy(),
                pessoaJuridica.getCreatedAt(),
                pessoaJuridica.getUpdateAt()
        );
    }
}
