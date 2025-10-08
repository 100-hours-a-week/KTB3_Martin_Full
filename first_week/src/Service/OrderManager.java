package Service;

import Object.Hamburger.*;
import Object.Ingredient.Ingredients;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
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
    public void end(){
        System.out.println("프로그램 종료");
    }



    //햄버거 종류 선택
    public Hamburger getHamburger() throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("무슨 종류로 드실건가요?\n");
        sb.append("종류: ").append(getHamburgerType()).append("\n");
        sb.append("문자로 입력해주세요");
        System.out.println(sb.toString());



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
        sb = new StringBuilder();
        sb.append("선택한 햄버거: ").append(hamburger.getName());
        sb.append("패티 : ").append(hamburger.getPatty().getName());
        sb.append(" ").append(hamburger.getPatty().getKcal()).append("kcal");
        System.out.println(sb.toString());
        return hamburger;
    }

    public String getHamburgerType(){
        StringBuilder sb = new StringBuilder();
        List<SetofHamburger> menu= Arrays.asList(SetofHamburger.values());
        for(SetofHamburger burger:menu){
            sb.append(burger).append(" ");
        }

        return sb.toString();
    }


    //재료 추가 입출력
    public Hamburger addIngredient(Hamburger hamburger) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("추가 가능한 재료 : ").append(showIngredientForAdd()).append("\n");
        sb.append("추가할 재료를 선택해주세요(패티는 추가 되지 않습니다)");

        String name;
        int num;
        while(true){
            System.out.println(sb.toString());
            name = br.readLine();
            System.out.println("얼마나 추가 하실건가요?");
            num = checkIntAndPositive();
            if(!hamburgerService.isAddable(name)){
                System.out.println("다시 입력해주세요");
                continue;
            }
            break;
        }

        hamburger = hamburgerService.addIngredient(hamburger, name, num);

        return hamburger;
    }

    //재료 빼기 입출력
    public Hamburger subIngredient(Hamburger hamburger) throws IOException {
        StringBuilder sb = new StringBuilder();
        showNowIngredient(hamburger);

        String name;
        int num;

        while(true){
            System.out.println("뺄 재료를 선택해주세요(패티는 뺄 수 없습니다.)");
            name = br.readLine();
            System.out.println("얼마나 빼실건가요?");
            num = checkIntAndPositive();
            if(!hamburgerService.isSubtractable(hamburger, name, num)){
                System.out.println("다시 입력해주세요");
                continue;
            }
            break;
        }

        hamburger = hamburgerService.subIngredient(hamburger, name, num);

        return hamburger;

    }
    //버거 완성
    public void saveHamburger(Hamburger hamburger) throws IOException {
        System.out.println("커스텀 버거 완성");
        hamburgerService.saveHamburger(hamburger);
    }

    //총 칼로리 계산
    public void calculateKcal(){
        StringBuilder sb = new StringBuilder();
        sb.append("총 칼로리 계산 시작").append("\n");
        System.out.println(sb.toString());
        sb = new StringBuilder();
        sb.append("총 칼로리 : ").append(hamburgerService.getTotalKcal()).append("\n");
        System.out.println(sb.toString());
    }



    //재료들 보여주기
    public String showIngredientForAdd(){
        StringBuilder sb = new StringBuilder();
        for (String s : hamburgerService.getAddableIngredient().keySet()) {
            sb.append(s).append(" ");
        }

        return sb.toString();
    }

    public void showNowIngredient(Hamburger hamburger){
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("현재 햄버거의 재료: ").append("\n");

        sb.append(hamburger.getPatty().getName());
        sb.append(" ").append(hamburger.getPatty().getKcal()).append("kcal").append("\n");

        HashMap<String, Ingredients> tmp = hamburger.getIngredientList();
        for (Ingredients i : tmp.values()) {
            sb.append(i.getName()).append(" ").append(i.getNum());
            sb.append(" ").append(i.getNum()*i.getKcal()).append("Kcal").append("\n");
        }
        System.out.println(sb.toString());

    }











    //사용자 선택 질문 출력
    public boolean isAdd() throws IOException {
        System.out.println("추가하실 재료 있으신가요?(y/n)");
        return isYesorNo();
    }

    public boolean isSub() throws IOException {
        System.out.println("빼실 재료 있으신가요>(y/n)");
        return isYesorNo();
    }
    public boolean isContinue() throws IOException {
        System.out.println("버거 종류를 추가 하실 건가요?(y/n)");
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
