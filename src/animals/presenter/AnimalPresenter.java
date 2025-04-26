package animals.presenter;

import animals.model.*;
import animals.service.AnimalService;

import java.util.List;

public class AnimalPresenter {
    private final AnimalService service;

    public AnimalPresenter(AnimalService service) {
        this.service = service;
    }

    // Добавление животного в сервис
    public void addAnimal(Animals animal) {
        service.addAnimal(animal);
    }

    // Получение животного по имени
    public Animals getAnimal(String name) {
        return service.findByName(name);
    }

    // Получение всех животных, отсортированных по дате рождения
    public List<Animals> getAnimalsSorted() {
        return service.getAllSortedByBirthDate();
    }

    // Получение всех животных
    public List<Animals> getAll() {
        return service.getAll();
    }

    // Получение животных по категории
    public List<Animals> getByCategory(Class<?> type) {
        return service.getByType(type);
    }
}
