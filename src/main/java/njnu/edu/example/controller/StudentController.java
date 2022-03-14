package njnu.edu.example.controller;

import njnu.edu.example.common.exception.MyException;
import njnu.edu.example.common.result.JsonResult;
import njnu.edu.example.common.result.ResultUtils;
import njnu.edu.example.pojo.dto.StudentDTO;
import njnu.edu.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/08/22:12
 * @Description:
 */

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public JsonResult getAllStudents() {
        return ResultUtils.getResult(studentService.getAllStudents());
    }

    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public JsonResult insertData(@RequestBody StudentDTO studentDTO) {
        studentService.insertData(studentDTO);
        return ResultUtils.getResult();
    }
}
