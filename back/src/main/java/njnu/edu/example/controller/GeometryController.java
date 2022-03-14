package njnu.edu.example.controller;

import njnu.edu.example.common.result.JsonResult;
import njnu.edu.example.common.result.ResultUtils;
import njnu.edu.example.pojo.dto.GeometryDTO;
import njnu.edu.example.service.GeometryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/15:48
 * @Description:
 */
@RestController
@RequestMapping("/geometry")
public class GeometryController {

    @Autowired
    GeometryService geometryService;

    @RequestMapping(value = "/getAllGeometry", method = RequestMethod.GET)
    public JsonResult getAllGeometry() {
        return ResultUtils.getResult(geometryService.getAllGeometry());
    }

    @RequestMapping(value = "/getGeo", method = RequestMethod.GET)
    public JsonResult getGeo() {
        return ResultUtils.getResult(geometryService.getGeo());
    }

    @RequestMapping(value = "/insertGeometry", method = RequestMethod.POST)
    public JsonResult insertGeometry(@RequestBody GeometryDTO geometryDTO) {
        geometryService.insertGeometry(geometryDTO);
        return ResultUtils.getResult();
    }

    @RequestMapping(value = "/insertMultipleGeometry", method = RequestMethod.POST)
    public JsonResult insertMultipleGeometry(@RequestBody List<GeometryDTO> geometryDTOS) {
        geometryService.insertMultipleGeometry(geometryDTOS);
        return ResultUtils.getResult();
    }

    @RequestMapping(value = "/computeQcode", method = RequestMethod.GET)
    public JsonResult computeQcode() {
        geometryService.computeQcode();
        return ResultUtils.getResult();
    }
}
