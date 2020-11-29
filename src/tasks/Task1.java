package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);

    // Создаём LinkedHashMap idsPersons (id - person).
    Map<Integer, Person> idsPersons = new LinkedHashMap<>();

    // Заполняем idsPersons ключами (id) из personIds. Порядок элементов в idsPersons и personIds будет одинаковый.
    // Асимпотика: forEach - O(n); idsPersons.put - O(1); общая - O(n) * O(1) = O(n).
    personIds.forEach(e -> idsPersons.put(e, null));

    // Каждый елемент из persons добавляем в idsPersons.
    // Т.к. при добавлении в LinkedHashMap элемента с уже существующим ключом, порядок элементов не меняется,
    // то в idsPersons останется нужный нам порядок элементов.
    // Асимпотика: forEach - O(n); idsPersons.put - O(1); общая - O(n) * O(1) = O(n).
    persons.forEach(e -> idsPersons.put(e.getId(), e));

    // Общая асимпотика: O(n) * O(1) + O(n) * O(1) = O(n)

    // Возвращаем ArrayList из значений values мапы idsPersons.
    return new ArrayList<>(idsPersons.values());
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
