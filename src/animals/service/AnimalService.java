package animals.service;

import animals.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalService {
    private final List<Animals> animalList = new ArrayList<>();

    // Добавление животного в список
    public void addAnimal(Animals animal) {
        animalList.add(animal);
    }

    // Поиск животного по имени (игнорируя регистр)
    public Animals findByName(String name) {
        return animalList.stream()
                .filter(a -> a.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null); // Возвращает null, если животное не найдено
    }

    // Получение списка животных, отсортированного по дате рождения
    public List<Animals> getAllSortedByBirthDate() {
        return animalList.stream()
                .sorted(Comparator.comparing(Animals::getBirthDate)) // Сортируем по дате рождения
                .collect(Collectors.toList()); // Возвращаем новый отсортированный список
    }

    // Получение всех животных
    public List<Animals> getAll() {
        return new ArrayList<>(animalList); // Возвращаем копию списка животных
    }

    // Получение животных по типу (например, домашние или вьючные)
    public List<Animals> getByType(Class<?> clazz) {
        return animalList.stream()
                .filter(clazz::isInstance) // Фильтруем по типу (например, Pets или PackAnimals)
                .collect(Collectors.toList()); // Возвращаем отфильтрованный список
    }
}

