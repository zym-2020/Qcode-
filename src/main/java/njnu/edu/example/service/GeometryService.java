package njnu.edu.example.service;

import jnr.ffi.annotations.In;
import njnu.edu.example.common.enums.ResultEnum;
import njnu.edu.example.dao.GeometryMapper;
import njnu.edu.example.pojo.Geometry;
import njnu.edu.example.pojo.dto.GeometryDTO;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import cn.hutool.json.JSONObject;
import java.awt.Polygon;
import java.awt.geom.Area;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/15:46
 * @Description:
 */
@Service
public class GeometryService {
    @Autowired
    GeometryMapper geometryMapper;

    public List<Geometry> getAllGeometry() {
        return geometryMapper.getAllGeometry();
    }

    public List<String> getGeo() {
        return geometryMapper.getGeo();
    }

    public void insertGeometry(GeometryDTO geometryDTO) {
        String code = Integer.toBinaryString(geometryDTO.getQcode().getLevel()) + Integer.toBinaryString(geometryDTO.getQcode().getX()) + Integer.toBinaryString(geometryDTO.getQcode().getY());
        for(List<Integer> l : geometryDTO.getQcode().getBitMap()) {
            for(Integer i : l) {
                code = code + i.toString();
            }
        }
        System.out.println(code);
        geometryMapper.insertGeometry(geometryDTO.getName(), geometryDTO.getGeo(), geometryDTO.getQcode().getLevel(), code);

    }

    public void insertMultipleGeometry(List<GeometryDTO> geometryDTOS) {
        for(GeometryDTO geometryDTO : geometryDTOS) {
            insertGeometry(geometryDTO);
        }
    }

    public void computeQcode() {
        /**
        * @Description:通过python的形式调用，不好用
        * @Author: Yiming
        * @Date: 2022/3/13
        */

//        String[] cmdArr = new String[] {"python", "E:\\codes\\Ideaproject\\example\\src\\main\\resources\\static\\example.py"};
//        Process pr = Runtime.getRuntime().exec(cmdArr);
//        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//        String line;
//        String result = "";
//        //接收返回结果
//        while ((line = in.readLine()) != null) {
//
//            result += line;
//        }
//        System.out.println(result);
//        in.close();
//        pr.waitFor();


    }
}
