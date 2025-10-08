package Service;

import Object.Hamburger.*;
import Service.*;

import java.io.IOException;

public class HamburgerFactory {

    public Hamburger create(String request) throws IllegalArgumentException{
        Hamburger hamburger;

        SetofHamburger burger = SetofHamburger.valueOf(request);
        switch (burger) {
            case SetofHamburger.불고기버거 -> hamburger = new Bulgogi();
            case SetofHamburger.치킨버거 -> hamburger = new Chicken();
            case SetofHamburger.치즈버거 -> hamburger = new Cheese();
            default -> throw new IllegalArgumentException();
        }

        return hamburger;
    }

}
