package animals.model;

public class Count implements AutoCloseable {
    private int animalCount = 0;
    private boolean closed = false;
    private boolean usedInTry = false;

    public void add() {
        if (closed) {
            throw new IllegalStateException("Ресурс уже закрыт!");
        }
        usedInTry = true;
        animalCount++;
    }

    public int getAnimalCount() {
        if (!usedInTry || closed) {
            throw new IllegalStateException("Счетчик должен использоваться в try-with-resources и быть открыт.");
        }
        return animalCount;
    }

    @Override
    public void close() {
        closed = true;
        System.out.println("Счетчик закрыт.");
    }
}


