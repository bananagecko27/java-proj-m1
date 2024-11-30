package main.java;

import java.util.Iterator;

/** Interface for the Set ADT. */
public interface BasicSetInterface<E> extends BasicCollectionInterface<E> {

  /**
   * Adds the specified element to this set.
   * @param elt element to be added to this set
   * @return {@code true} if the element was added to this set
   */
  public boolean add(E elt);

  /**
   * Adds all elements to this set.
   * @param collection the collection of elements to add
   */
  public void addAll(BasicCollectionInterface<? extends E> collection);

  /** Removes all the elements from this set. */
  public void clear();

  /**
   * Returns {@code true} if the specified element is in this set.
   * @param elt the element being checked
   * @return {@code true} if the element is present in this set
   */
  public boolean contains(E elt);

  /**
   * Returns the set difference of this set and the specified set. The set difference of two
   * sets A and B is defined as new set C that contains all elements of A that are not in B.
   * @param set the set to subtract
   * @return the set difference of this set and the speciifed set
   */
  public BasicSetInterface<E> difference(BasicSetInterface<E> set);

  /**
   * Returns {@code true} if the specified set contains the same elements as this set.
   * @param set the set of elements to check
   * @return {@code true} if the specified set is equivalent to this set
   */
  public boolean equals(BasicSetInterface<E> set);

  /**
   * Returns the set intersection of this set and the specified set. The set intersection of two
   * sets A and B is defined as new set C that contains all elements of A that are also in B.
   * @param set the set to intersect
   * @return the set intersection of this set and the speciifed set
   */
  public BasicSetInterface<E> intersection(BasicSetInterface<E> set);

  /**
   * Returns {@code true} if this set contains no elements.
   * @return {@code true} if this set contains no elements
   */
  public boolean isEmpty();

  /**
   * Returns an iterator for the elements of this set.
   * @return an iterator for elements of this set
   */
  public Iterator<E> iterator();

  /**
   * Removes the first occurrence of the specified element from this set if it is present.
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the specified element is removed
   */
  public boolean remove(E elt);

  /**
   * Returns the number of elements in this set.
   * @return the number of elements in this set
   */
  public int size();

  /**
   * Returns the set union of this set and the specified set. The set union of two
   * sets A and B is defined as new set C that contains all elements that are in A or B.
   * @param set the set to union
   * @return the set union of this set and the speciifed set
   */
  public BasicSetInterface<E> union(BasicSetInterface<E> set);
}
