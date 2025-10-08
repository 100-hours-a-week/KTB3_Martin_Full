import Object.Hamburger.Hamburger;
import Service.OrderManager;

import java.io.IOException;


public class Main {



    public static void main(String[] args) throws IOException {
        OrderManager orderManager = new OrderManager();


        Hamburger hamburger;
        orderManager.start();
        hamburger = orderManager.getHamburger();


//        while(true){
//            Hamburger hamburger;
//
//            //햄버거 종류 선택 지점
//            hamburger = orderManager.getHamburger();
//
//            //재료추가 지점
//            boolean answer =orderManager.isadd();
//            while(answer){
//                System.out.println("추가가능한 재료 : 빵, 양상추, 토마토, 양파, 소스, 베이컨, 치즈, 패티");
//                System.out.println("추가할 재료를 입력해주세요");
//                String name = br.readLine();
//
//                System.out.println("얼마나 추가하실건가요");
//                int num = orderManger.checkIntAndPositive();
//
//                if(hamburger.add(name,num)){
//                    System.out.println("재료를 더 추가하시나요?(y/n)");
//                    answer = orderManger.isYesorNo();
//
//                }
//                else{
//                    System.out.println("재료의 이름이 잘못된것 같습니다 재료 선택으로 돌아갑니다.");
//                }
//            }
//
//            //재료빼기 지점
//            System.out.println("빼실 재료 있으신가요?(y/n)");
//            answer = orderManger.isYesorNo();
//            while(answer) {
//                sb = new StringBuilder();
//                sb.append("뺄수있는 재료 : ");
//                sb.append(hamburger.ShowIngredient());
//                sb.append("뺄 재료를 입력해주세요");
//                System.out.println(sb.toString());
//
//                String name = br.readLine();
//
//                System.out.println("얼마나 빼실건가요");
//                int num = orderManger.checkIntAndPositive();
//
//                if (hamburger.sub(name, num)) {
//                    System.out.println("더빼실 재료 있으신가요?(y/n)");
//                    answer = orderManger.isYesorNo();
//
//                } else {
//                    System.out.println("재료의 이름이나 갯수가 잘못된것 같습니다 뺄 재료 선택으로 돌아갑니다.");
//                }
//
//
//            }
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