package com.smart.test.dataset;

import com.smart.bean.Board;
import com.smart.dao.BoardDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @Author: yk
 * @Date: 2020/1/30 14:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/*.xml"})
public class testBoardDao {

    @Autowired
    BoardDao boardDao;

    @Test
    public void testGet() {

        Board board = boardDao.get(1);
        System.out.println("--------" + board.toString());


    }
}
