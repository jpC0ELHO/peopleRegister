package cadastroPessoas.backend.domain.entities.enums;

import lombok.Getter;

@Getter
public enum TelefoneTipo {
    PESSOAL("PESSOAL"),
    RESIDENCIAL("RESIDENCIAL"),
    OUTROS("OUTROS");

    private String name;

    TelefoneTipo(String name){
        this.name=name;
    }

}
