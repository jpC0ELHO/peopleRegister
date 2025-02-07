package cadastroPessoas.backend.api.dtos;

import cadastroPessoas.backend.domain.entities.Endereco;
import cadastroPessoas.backend.domain.entities.enums.EstadoBr;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"cep","logradouro","numero"
        ,"complemento","bairro","cidade","estado"})
public record EnderecoResponse(
        String logradouro,
        String numero,
        String complemento,
        String cidade,
        String bairro,
        EstadoBr estado,
        String cep
) {
    public static EnderecoResponse toResponse(Endereco endereco){
        if (endereco==null){
            return null;
        }
        return new EnderecoResponse(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
}
