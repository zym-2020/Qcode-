package njnu.edu.example.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/15:09
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    String name;
    Integer age;
}
