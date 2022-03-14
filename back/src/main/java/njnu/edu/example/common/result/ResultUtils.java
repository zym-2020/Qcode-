package njnu.edu.example.common.result;

import njnu.edu.example.common.enums.ResultEnum;
import njnu.edu.example.common.exception.MyException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/21:47
 * @Description:
 */

public class ResultUtils {

    /**
    * @Description:处理没有返回的的结果
    * @Author: Yiming
    * @Date: 2022/3/10
    */

    public static JsonResult getResult() {
        return new JsonResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), null);
    }

    /**
    * @Description:处理有返回值的结果
    * @Author: Yiming
    * @Date: 2022/3/11
    */

    public static JsonResult getResult(Object obj) {
        return new JsonResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), obj);
    }

    /**
    * @Description:处理异常的结果
    * @Author: Yiming
    * @Date: 2022/3/11
    */

    public static JsonResult getResult(MyException myException) {
        return new JsonResult(myException.getCode(), myException.getMessage(), null);
    }


}
