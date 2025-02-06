package cadastroPessoas.backend.domain.exceptions;

import lombok.Getter;

@Getter
public class PessoaFisicaNotFoundException extends ModelNotFoundException{
    public PessoaFisicaNotFoundException(String message){
        super(message);
    }

    public PessoaFisicaNotFoundException(){
        super("User not found!");
    }
}