package com.sunlx.servicebase.handler;

 import com.sunlx.commonutils.R;
 import com.sunlx.servicebase.exception.SunlxException;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceprionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }


    @ExceptionHandler(SunlxException.class)
    @ResponseBody
    public R error(SunlxException e){
        e.printStackTrace();
        log.error(e.getMsg());
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
