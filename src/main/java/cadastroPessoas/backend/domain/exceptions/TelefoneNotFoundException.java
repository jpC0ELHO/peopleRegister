package cadastroPessoas.backend.domain.exceptions;

import lombok.Getter;

@Getter
public class TelefoneNotFoundException extends ModelNotFoundException{
    public TelefoneNotFoundException(String message){
        super(message);
    }

    public TelefoneNotFoundException(){
        super("Cellphone not found!");
    }
}
