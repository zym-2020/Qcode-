package njnu.edu.example.common.exception;

import lombok.Getter;
import njnu.edu.example.common.enums.ResultEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Yiming
 * @Date: 2022/03/10/21:31
 * @Description:
 */
@Getter
public class MyException extends RuntimeException{
    private Integer code;
    
    /**
    * @Description:resultEnum中的异常
    * @Author: Yiming
    * @Date: 2022/3/10
    */
    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    /**
    * @Description:自定义异常
    * @Author: Yiming
    * @Date: 2022/3/10
    */
    public MyException(String message) {
        super(message);
        this.code = -9999;
    }


}
