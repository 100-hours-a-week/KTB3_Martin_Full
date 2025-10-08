package Service;

import Object.Hamburger.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


;

//입력값 유효성 검사
public class OrderManger {

    private final HamburgerService hamburgerService;
    private final BufferedReader br;


    public OrderManger() {
        this.hamburgerService = new HamburgerService();
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }


    //햄버거 선택
    public Hamburger getHamburger() throws IOException {
        String line = br.readLine();
        Hamburger hamburger;
        while (true) {
            try {
                SetofHamburger burger = SetofHamburger.valueOf(line);
                switch (burger) {
                    case SetofHamburger.불고기버거 -> hamburger = new Bulgogi();
                    case SetofHamburger.치킨버거 -> hamburger = new Chicken();
                    case SetofHamburger.치즈버거 -> hamburger = new Cheese();
                    default -> throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                System.out.println("다시 입력해주세요");
                continue;
            }
            break;
        }

        hamburger.ShowIngredient();


        return hamburger;


    }

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
