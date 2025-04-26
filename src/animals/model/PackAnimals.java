package animals.model;

import java.time.LocalDate;

public abstract class PackAnimals extends Animals {
    public PackAnimals(String name, LocalDate birthDate) {
        super(name, birthDate);
    }
}
