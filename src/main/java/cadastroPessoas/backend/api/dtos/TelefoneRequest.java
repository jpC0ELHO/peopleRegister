package cadastroPessoas.backend.api.dtos;

import cadastroPessoas.backend.domain.entities.Telefone;
import cadastroPessoas.backend.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TelefoneRequest(
        @NotBlank
        String number,
        @NotBlank
        @NotNull
        TelefoneTipo telefoneTipo
) {
    public static Telefone toEntidade(TelefoneRequest telefoneRequest){
        if (telefoneRequest==null){
            return null;
        }
        return new Telefone(
                telefoneRequest.number(),
                telefoneRequest.telefoneTipo()
        );
    }
}
