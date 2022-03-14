package njnu.edu.example.pojo.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/14/9:48
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qcode {
    @JsonProperty(value = "level")
    Integer level;
    @JsonProperty(value = "X")
    Integer X;
    @JsonProperty(value = "Y")
    Integer Y;
    @JsonProperty(value = "Bitmap")
    List<List<Integer>> bitMap;
}
