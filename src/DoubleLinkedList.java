import java.util.Arrays;

/**
 * Класс элемента списка
 */
class DoubleLinkedListItem<T> {

  /**
   * Ссылка на следующий элемент списка
   */
  private DoubleLinkedListItem<T> nextItem = null;
  /**
   * Ссылка на предыдущий элемент списка
   */
  private DoubleLinkedListItem<T> previousItem = null;
  public final T data;

  public DoubleLinkedListItem(T data) {
    this.data = data;
  }

  /**
   * Сеттер следующего элемента
   * @param node передаваемый элемент
   */
  public void setNextItem(DoubleLinkedListItem<T> node) {
    nextItem = node;
    node.previousItem = this;
  }

  /**
   * Геттер следующего элемента
   */
  public DoubleLinkedListItem<T> getNextItem() {
    return nextItem;
  }

  /**
   * Сеттер предыдущего элемента
   * @param node передаваемый элемент
   */
  public void setPreviousItem(DoubleLinkedListItem<T> node) {
    previousItem = node;
    node.nextItem = this;
  }

  /**
   * Геттер предыдущего элемента
   */
  public DoubleLinkedListItem<T> getPreviousItem() {
    return previousItem;
  }

  public String toString() {
    return data.toString();
  }

  public String toRepr() {
    return String.format("""
        prev: %s
        this: %s
        next: %s
        """, previousItem, this, nextItem);
  }
}

/**
 * Класс списка
 */
public class DoubleLinkedList<T> {

  /**
   * Указатель на начальный элемент списка
   */
  private DoubleLinkedListItem<T> head;
  /**
   * Указатель на текущий элемент списка
   */
  private DoubleLinkedListItem<T> curNode;
  /**
   * Указатель на конечный элемент списка
   */
  private DoubleLinkedListItem<T> tail;
  /**
   * Количество элементов в списке
   */
  private int size = 0;

  public DoubleLinkedList(DoubleLinkedListItem<T> head) {
    this.head = head;
    this.size = 1;
    DoubleLinkedListItem<T> ptr = head;
    while (ptr.getNextItem() != null && ptr.getNextItem() != this.head) {
      ptr = ptr.getNextItem();
      this.size += 1;
    }
    this.curNode = head;
    this.tail = ptr;
    if (tail.getNextItem() == null) {
      tail.setNextItem(head);
    }
  }

  /**
   * Констркутор списка без элементов
   */
  public DoubleLinkedList() {
    this.head = null;
    this.tail = null;
    this.curNode = null;
    this.size = 0;
  }

  /**
   * Конструктор списка, позволяющий создать
   * список из последовательности элементов определенного типа
   *
   * @param vals последовательность элементов
   */
  @SafeVarargs
  public static <E> DoubleLinkedListItem<E> createNodeSeq(E... vals) {
    DoubleLinkedListItem<E> head = new DoubleLinkedListItem<>(vals[0]);
    DoubleLinkedListItem<E> ptr = head;
    for (int i = 1; i < vals.length; i++) {
      DoubleLinkedListItem<E> newNode = new DoubleLinkedListItem<>(vals[i]);
      ptr.setNextItem(newNode);
      ptr = ptr.getNextItem();
    }

    head.setPreviousItem(ptr);
    return head;
  }

  /**
   * Метод проверки списка на пустоту
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Метод перемещения указателя на начало списка
   */
  public void curToHead(){
    curNode = head;
    System.out.print("Указатель перемещен на начало списка \n");
  }

  /**
   * Метод перемещения указателя в конец списка
   */
  public void curToTail(){
    curNode = tail;
    System.out.println("Указатель перемещен в конец списка\n");
  }

  /**
   * Метод перемещения указателя влево
   */
  public void curLeft(){
    curNode = curNode.getPreviousItem();
    System.out.print("Указатель перемещен влево \n");
  }

  /**
   * Метод перемещения указателя вправо
   */
  public void curRight(){
    curNode = curNode.getNextItem();
    System.out.print("Указатель перемещен вправо \n");
  }

  /**
   * Метод вставки элемента после указателя
   *
   * @param data вставляемый элемент
   */
  public void insertAfterNode(T data) {
    if (curNode == null){
      System.out.println("Указатель пуст, сначала введите список");
      return;
    }
    DoubleLinkedListItem<T> prevNode = curNode;
    DoubleLinkedListItem<T> newNode = new DoubleLinkedListItem<T>(data);
    DoubleLinkedListItem<T> nextNode = prevNode.getNextItem();
    if (tail.data == curNode.data) {
      tail = newNode;
    }
    nextNode.setPreviousItem(newNode);
    prevNode.setNextItem(newNode);
    size += 1;
  }

  /**
   * Метод вставки элемента перед указателем
   *
   * @param data вставляемый элемент
   */
  public void insertBeforeNode(T data) {
    DoubleLinkedListItem<T> nextNode = curNode;
    DoubleLinkedListItem<T> newNode = new DoubleLinkedListItem<T>(data);
    DoubleLinkedListItem<T> prevNode = nextNode.getPreviousItem();
    if (curNode == null){
      System.out.println("Указатель пуст, сначала введите список");
      return;
    }
    if (head.data == curNode.data) {
      head = newNode;
    }
    prevNode.setNextItem(newNode);
    nextNode.setPreviousItem(newNode);
    size += 1;
  }

  /**
   * Метод удаления элемента перед указателем
   */
  public void removeBeforeNode() {
    DoubleLinkedListItem<T> nextNode = curNode;
    DoubleLinkedListItem<T> delNode = nextNode.getPreviousItem();
    DoubleLinkedListItem<T> prevNode = delNode.getPreviousItem();
    if (size == 0){
      System.out.println("Список пуст, удаление невозможно");
      return;
    }
    if (size == 1) {
      head = null;
      curNode = null;
      tail = null;
    } else {
      if (tail == delNode) {
        tail = tail.getPreviousItem();
      }
      if (head == delNode) {
        head = nextNode;
      }
      nextNode.setPreviousItem(prevNode);
      prevNode.setNextItem(nextNode);
    }
    size -= 1;
  }

  /**
   * Метод удаления элемента после указателя
   */
  public void removeAfterNode() {
    DoubleLinkedListItem<T> prevNode = curNode;
    DoubleLinkedListItem<T> delNode = prevNode.getNextItem();
    DoubleLinkedListItem<T> nextNode = delNode.getNextItem();
    if (size == 0){
      System.out.println("Список пуст, удаление невозможно");
      return;
    }
    if (size == 1) {
      head = null;
      curNode = null;
      tail = null;
    } else {
      if (tail == delNode) {
        tail = tail.getPreviousItem();
      }
      if (head == delNode) {
        head = nextNode;
      }
      prevNode.setNextItem(nextNode);
      nextNode.setPreviousItem(prevNode);
    }
    size -= 1;
  }

  /**
   * Метод изменения местами предыдущего элемента указателя и следующего
   */
  public void changePrevWithNext(){
    DoubleLinkedListItem<T> prevNode = curNode.getPreviousItem();
    DoubleLinkedListItem<T> prevPrevNode = prevNode.getPreviousItem();
    DoubleLinkedListItem<T> nextNode = curNode.getNextItem();
    DoubleLinkedListItem<T> nextNextNode = nextNode.getNextItem();
    if (prevNode == head){
      head = nextNode;
    }
    if (tail == nextNode){
      tail = prevNode;
    }
    if (curNode.data == head.data){
      tail = curNode.getNextItem();
    }
    if (curNode == tail){
      head = curNode.getPreviousItem();
    }
    nextNode.setNextItem(curNode);
    nextNode.setPreviousItem(prevPrevNode);
    prevNode.setPreviousItem(curNode);
    prevNode.setNextItem(nextNextNode);

  }

  /**
   * Метод строкового представления списка
   */
  public String toString() {
    Object[] vals = new Object[size];
    int i = 0;
    DoubleLinkedListItem<T> ptr = head;
    while (ptr != tail) {
      vals[i] = ptr.data;
      i++;
      ptr = ptr.getNextItem();
    }
    vals[size - 1] = tail;
    return Arrays.toString(vals);
  }

  /**
   * Метод поэлементного представления списка
   */
  public String repr() {
    String[] vals = new String[size];
    int i = 0;
    DoubleLinkedListItem<T> ptr = head;
    while (ptr != tail) {
      System.out.println(ptr);
      vals[i] = ptr.toRepr();
      i++;
      ptr = ptr.getNextItem();
    }
    vals[size - 1] = tail.toRepr();
    return String.join("______\n", vals);
  }
}


