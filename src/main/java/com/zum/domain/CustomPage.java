package com.zum.domain;

import com.zum.exception.NotFoundExceptionRest;
import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * Created by joeylee on 2017-04-18.
 */
@Data
public class CustomPage {

    private int totalBlock;
    private int currentBlock;
    private int begin;
    private int end;
    private Page<Board> boardList;
    private int pageSize;
    private int maxPager;
    private int pNo;
    private int totalPage;
    private Long totalElement;

    public CustomPage(Page<Board> boardList,int pageSize, int maxPager, int pNo){

        this.totalBlock = (boardList.getTotalPages() - 1) / maxPager + 1;
        this.currentBlock = (int) Math.ceil(pNo / (double) maxPager);
        this.begin = 1;
        this.end = boardList.getTotalPages();
        this.totalPage = boardList.getTotalPages();
        this.totalElement = boardList.getTotalElements();
        this.pageSize = pageSize;
        this.maxPager = maxPager;
        this.pNo= pNo;

        if(pNo > end) {
            throw new NotFoundExceptionRest();
        }

        setIfNotExistBoard(boardList);

        setIfBlockManyExist(maxPager);

        setIfLastBlock(boardList, maxPager);

        setIfMiddleBlock(maxPager);


    }

    private void setIfMiddleBlock(int maxPager) {
        if (totalBlock > currentBlock && totalBlock > 1) {
            begin = (currentBlock * maxPager) - (maxPager - 1);
            end = maxPager + (currentBlock - 1) * maxPager;
        }
    }

    private void setIfLastBlock(Page<Board> boardList, int maxPager) {
        if (totalBlock == currentBlock && totalBlock > 1) {
            begin = (currentBlock * maxPager) - (maxPager - 1);
            end = boardList.getTotalPages();
        }
    }

    private void setIfNotExistBoard(Page<Board> boardList) {
        if (boardList.getTotalElements() == 0) {
            end = 0;
        }
    }

    private void setIfBlockManyExist(int maxPager) {
        if (currentBlock == 1 && totalBlock > 1) {
            begin = 1;
            end = maxPager;
        }
    }



}
