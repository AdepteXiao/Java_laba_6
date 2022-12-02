/**
 *
 */
public class Main {

  /**
   * @param args Точка входа в программу
   */
  public static void main(String[] args) {

    UI.mainM();
  }
}

class UI {
  /**
   * Экземпляр класса содержащего методы ввода разных типов
   */
  private static final Inputer inp = new Inputer();

  /**
   * Метод реализующий логику меню программы
   */
  public static void mainM() {

    int menuChoice;
    int listT;
    DoubleLinkedList list = null;
    do {
      listT = chooseT();
      if (listT == 1) {
        try {
          System.out.println("Введите значения\n");
          Integer[] ints = inp.getIntLine();

          list = new DoubleLinkedList<>(DoubleLinkedList.createNodeSeq(ints));
        } catch (NumberFormatException e) {
          System.out.println("Некорректное значение: " + e.getMessage());
        }

      }
      if (listT == 2) {
        try {
          System.out.println("Введите значения\n");
          String[] strings = inp.getStrLine();
          list = new DoubleLinkedList<>(DoubleLinkedList.createNodeSeq(strings));
        } catch (NullPointerException e) {
          System.out.println("Некорректное значение");
        }
      }
    } while (list == null);
    do {
      menu();
      menuChoice = inp.getInt();
      switch (menuChoice) {
        case 1 -> System.out.println(list.isEmpty() ? "Список пуст" : "Список не пуст");
        case 2 -> {
          System.out.println("Введите значение:");
          switch (listT) {
            case 1 -> list.insertAfterNode(inp.getInt());
            case 2 -> list.insertAfterNode(inp.getString());
          }
        }
        case 3 -> {
          System.out.println("Введите значение:");
          switch (listT) {
            case 1 -> list.insertBeforeNode(inp.getInt());
            case 2 -> list.insertBeforeNode(inp.getString());
          }
        }
        case 4 -> list.removeBeforeNode();
        case 5 -> list.removeAfterNode();
        case 6 -> list.changePrevWithNext();
        case 7 -> list.curLeft();
        case 8 -> list.curRight();
        case 9 -> list.curToHead();
        case 10 -> list.curToTail();
        case 11 -> System.out.println(list);
        case 12 -> System.out.println("Программа завершена");
        default -> System.out.println("Некорректный ввод");
      }
    } while (menuChoice != 12);
  }

  /**
   * Метод вывода меню программы
   */
  public static void menu() {
    System.out.println("""
        1. Проверить на пустоту
        2. Вставить элемент после указателя
        3. Вставить элемент до указателя
        4. Удалить элемент перед указателем
        5. Удалить элемент после указателя
        6. Поменять местами предыдущий и следующий элемент за указателем
        7. Переместить указатель влево
        8. Переместить указатель вправо
        9. Переместить указатель в начало
        10.Переместить указатель в конец
        11.Вывести список
        12.Выход""");
  }

  /**
   * @return выбор типа списка
   */
  public static int chooseT() {
    int listT;
    System.out.println(" Выберите тип списка:\n1. Integer\n2. String");
    do {
      listT = inp.getInt();
      if (listT != 1 && listT != 2) {
        System.out.println("Некорректный ввод");
      }
    } while (listT != 1 && listT != 2);
    return listT;
  }
}