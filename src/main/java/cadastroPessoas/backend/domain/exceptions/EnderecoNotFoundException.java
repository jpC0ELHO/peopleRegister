package cadastroPessoas.backend.domain.exceptions;

import lombok.Getter;

@Getter
public class EnderecoNotFoundException extends ModelNotFoundException{
    public EnderecoNotFoundException(String message){
        super(message);
    }
    public EnderecoNotFoundException(){
        super("Location not found!");
    }
}
