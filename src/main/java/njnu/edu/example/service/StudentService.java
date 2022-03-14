package njnu.edu.example.service;

import njnu.edu.example.common.enums.ResultEnum;
import njnu.edu.example.dao.StudentMapper;
import njnu.edu.example.pojo.Student;
import njnu.edu.example.pojo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/08/22:07
 * @Description:
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }

    public void insertData(StudentDTO studentDTO) {
        studentMapper.insertData(studentDTO);
    }
}
