package mk.ukim.finki.wp.app.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED)
public class CourseAlreadyExistsException extends RuntimeException{
    public CourseAlreadyExistsException(String name) {
        super(String.format("Course with name '%s' already exists", name));
    }
}
