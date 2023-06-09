package alura.forum.api.infra.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TradadorDeErros {

    @ExceptionHandler(TopicoDuplicadoException.class)
    public ResponseEntity<ExceptionMessage> trataErro409(TopicoDuplicadoException ex) {
        ExceptionMessage message = new ExceptionMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity
            .badRequest()
            .body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    private record DadosErroValidacao(String campo, String mensagem) {

        public DadosErroValidacao(FieldError erro) {
            this(
                erro.getField(),
                erro.getDefaultMessage()
            );
        }
    }
}
