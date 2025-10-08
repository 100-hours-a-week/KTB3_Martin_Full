package Service;

import Object.Hamburger.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


;

//입출력 레이어
public class OrderManager {

    private final HamburgerService hamburgerService;
    private final BufferedReader br;


    public OrderManager() {
        this.hamburgerService = new HamburgerService();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start(){
        System.out.println("햄버거 계산기 시작");
    }



    //햄버거 종류 선택
    public Hamburger getHamburger() throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("무슨 종류로 드실건가요?\n");
        sb.append("종류: ");

        List<SetofHamburger> menu= Arrays.asList(SetofHamburger.values());
        for(SetofHamburger burger:menu){
            sb.append(burger).append(" ");
        }
        sb.append("\n");
        sb.append("문자로 입력해주세요");
        System.out.println(sb.toString());
        sb = new StringBuilder();



        String request;
        Hamburger hamburger;
        while (true) {
            try {
                request = br.readLine();
                hamburger = hamburgerService.getHamburger(request);

            } catch (IllegalArgumentException e) {
                System.out.println("다시 입력해주세요");
                continue;
            }
            break;
        }
        sb.append("선택한 햄버거: ").append(hamburger.getName());
        System.out.println(sb.toString());
        return hamburger;
    }



    public Hamburger addingredient(Hamburger hamburger){
        return hamburger;
    }

    //재료 추가 입출력









    //사용자 선택 질문 출력
    public boolean isadd() throws IOException {
        System.out.println("추가하실 재료 있으신가요?(y/n)");
        return isYesorNo();
    }

    public boolean issub() throws IOException {
        System.out.println("빼실 재료 있으신가요>(y/n)");
        return isYesorNo();
    }

    //입력 유효성 검사

    //둘중에 하나만 골라 y or n
    public boolean isYesorNo() throws IOException {

        String line;
        while (true) {

            line = br.readLine();
            line = line.replaceAll("\\s", "");
            line = line.toLowerCase();
            if (line.equals("y")) return true;
            if (line.equals("n")) return false;
            System.out.println("y 또는 n으로 입력해주세요");

        }

    }

    //int값을 넣었는지 확인
    public int checkIntAndPositive() throws IOException {
        int num;
        while (true) {
            try {
                String str = br.readLine();
                str.replaceAll("\\s", "");
                num = Integer.parseInt(str);
                if (num < 0) throw new NumberFormatException();
                break;

            } catch (NumberFormatException e) {
                System.out.println("입력하신 숫자가 잘못된것 같습니다. 다시 입력해주세요");
                continue;
            }

        }
        return num;
    }

}
