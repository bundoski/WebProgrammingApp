package mk.ukim.finki.wp.app.model.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException() {
        super("Error 404: Course not found");
    }
}
