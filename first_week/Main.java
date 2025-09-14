import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class  Ingredient{
    //재료 이름
    String name;
    //재료 개수
    int num;
    //재료 1개당 칼로리(베이컨은 2줄 기준)
    int kcal;

     Ingredient(String name, int num, int kcal){
        this.name = name;
        this.num = num;
        this.kcal = kcal;
    }
}
abstract class Hamburger{
    //재료들을 모아놓은 리스트
    ArrayList<Ingredient> ingarr;
    //버거종류
    String name;

    //버거 객체별 재료이름 변경을 위한 추상 메서드
    abstract boolean add(String name, int num);
    abstract boolean sub(String name, int num);


    //정상 입력시 true 입력실패시 false
    //재료추가
    boolean hamadd(String name, int num){
        if(name.equals("베이컨")) name = "베이컨(2장)";
        else if(name.equals("소스")) name = "소스(마요네즈)";
        boolean result = false;

        for( Ingredient i:ingarr){
            if(i.name.equals(name)){
                i.num += num;
                result = true;
                StringBuilder sb = new StringBuilder();
                sb.append("갯수를 추가한 재료: ").append(i.name).append("\n");
                sb.append("총 갯수: ").append(i.num).append("개");
                sb.append("(").append(i.kcal*i.num).append("kcal)\n");
                System.out.println(sb.toString());


                break;
            }
        }

        return result;
    }


    //입력성공시 true, 실패시 false
    //재료빼기
    boolean hamsub(String name, int num){
        if(name.equals("베이컨")) name = "베이컨(2장)";
        else if(name.equals("소스")) name = "소스(마요네즈)";
        boolean result = false;

        for( Ingredient i:ingarr){
            if(i.name.equals(name)){
                if(i.num<num){
                    System.out.println("뺄려는 갯수가 이미 있는 갯수보다 큽니다.");
                }
                else{
                    i.num -= num;
                    StringBuilder sb = new StringBuilder();

                    sb.append("갯수를 뺀 재료: ").append(i.name).append("\n");
                    sb.append("총 갯수: ").append(i.num).append("개");
                    sb.append("(").append(i.kcal*i.num).append("kcal)\n");
                    result = true;
                    System.out.println(sb.toString());

                }

                break;
            }
        }

        return result;
    }

    //뺄수 있는 재료들을 보여줌
    String subshow(){
        StringBuilder sb = new StringBuilder();
        for( Ingredient i : ingarr){
            if(i.num>0){
                String tmp = i.name;
                if(i.name.equals("베이컨(2장)")) tmp = "베이컨";
                else if(i.name.equals("소스(마요네즈)")) tmp = "소스";
                sb.append(tmp).append(":").append(i.num).append("\n");

            }
        }
        return sb.toString();
    }

    //현재 햄버거의 총 칼로리 계산
    int total(){
        int totalkcal = 0;
        StringBuilder sb  = new StringBuilder();
        sb.append("종류 : ").append(this.name).append("\n");
        for( Ingredient i:ingarr){
//            if(i.num<0){System.out.println("문제발생");}
            if(i.num ==0) continue;
            sb.append(i.name).append(" ").append(i.num).append("개 ");
            sb.append("(").append(i.kcal*i.num).append("kcal)\n");
            totalkcal += i.kcal*i.num;

        }
        sb.append("위의 햄버거의 칼로리 :").append(totalkcal).append("\n");
        System.out.println(sb.toString());
        return totalkcal;
    }

    //처음 객체 생성시 defualt 재료들 보여주기
    String show(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        for( Ingredient i:ingarr){
            if(i.num ==0) continue;
            sb.append(i.name).append(" ").append(i.num).append("개 ");
            sb.append("(").append(i.kcal*i.num).append("kcal)\n");

        }
        return sb.toString();

    }

    Hamburger(){
        ingarr = new ArrayList<>();
        ingarr.add(new  Ingredient("빵",2,90));
        ingarr.add(new  Ingredient("양상추",1,4));
        ingarr.add(new  Ingredient("토마토",1,6));
        ingarr.add(new  Ingredient("양파",1,7));
        ingarr.add(new  Ingredient("소스(마요네즈)",1,100));
        ingarr.add(new  Ingredient("베이컨(2장)",0,90));
        ingarr.add(new  Ingredient("치즈",0,50));

    }




}
class Cheese extends Hamburger{

    Cheese(){
        name = "치즈버거";

        for( Ingredient i:ingarr){
            if(i.name.equals("치즈")){
                i.num++;
                break;
            }
        }
        ingarr.add(new  Ingredient("패티",1,250));

        StringBuilder sb = new StringBuilder();
        sb.append("치즈버거 기본 재료 :\n");

        System.out.println(super.show(sb.toString()));



    }
    @Override
    boolean add(String name, int num){
        return super.hamadd(name,num);

    }

    boolean sub(String name, int num){
        return super.hamsub(name,num);

    }
}



class Beef extends Hamburger{

    Beef(){
        name = "불고기버거";
        ingarr.add(new  Ingredient("패티",1,280));

        StringBuilder sb = new StringBuilder();
        sb.append("불고기버거 기본 재료 :\n");
        System.out.println(super.show(sb.toString()));


    }

    boolean add(String name, int num){
        return super.hamadd(name,num);
    }

    boolean sub(String name, int num){
        return super.hamsub(name,num);
    }
}

class Chicken extends Hamburger{
    Chicken(){
        name = "치킨버거";
        ingarr.add(new  Ingredient("치킨패티",1,280));

        StringBuilder sb = new StringBuilder();
        sb.append("치킨버거 기본 재료 :\n");
        System.out.println(super.show(sb.toString()));

    }
    boolean add(String name, int num){
        if(name.equals("패티")) name = "치킨패티";
        return super.hamadd(name,num);
    }

    boolean sub(String name, int num){
        if(name.equals("패티")) name = "치킨패티";
        return super.hamsub(name,num);
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //버거 선택에서 문자열과 숫자를 받기위한 해쉬맵
        HashMap<String, Integer> burchoice = new HashMap<>();
        burchoice.put("치즈버거" , 1);
        burchoice.put("불고기버거" , 2);
        burchoice.put("치킨버거" , 3);

        //지금까지 선책한 버거들
        ArrayList<Hamburger> burgers = new ArrayList<>();

        System.out.println("햄버거 칼로리 계산기 \n");


        while(true){
            StringBuilder sb = new StringBuilder();

            sb.append("무슨 종류로 드실건가요?\n");
            sb.append("종류 : 1.치즈버거 2. 불고기버거 3.치킨버거\n");
            sb.append("숫자또는 문자로 입력해주세요");

            System.out.println(sb.toString());
            Hamburger hamburger;

            //햄버거 종류 선택 지점
            while(true){
                String line = br.readLine();
                int choice;
                try{
                    choice = Integer.parseInt(line);
                }
                catch(NumberFormatException e){
                    line = line.replaceAll("\\s","");
                    if(burchoice.containsKey(line)){
                        choice = burchoice.get(line);
                    }
                    else{
                        System.out.println("정확하게 입력해 주세요");
                        continue;
                    }

                }

                if(choice >3||choice <1) {
                    System.out.println("숫자를 정확하게 입력해 주세요");
                    continue;
                }

                if(choice == 1) hamburger = new Cheese();
                else if(choice == 2) hamburger = new Beef();
                else hamburger = new Chicken();


                break;

            }
            String answer;

            while(true){
                System.out.println("추가하실 재료 있으신가요?(y/n)");
                answer = br.readLine();
                answer = answer.replaceAll("\\s","");
                if(answer.equals("y")||answer.equals("n")) break;
                System.out.println("y 또는 n으로 입력해주세요");

            }







            //재료추가 지점
            while(answer.equals("y")){
                System.out.println("추가가능한 재료 : 빵, 양상추, 토마토, 양파, 소스, 베이컨, 치즈, 패티");
                System.out.println("추가할 재료를 입력해주세요");

                String name = br.readLine();

                System.out.println("얼마나 추가하실건가요");
                int num;
                try{
                    num = Integer.parseInt(br.readLine());
                    if(num<0) throw new NumberFormatException();


                }catch(NumberFormatException e){
                    System.out.println("입력하신 숫자가 잘못된것 같습니다. 추가할 재료 선택으로 돌아갑니다.");
                    continue;
                }

                if(hamburger.add(name,num)){
                    while(true){
                        System.out.println("더 추가하실 재료 있으신가요?(y/n)");
                        answer = br.readLine();
                        answer = answer.replaceAll("\\s","");
                        if(answer.equals("y")||answer.equals("n")) break;
                        System.out.println("y 또는 n으로 입력해주세요");

                    }

                }

                else{
                    System.out.println("재료의 이름이 잘돗된것 같습니다 재료 선택으로 돌아갑니다.");
                }
            }

            while(true){
                System.out.println("빼실 재료 있으신가요?(y/n)");
                answer = br.readLine();
                answer = answer.replaceAll("\\s","");
                if(answer.equals("y")||answer.equals("n")) break;
                System.out.println("y 또는 n으로 입력해주세요");



            }


            //재료빼기 지점
            while(answer.equals("y")) {
                System.out.println("뺄수있는 재료 : ");
                System.out.println(hamburger.subshow());
                System.out.println("뺄 재료를 입력해주세요");

                String name = br.readLine();

                System.out.println("얼마나 빼실건가요");
                int num;
                try {
                    num = Integer.parseInt(br.readLine());
                    if (num < 0) throw new NumberFormatException();


                } catch (NumberFormatException e) {
                    System.out.println("입력하신 숫자가 잘못된것 같습니다. 재료 선택으로 돌아갑니다.");
                    continue;
                }

                if (hamburger.sub(name, num)) {
                    while(true){
                        System.out.println("더 빼실 재료 있으신가요?(y/n)");
                        answer = br.readLine();
                        answer = answer.replaceAll("\\s","");
                        if(answer.equals("y")||answer.equals("n")) break;
                        System.out.println("y 또는 n으로 입력해주세요");

                    }

                } else {
                    System.out.println("재료의 이름이나 갯수가 잘돗된것 같습니다 뺄 재료 선택으로 돌아갑니다.");
                }


            }
            burgers.add(hamburger);

            while(true){
                System.out.println("버거 종류를 더 추가하시겠나요?(y/n)");
                answer = br.readLine();
                answer = answer.replaceAll("\\s","");

                if(answer.equals("y")||answer.equals("n")) break;
                System.out.println("y 또는 n으로 입력해주세요");
            }

            if(answer.equals("y")){
                System.out.println("종류 선택 지점으로 돌아갑니다.\n");
                continue;
            }

            else{

                System.out.println("총 칼로리를 계산합니다.");
                int sumnum = 0;
                int sum = 0;
                for(Hamburger burger:burgers){
                    sum += burger.total();
                    sumnum++;
                }

                sb = new StringBuilder();
                sb.append("총 햄버거 갯수: ").append(sumnum).append("\n");
                sb.append("총 칼로리: ").append(sum);

                System.out.println(sb);
            }

            System.out.println("프로그램 종료");
            return ;









        }









    }
}