package cadastroPessoas.backend.domain.exceptions;

import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;

@Getter
public class ModelIntegrityViolationException extends DataIntegrityViolationException {
    public ModelIntegrityViolationException(String msg) {
        super(msg);
    }

    public ModelIntegrityViolationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
