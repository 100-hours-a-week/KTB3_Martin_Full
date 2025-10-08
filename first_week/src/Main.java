import Object.Hamburger.Hamburger;
import Service.OrderManager;

import java.io.IOException;


public class Main {



    public static void main(String[] args) throws IOException {
        OrderManager orderManager = new OrderManager();


        Hamburger hamburger;
        orderManager.start();
//        hamburger = orderManager.getHamburger();



        while(true) {

            //햄버거 종류 선택 지점
            hamburger = orderManager.getHamburger();
            orderManager.showNowIngredient(hamburger);

            //재료추가 지점
            boolean answer = orderManager.isadd();
            while (answer) {
                hamburger = orderManager.addIngredient(hamburger);
                orderManager.showNowIngredient(hamburger);
                answer = orderManager.isadd();
            }

            //재료빼기 지점
            answer = orderManager.issub();
            while (answer) {
                hamburger = orderManager.subIngredient(hamburger);
                orderManager.showNowIngredient(hamburger);
                answer = orderManager.issub();
            }



        }

//            hamburgers.add(hamburger);
//
//            System.out.println("버거 종류를 더 추가하시겠나요?(y/n)");
//            answer = orderManger.isYesorNo();
//            if(answer){
//                System.out.println("종류 선택 지점으로 돌아갑니다.\n");
//                continue;
//            }
//
//            else{
//                System.out.println("총 칼로리를 계산합니다.");
//
//            }
//
//            System.out.println("프로그램 종료");
//            return ;
//        }
    }
}