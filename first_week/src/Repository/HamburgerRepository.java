package Repository;

import Dto.Hamburger.Hamburger;
import Dto.Ingredient.Ingredient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Optional;


//햄버거 레포지토리 (넣은 순서로 찾음)
public class HamburgerRepository {
    private LinkedHashMap<Long, Hamburger> hamburgers;
    private long sequence;


    public HamburgerRepository() {
        this.hamburgers = new LinkedHashMap<>();
    }

    public LinkedHashMap<Long, Hamburger> getHamburgers() {
        return hamburgers;
    }

    public Hamburger save(Hamburger hamburger) {
        hamburgers.put(++sequence, hamburger);
        return hamburger;
    }

    public Optional<Hamburger> findById(long id) {
        return Optional.ofNullable(hamburgers.get(id));
    }
}
