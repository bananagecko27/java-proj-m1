package main.java;

import java.util.Iterator;

/**
 * Array-based implementation of the List ADT. This implementation is intended to
 * mirror (but not use) {@code java.util.ArrayList}.
 */
public class BasicArrayList<E> implements BasicListInterface<E> {

  private static final int DEFAULT_CAPACITY = 10; // the starting capacity of the array

  private Object[] items; // the array in which to store elements; DO NOT CHANGE
  private int size = 0; // the number of elements stored in the array

  /** Constructs an empty list. */
  public BasicArrayList() {
    // : implement default constructor
    this.items = new Object[DEFAULT_CAPACITY];
  }

  /**
   * Constructs a list with the input capacity as its initial array capacity.
   * @param capacity the initial capacity of the array
   */
  public BasicArrayList(int capacity) {
    // : complete parameterized constructor
    this.items = new Object[capacity];
  }

  /**
   * Constructs a list that is a deep-copy of the input list with shallow copies of the
   * elements from the input list.
   * @param list the list to be copied
   */
  public BasicArrayList(BasicArrayList<E> list) {
    // : complete copy constructor
    this.items = new Object[list.items.length];
    for (int i=0; i<list.size; i++){
      this.items[i] = list.items[i];
    }
    this.size = list.size;
    
  }

  /**
   * Appends the input element to the end of this list.
   * @param elt the element to be appended to the end of this list
   * @return {@code true} if the input element was appended to this list
   */
  public boolean add(E elt) {
    // : implement add(E)
    this.add(this.size, elt);
    return true;
  }

  /**
   * Inserts the input element at the input position in this list. Shifts the element
   * currently at that position (if any) and any subsequent elements to the right (adds one to
   * their indices).
   * @param index the index at which the input element is to be inserted
   * @param elt the element to be inserted
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() < index)}
   */
  public void add(int index, E elt) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size);
    // : implement add(int, E)
    if(this.size==this.items.length){
      Object[] tep = new Object[2*this.items.length];
      for (int i = 0; i< this.size; i++){
        tep[i] = this.items[i];
      }
      this.items = tep;
    }

    for (int i = this.size; i > index; i--){
      this.items[i] = this.items[i-1];
    }
    this.items[index] = elt;
    this.size++;
  }

  /**
   * Appends all elements in the input collection to the end of this list.
   * @param collection the collection of elements to append
   */
  public void addAll(BasicCollectionInterface<? extends E> collection) {
    for (E elt : collection) {
      this.add(elt);
    }
  }

  /** Removes all elements from this list. */
  public void clear() {
    this.size = 0; // fast clear (is a memory leak; drop BasicArrayList to free memory)
  }

  /**
   * Returns {@code true} if the input element is in this list.
   * @param elt the element being checked
   * @return {@code true} if the input element is present in this list
   */
  public boolean contains(E elt) {
    return this.indexOf(elt) >= 0;
  }

  /**
   * Deletes the element at the input position in this list and returns it. Shifts any subsequent
   * elements to the left (subtracts one from their indices).
   * @param index the index to delete
   * @return the element that was deleted
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E delete(int index) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size - 1);
    // : implement delete(int)
    
    E tep = this.get(index);
    for(int i = index; i<this.size-1; i++){
      this.items[i] = this.items[i+1];
    }
    this.size--;
    this.items[this.size] = null;
    return tep;
  }

  /**
   * Returns {@code true} if the input list contains equivalent elements in the same positions
   * as this list.
   * @param list the list of elements to check
   * @return {@code true} if the input list is equivalent to this list
   */
  public boolean equals(BasicListInterface<E> list) {
    if (this.size != list.size()) {
      return false;
    }
    Iterator<E> iter = list.iterator();
    for (int i = 0; i < this.size; i++) {
      if (!iter.next().equals(this.items[i])) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the element at the input position in this list.
   * @param index the index of element to return
   * @return the element at the input position in this list
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E get(int index) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size - 1);
    @SuppressWarnings("unchecked") // Java complains when casting Object to E
    E tmp = (E) this.items[index]; // have to cast and assign to a variable
    return tmp; // casting here can be a compilation error
  }

  /**
   * Returns the index of the first occurrence of the input element in this list. If the
   * element is not present, the method returns {@code -1}.
   * @param elt the element whose index is being looked for
   * @return either the index of the first occurrence of the input element in this list, or
   *   {@code -1} if the element is not contained in this list
   */
  public int indexOf(E elt) {
    // : implement indexOf(E)
    for(int i=0; i<this.size; i++){
      if(this.items[i] == elt)
      return i;
    }
      return -1;
  }

  /**
   * Returns {@code true} if this list contains no elements.
   * @return {@code true} if this list contains no elements
   */
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * Returns an iterator for the elements of this list.
   * @return an iterator for elements of this list
   */
  public Iterator<E> iterator() {
    return new BasicArrayListIterator();
  }

  /**
   * Removes the first occurrence of the input element from this list if it is present. Shifts
   * any subsequent elements to the left (subtracts one from their indices).
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the input element is removed
   */
  public boolean remove(E elt) {
    // : implement remove(E)
    if(this.contains(elt)){
      this.delete(this.indexOf(elt));
      return true;
      }
      return false;
  }

  /**
   * Replaces the element in this list at the input index with the input element.
   * @param index the index of the element to be replaced
   * @param elt the element to be stored in the input position in this list
   * @return the element previously at the input position in this list
   * @throws IndexOutOfBoundsException if index is out of range
   *   {@code (index < 0 || size() <= index)}
   */
  public E replace(int index, E elt) throws IndexOutOfBoundsException {
    this.assertBounds(index, this.size - 1);
    E tmp = this.get(index);
    this.items[index] = elt;
    return tmp;
  }

  /**
   * Returns the number of elements in this list.
   * @return the number of elements in this list
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns a String representation of this list. The following are examples of a standard
   * representation of a list of numbers, (e.g., "[]", "[10]", "[10, 20, 30]").
   * @return the String representation of this list
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("{");
    if (!this.isEmpty()) {
      str.append(this.items[0].toString());
      for (int i = 1; i < this.size; i++) {
        str.append(", ");
        str.append(this.items[i].toString());
      }
    }
    str.append("}");
    return str.toString();
  }

  /**
   * Asserts that the input index is within the provided bounds (inclusive).
   * @param index the index being checked
   * @param bound the upper bound for the index (inclusive)
   * @throws IndexOutOfBoundsException if the index is out of bounds
   *     {@code index < 0 || bound < index}
   */
  private void assertBounds(int index, int bound) {
    if (index < 0 || bound < index) {
      throw new IndexOutOfBoundsException(
          String.format("Index %d out of bounds for size %d", index, this.size));
    }
  }

  /** An iterator for {@code BasicArrayList}. */
  private class BasicArrayListIterator implements Iterator<E> {

    private int cursor = 0; // marks where the iterator is in the array

    /**
     * Returns {@code true} if this list has more elements to iterate over.
     * @return {@code true} if cursor has not reached the end of the array
     */
    public boolean hasNext() {
      return this.cursor < BasicArrayList.this.size;
    }

    /**
     * Returns the next element in this list.
     * @return the next element in this list
     */
    public E next() {
      @SuppressWarnings("unchecked") // Java complains when casting Object to E
      E tmp = (E) BasicArrayList.this.items[this.cursor];
      this.cursor++;
      return tmp;
    }
  }
}
