package com.yt.boot.utils;

/**
 * @author Mr.He
 * @description:
 * @create 2021-08-12 17:58
 */
public class MyWebUtils {

    /**
     * 校验页码并返回一个在合法范围内的页码
     *
     * @param pageNumber      需要校验的页码
     * @param dataTotalNumber 查询数据得到的总量
     * @param pageSize        每一页的总量
     * @return 合法页码
     */
    public static Integer validPageNumber(Integer pageNumber, Integer dataTotalNumber, Integer pageSize) {
        Integer maxPage = dataTotalNumber % pageSize == 0 ? dataTotalNumber / pageSize : dataTotalNumber / pageSize + 1;
        if (pageNumber > maxPage) {
            pageNumber = maxPage;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        return pageNumber;
    }
}
