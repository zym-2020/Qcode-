package njnu.edu.example.dao;

import njnu.edu.example.pojo.Geometry;
import njnu.edu.example.pojo.dto.GeometryDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/15:43
 * @Description:
 */
@Repository
public interface GeometryMapper {
    List<Geometry> getAllGeometry();

    List<String> getGeo();

    int insertGeometry(@Param("name") String name, @Param("geo") String geo, @Param("level") Integer level, @Param("qcode") String code);
}
