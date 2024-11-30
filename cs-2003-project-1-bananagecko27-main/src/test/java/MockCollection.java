package test.java;

import java.util.ArrayList;
import java.util.Iterator;
import main.java.BasicCollectionInterface;

/** A mock implementation of {@code BasicCollectionInterface} to use for testing. */
public class MockCollection<E> implements BasicCollectionInterface<E> {

  private ArrayList<E> list = new ArrayList<>();

  /**
   * Adds the specified element to this collection.
   * @param elt element to be added to this collection
   * @return {@code true} if the element was added to this collection
   */
  public boolean add(E elt) {
    return this.list.add(elt);
  }

  /**
   * Adds all elements to this collection.
   * @param collection the collection of elements to add
   */
  public void addAll(BasicCollectionInterface<? extends E> collection) {
    for (E elt : collection) {
      this.add(elt);
    }
  }

  /**
   * Adds all elements to this collection.
   * @param elts the array of elements to add
   */
  public void addArray(E[] elts) {
    for (E elt : elts) {
      this.add(elt);
    }
  }

  /** Removes all the elements from this collection. */
  public void clear() {
    this.list.clear();
  }

  /**
   * Returns {@code true} if the specified element is in this collection.
   * @param elt the element being checked
   * @return {@code true} if the element is present in this collection
   */
  public boolean contains(E elt) {
    return this.list.contains(elt);
  }

  /**
   * Returns {@code true} if this collection contains no elements.
   * @return {@code true} if this collection contains no elements
   */
  public boolean isEmpty() {
    return this.list.isEmpty();
  }

  /**
   * Returns an iterator for the elements of this collection.
   * @return an iterator for elements of this collection
   */
  public Iterator<E> iterator() {
    return this.list.iterator();
  }

  /**
   * Removes the first occurrence of the specified element from this collection if it is present.
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the specified element is removed
   */
  public boolean remove(E elt) {
    return this.list.remove(elt);
  }

  /**
   * Returns the number of elements in this collection.
   * @return the number of elements in this collection
   */
  public int size() {
    return this.list.size();
  }
}
