package main.java;

import java.util.Iterator;

/** Interface for the Collection ADT. */
public interface BasicCollectionInterface<E> extends Iterable<E> {

  Object list = null;

/**
   * Adds the specified element to this collection.
   * @param elt element to be added to this collection
   * @return {@code true} if the element was added to this collection
   */
  public boolean add(E elt);

  /**
   * Adds all elements to this collection.
   * @param collection the collection of elements to add
   */
  public void addAll(BasicCollectionInterface<? extends E> collection);

  /** Removes all the elements from this collection. */
  public void clear();

  /**
   * Returns {@code true} if the specified element is in this collection.
   * @param elt the element being checked
   * @return {@code true} if the element is present in this collection
   */
  public boolean contains(E elt);

  /**
   * Returns {@code true} if this collection contains no elements.
   * @return {@code true} if this collection contains no elements
   */
  public boolean isEmpty();

  /**
   * Returns an iterator for the elements of this collection.
   * @return an iterator for elements of this collection
   */
  public Iterator<E> iterator();

  /**
   * Removes the first occurrence of the specified element from this collection if it is present.
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the specified element is removed
   */
  public boolean remove(E elt);

  /**
   * Returns the number of elements in this collection.
   * @return the number of elements in this collection
   */
  public int size();
}
