package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Appliacation {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;     // 쿼리를ㄹ 저장하고 실ㄹ행

        int result = 0;     // 변화가 정상적으로 감ㅁ지되면 1 저장

        Properties prop = new Properties(); // 프로퍼티 파일 읽을 준비

        try {   // 호출한 메소드에 throw 처리를 해두었기에 예외처리 필요
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("deleteMenu");  // 키 값

            Scanner sc = new Scanner(System.in);
            System.out.print("삭제할 메뉴 코드를 입력해주세요 : ");
            int menuCode = sc.nextInt();

            pstmt = con.prepareStatement(query);  // 쿼리문 저장 실행시켜주는 구문 만들기
            pstmt.setInt(1, menuCode);      // 메뉴 쿼리 ?에 값 전달

           result = pstmt.executeUpdate();      // 잘 수행이 된다면 1 반환

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(con);
            close(pstmt);       // 사용한 자원 닫아주기

        }

        if (result > 0) {       // 성공했다면 1보다 클 것
            System.out.println("메뉴 삭제에 성공했습니다!!!!!");
        } else {
            System.out.println("메뉴 삭제에 실패했습니다.....ㅡㅜ");
        }

    }




}
