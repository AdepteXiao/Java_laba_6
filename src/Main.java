import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

  public static void main(String[] args) {

    UI.mainM();
  }
}

class UI {

  /**
   * //   * Поток вывода, поддерживающий русские символы //
   */
  private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

  /**
   * Экземпляр класса содержащего методы ввода разных типов
   */
  private static final Inputer inp = new Inputer();


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
        case 1:
          System.out.println(list.isEmpty() ? "Список пуст" : "Список не пуст");
          break;
        case 2:
          System.out.println("Введите значение:");
          switch (listT) {
            case 1 -> list.insertAfterNode(inp.getInt());
            case 2 -> list.insertAfterNode(inp.getString());
          }
          break;
        case 3:
          System.out.println("Введите значение:");
          switch (listT) {
            case 1 -> list.insertBeforeNode(inp.getInt());
            case 2 -> list.insertBeforeNode(inp.getString());
          }
          break;
        case 4:
          list.removeBeforeNode();
          break;
        case 5:
          list.removeAfterNode();
          break;
        case 6:
          list.changePrevWithNext();
        case 7:
          list.curLeft();
        case 8:
          list.curRight();
        case 9:
          list.curToHead();
        case 10:
          list.curToTail();
        case 11:
          System.out.println(list.toString());
          break;
        case 12:
          System.out.println("Программа завершена");
        default:
          System.out.println("Некорректный ввод");
      }
    } while (menuChoice != 12);

  }

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

//  }
//}
//    DoubleLinkedListItem<Integer> firstNode =
//        DoubleLinkedList.createNodeSeq(1, 2, 3, 5);
//    DoubleLinkedList<Integer> list = new DoubleLinkedList<>(firstNode);
//    System.out.println(list.toString());
//  }
//