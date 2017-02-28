package com.ktds.dojun.board.controller;

import com.ktds.dojun.board.biz.BoardBiz;
import com.ktds.dojun.board.biz.BoardBizImpl;
import com.ktds.dojun.board.vo.BoardVO;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardController {


    private BoardBiz boardBiz;
    private Scanner sc;

    public BoardController() {

        boardBiz = new BoardBizImpl();
        sc= new Scanner(System.in);


    }

    public void writeArticle() {
        BoardVO boardVO = new BoardVO();
        boardVO.setWriter("김도준");
        boardVO.setSubject("주말과제..");
        boardVO.setContent("줄까요..?");


        if (boardBiz.writeArticle(boardVO)) {
            System.out.println("글쓰기 성공");

        } else {
            System.out.println("글 쓰기 실패");
        }


    }


    public void printAllArticles() {
        List<BoardVO> articles = boardBiz.getAllArticles();

        for (BoardVO article : articles) {

            System.out.printf(" 글 번호 : %d \n", article.getBoardId());
            System.out.printf(" 제목 : %s \n", article.getSubject());
            System.out.printf(" 작성자 : %s \n", article.getWriter());
            System.out.printf(" 작성일 : %s \n", article.getWriteDate());
            System.out.println(" ============================================= ");


        }


    }

    public void printOneArticles(int articleNum) {
        BoardVO boardVO = boardBiz.getOneArticle(articleNum);
        System.out.printf(" 글 번호 : %d \n", boardVO.getBoardId());
        System.out.printf(" 제목 : %s \n", boardVO.getSubject());
        System.out.printf(" 작성자 : %s \n", boardVO.getWriter());
        System.out.printf(" 작성일 : %s \n", boardVO.getWriteDate());
        System.out.println(" ============================================= ");


    }


    public void start() {




        System.out.println(" 1. write  2. List  3. findOne");

        int choose = sc.nextInt();

        if (choose == 1) {
            writeArticle();

        } else if (choose == 2) {
            printAllArticles();

        } else if (choose == 3) {

            int artNum = sc.nextInt();

            printOneArticles(artNum);

        } else {

            System.out.println("놉 ");
        }


    }

    public static void main(String[] args) {
        new BoardController().start();

    }


}
