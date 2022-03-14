package njnu.edu.example.dao;

import njnu.edu.example.pojo.Student;
import njnu.edu.example.pojo.dto.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/08/22:00
 * @Description:
 */
@Repository
public interface StudentMapper {
    List<Student> getAllStudents();
    int insertData(StudentDTO studentDTO);
}
