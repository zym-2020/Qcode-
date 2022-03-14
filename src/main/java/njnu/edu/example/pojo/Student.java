package njnu.edu.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/08/21:44
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    Integer id;
    String name;
    Integer age;
}
