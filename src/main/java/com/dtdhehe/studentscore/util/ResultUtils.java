package com.dtdhehe.studentscore.util;

import com.dtdhehe.studentscore.vo.ResultVO;

/**
 * @author 陈珊珊
 * @version 1.0
 * @date 2019/11/5 23:31
 * @description
 **/
public class ResultUtils {
    private static final Integer SUCCESS = 200;
    private static final Integer FAILED = 500;

    /**
     * 返回成功，带消息及数据
     * @param msg
     * @param object
     * @return
     */
    public static ResultVO success(String msg, Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(SUCCESS);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 返回成功，带消息
     * @param msg
     * @return
     */
    public static ResultVO success(String msg){
        return success(msg, null);
    }

    /**
     * 返回成功
     * @return
     */
    public static ResultVO success(){
        return success(null, null);
    }

    /**
     * 返回失败
     * @param msg
     * @return
     */
    public static ResultVO failed(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(FAILED);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
