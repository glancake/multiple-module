package com.gl.util;

/**
 * 分页工具类
 */
public class PaginationUtils {
//    默认每页数量
    private static final int DEFAULT_PAGE_SIZE = 10;

    public static int  validatePageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

}
