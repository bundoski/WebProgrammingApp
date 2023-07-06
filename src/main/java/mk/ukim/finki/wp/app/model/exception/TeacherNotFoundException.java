package mk.ukim.finki.wp.app.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TeacherNotFoundException extends RuntimeException{
    public TeacherNotFoundException(Long teacherId) {
        super(String.format("Teacher with ID: %d was not found in the repository.", teacherId));
    }
}
