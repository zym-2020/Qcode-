package njnu.edu.example.pojo;

import lombok.Data;
import njnu.edu.example.pojo.support.Qcode;

import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/15:18
 * @Description:
 */
@Data
@Table(name = "geo_test")
public class Geometry {
    Integer id;
    String geo;
    String name;
    Object qcode;
    Integer level;
}
