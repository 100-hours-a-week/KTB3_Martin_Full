import Object.Hamburger.Hamburger;
import Service.OrderManager;

import java.io.IOException;


public class Main {



    public static void main(String[] args) throws IOException {
        OrderManager orderManager = new OrderManager();


        Hamburger hamburger;
        orderManager.start();



        while(true) {

            //햄버거 종류 선택 지점
            hamburger = orderManager.getHamburger();
            orderManager.showNowIngredient(hamburger);

            //재료추가 지점
            boolean answer = orderManager.isAdd();
            while (answer) {
                hamburger = orderManager.addIngredient(hamburger);
                orderManager.showNowIngredient(hamburger);
                answer = orderManager.isAdd();
            }

            //재료빼기 지점
            answer = orderManager.isSub();
            while (answer) {
                hamburger = orderManager.subIngredient(hamburger);
                orderManager.showNowIngredient(hamburger);
                answer = orderManager.isSub();
            }

            orderManager.saveHamburger(hamburger);

            answer = orderManager.isContinue();
            if(answer) continue;

            orderManager.calculateKcal();
            orderManager.end();

            break;







        }


    }
}