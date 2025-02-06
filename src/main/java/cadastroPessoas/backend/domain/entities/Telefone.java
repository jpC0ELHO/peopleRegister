package cadastroPessoas.backend.domain.entities;

import cadastroPessoas.backend.domain.entities.enums.TelefoneTipo;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "tb_telefone")
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Telefone extends Entidade{

    @Column(name = "numero",unique = true,length = 18)
    private String number;

    @Enumerated
    @Column(name = "tipo_telefone")
    private TelefoneTipo telefoneTipo;
}
