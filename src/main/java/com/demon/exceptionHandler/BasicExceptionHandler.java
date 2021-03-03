package com.demon.exceptionHandler;

import com.demon.common.ResponseVo;
import com.demon.exception.BasicException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: TODO
 * @author: liuhao
 * @create: 2020/9/17 13:32
 */
@ControllerAdvice
@ResponseBody
public class BasicExceptionHandler {

    //出现自定义的BasicException异常时，进入改方法
    @ExceptionHandler(value = BasicException.class)
//    @ResponseBody
    public ResponseVo baseErrorHandler(BasicException e) throws Exception {
        ResponseVo r = new ResponseVo();
        r.failureMsg(e.getMessage());
        r.setStatus("-1");
        e.printStackTrace();
        //r.setThrowable(e);
        return r;
    }
}
