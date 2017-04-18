package com.zum.util;

import com.zum.domain.Board;
import com.zum.exception.NotFoundExceptionRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.HashMap;

/**
 * Created by joeylee on 2017-04-10.
 */
public class PageCustomUtil {

    static Logger logger = LoggerFactory.getLogger(PageCustomUtil.class);

    public static HashMap<String, Object> getPageInfo(Page<Board> boardList,int pageSize, int maxPager, int pNo) {
        HashMap<String, Object> pageMap = new HashMap<String, Object>();


        int totalBlock = (boardList.getTotalPages() - 1) / maxPager + 1;
        int currentBlock = (int) Math.ceil(pNo / (double) maxPager);

        int begin = 1;
        int end = boardList.getTotalPages();

        if(pNo > end) {
            throw new NotFoundExceptionRest();
        }

        //게시글이 없을때
        if (boardList.getTotalElements() == 0) {
            end = 0;
        }

        //페이지가 많을때
        if (currentBlock == 1 && totalBlock > 1) {
            //첫 페이지
            begin = 1;
            end = maxPager;
        }

        if (totalBlock == currentBlock && totalBlock > 1) {
            //마지막 페이지
            begin = (currentBlock * maxPager) - (maxPager - 1);
            end = boardList.getTotalPages();
        }

        if (totalBlock > currentBlock && totalBlock > 1) {
            //중간 페이지
            begin = (currentBlock * maxPager) - (maxPager - 1);
            end = maxPager + (currentBlock - 1) * maxPager;
        }


        pageMap.put("totalPage", boardList.getTotalPages());
        pageMap.put("totalElement", boardList.getTotalElements());
        pageMap.put("pNo", pNo);
        pageMap.put("pageSize", pageSize);
        pageMap.put("maxPager", maxPager);
        pageMap.put("begin", begin);
        pageMap.put("end", end);
        pageMap.put("currentBlock", currentBlock);

        return pageMap;
    }



}
