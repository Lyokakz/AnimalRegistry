package animals.model;

import java.time.LocalDate;

public abstract class Pets extends Animals {
    public Pets(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}