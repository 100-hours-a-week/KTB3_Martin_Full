import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import Object.Hamburger.*;
import Service.OrderManger;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OrderManger orderManger = new OrderManger();


        //지금까지 선책한 버거들
        ArrayList<Hamburger> hamburgers = new ArrayList<>();
        System.out.println("햄버거 칼로리 계산기 \n");


        while(true){
            StringBuilder sb = new StringBuilder();

            sb.append("무슨 종류로 드실건가요?\n");
            sb.append("종류 : 치즈버거 불고기버거 치킨버거\n");
            sb.append("문자로 입력해주세요");

            System.out.println(sb.toString());

            Hamburger hamburger;

            //햄버거 종류 선택 지점
            hamburger = orderManger.getHamburger();

            //재료추가 지점
            System.out.println("추가하실 재료 있으신가요?(y/n)");
            boolean answer =orderManger.isYesorNo();
            while(answer){
                System.out.println("추가가능한 재료 : 빵, 양상추, 토마토, 양파, 소스, 베이컨, 치즈, 패티");
                System.out.println("추가할 재료를 입력해주세요");
                String name = br.readLine();

                System.out.println("얼마나 추가하실건가요");
                int num = orderManger.checkIntAndPositive();

                if(hamburger.add(name,num)){
                    System.out.println("재료를 더 추가하시나요?(y/n)");
                    answer = orderManger.isYesorNo();

                }
                else{
                    System.out.println("재료의 이름이 잘못된것 같습니다 재료 선택으로 돌아갑니다.");
                }
            }

            //재료빼기 지점
            System.out.println("빼실 재료 있으신가요?(y/n)");
            answer = orderManger.isYesorNo();
            while(answer) {
                sb = new StringBuilder();
                sb.append("뺄수있는 재료 : ");
                sb.append(hamburger.ShowIngredient());
                sb.append("뺄 재료를 입력해주세요");
                System.out.println(sb.toString());

                String name = br.readLine();

                System.out.println("얼마나 빼실건가요");
                int num = orderManger.checkIntAndPositive();

                if (hamburger.sub(name, num)) {
                    System.out.println("더빼실 재료 있으신가요?(y/n)");
                    answer = orderManger.isYesorNo();

                } else {
                    System.out.println("재료의 이름이나 갯수가 잘못된것 같습니다 뺄 재료 선택으로 돌아갑니다.");
                }


            }
            hamburgers.add(hamburger);

            System.out.println("버거 종류를 더 추가하시겠나요?(y/n)");
            answer = orderManger.isYesorNo();
            if(answer){
                System.out.println("종류 선택 지점으로 돌아갑니다.\n");
                continue;
            }

            else{
                System.out.println("총 칼로리를 계산합니다.");


                AtomicInteger sumkcal = new AtomicInteger(0);
                ExecutorService es = Executors.newFixedThreadPool(4);


                for(Hamburger burger: hamburgers){
                    es.submit(()->{
                        int kcal = burger.total();
                        sumkcal.addAndGet(kcal);
                    });

                }
                es.shutdown();
                es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);


                sb = new StringBuilder();
                sb.append("총 햄버거 갯수: ").append(hamburgers.size()).append("\n");
                sb.append("총 칼로리: ").append(sumkcal);

                System.out.println(sb);
            }

            System.out.println("프로그램 종료");
            return ;
        }
    }
}