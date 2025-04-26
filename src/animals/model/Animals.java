package animals.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animals {
    private final String name;
    private final LocalDate birthDate;
    private final List<String> commands = new ArrayList<>();

    public Animals(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public static class Camel extends PackAnimals {
        public Camel(String name, LocalDate birthDate) {
            super(name, birthDate);
        }
    }
}


