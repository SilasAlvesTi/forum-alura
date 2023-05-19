package alura.forum.api.infra.exception;

public class TopicoDuplicadoException extends RuntimeException{
    
    public TopicoDuplicadoException(String message) {
        super(message);
    }
}
