package cadastroPessoas.backend.api.dtos;

import cadastroPessoas.backend.domain.entities.Telefone;
import cadastroPessoas.backend.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"uuid","number","telefoneTipo"
        ,"createdBy","lastModifiedBy","createdAt","updateAt"})
public record TelefoneResponse(
        UUID uuid,
        String number,
        TelefoneTipo telefoneTipo,
        String cretedBy,
        String lastModifiedBy,
        LocalDateTime cretedAt,
        LocalDateTime updateAt
) {
    public static TelefoneResponse toResponse(Telefone telefone){
        if (telefone==null){
            return null;
        }
        return new TelefoneResponse(
                telefone.getUuid(),
                telefone.getNumber(),
                telefone.getTelefoneTipo(),
                telefone.getCreatedBy(),
                telefone.getLastModifiedBy(),
                telefone.getCreatedAt(),
                telefone.getUpdateAt()
        );
    }
}