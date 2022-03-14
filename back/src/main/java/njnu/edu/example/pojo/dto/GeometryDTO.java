package njnu.edu.example.pojo.dto;

import cn.hutool.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import njnu.edu.example.pojo.support.Qcode;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/16:12
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeometryDTO {
    String name;
    String geo;
    Qcode qcode;
}
