package mk.ukim.finki.wp.app.model.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException() {
        super("Error 404: Student not found");
    }
}
