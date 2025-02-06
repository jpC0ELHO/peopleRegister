package cadastroPessoas.backend.domain.entities;


import cadastroPessoas.backend.domain.entities.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Table(name = "tb_pessoafisica")
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PessoaFisica extends Entidade{
    @CPF
    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false,unique = true,length = 10)
    private String rg;

    @Column(nullable = false)
    private String primeiroNome;

    @Column(nullable = false)
    private String sobrenome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = false, length = 20)
    private Sexo sexo;

    @ElementCollection
    @CollectionTable(name = "tb_pessoafisica_email",
            joinColumns = @JoinColumn(name = "pessoafisica_cpf", referencedColumnName = "cpf"))
    @Column(name = "emails", nullable = false)
    private Set<String> email;

    @Column(nullable = false)
    @JoinColumn(name = "pessoafisica_cpf", referencedColumnName = "cpf")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<Telefone> telefone;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;
}
