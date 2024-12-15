package vn.techzen.academy_pnv_12.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    EMPLOYEE_NOT_EXIST(344334, "Employee not found", HttpStatus.NOT_FOUND),
    BAD_REQUEST(22323, "Empty data", HttpStatus.BAD_REQUEST),
    DEPARTMENT_NOT_EXIST(26535, "Empty data", HttpStatus.NOT_FOUND),
    STUDENT_NOT_EXIST(4040404, "Student not found", HttpStatus.NOT_FOUND);

    Integer code;
    String message;
    HttpStatus status;
}
