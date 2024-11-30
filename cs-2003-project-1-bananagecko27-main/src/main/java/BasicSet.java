package main.java;

import java.util.Iterator;

/** Interface for the Set ADT. */
public class BasicSet<E> implements BasicSetInterface<E> {

  public static int sigOps = 0;

    private BasicArrayList<E> list;
    

    public BasicSet(){
      this.list = new BasicArrayList<>();
    }

    public BasicSet(BasicSet<E> set){
      this.list = new BasicArrayList<>(set.list);
    }

    public BasicSet(BasicSetInterface<E> set){
      
      BasicSet<E> newSet = new BasicSet<>();
      for (E t : set){
        newSet.add(t);
      } 
      this.list = new BasicArrayList<>(newSet.list);
    }

    public BasicSet(BasicCollectionInterface<? extends E> set){    
      
      BasicSet<E> newSet = new BasicSet<>();
      newSet.addAll(set);
      this.list = new BasicArrayList<>(newSet.list);
    }
  

  /**
   * Adds the specified element to this set.
   * @param elt element to be added to this set
   * @return {@code true} if the element was added to this set
   */
  public boolean add(E elt){

    if(!list.contains(elt)){
        list.add(elt);
        return true;
    }
    return false;
  }
  /**
   * Adds all elements to this set.
   * @param collection the collection of elements to add
   */
  public void addAll(BasicCollectionInterface<? extends E> collection){
    for (E elt : collection){
      sigOps++;
      if(!list.contains(elt)){
        list.add(elt);
      }
    } 
  }

  /** Removes all the elements from this set. */
  public void clear(){
    list.clear();
  }

  /**
   * Returns {@code true} if the specified element is in this set.
   * @param elt the element being checked
   * @return {@code true} if the element is present in this set
   */
  public boolean contains(E elt){
    return list.contains(elt);
    
  }

  /**
   * Returns the set difference of this set and the specified set. The set difference of two
   * sets A and B is defined as new set C that contains all elements of A that are not in B.
   * @param set the set to subtract
   * @return the set difference of this set and the speciifed set
   */
  public BasicSetInterface<E> difference(BasicSetInterface<E> set){
    BasicSet<E> newSet = new BasicSet<>();
    newSet.addAll(list);

    for(E elt : set){
      sigOps++;
      if(list.contains(elt)){
        newSet.remove(elt);
      }
    } 
    return newSet;
  }

  /**
   * Returns {@code true} if the specified set contains the same elements as this set.
   * @param set the set of elements to check
   * @return {@code true} if the specified set is equivalent to this set
   */
  public boolean equals(BasicSetInterface<E> set){
    if(list.size() != set.size()){
      return false;
    }
    
    if(this.difference(set).isEmpty()){
      return true;
    }
    return false;
  }

  /**
   * Returns the set intersection of this set and the specified set. The set intersection of two
   * sets A and B is defined as new set C that contains all elements of A that are also in B.
   * @param set the set to intersect
   * @return the set intersection of this set and the speciifed set
   */
  public BasicSetInterface<E> intersection(BasicSetInterface<E> set){
    
    BasicSetInterface<E> arr = new BasicSet<>(list);
    
    /*for(E elt: set){
      for(E elt2: list){
        sigOps++;
        if(elt.equals(elt2)){
          arr.add(elt);
        }
      }
    }
    return arr; */
    return arr.difference(arr.difference(set));
    
  }

  /**
   * Returns {@code true} if this set contains no elements.
   * @return {@code true} if this set contains no elements
   */
  public boolean isEmpty(){
    return list.isEmpty();
  }

  /**
   * Returns an iterator for the elements of this set.
   * @return an iterator for elements of this set
   */
  public Iterator<E> iterator(){
    return list.iterator();
  }

  /**
   * Removes the first occurrence of the specified element from this set if it is present.
   * @param elt the element to remove
   * @return {@code true} if an occurrence of the specified element is removed
   */
  public boolean remove(E elt){
    return list.remove(elt);
  }

  /**
   * Returns the number of elements in this set.
   * @return the number of elements in this set
   */
  public int size(){
    return list.size();
    
  }

  public String toString(){
    return list.toString();
  }

  /**
   * Returns the set union of this set and the specified set. The set union of two
   * sets A and B is defined as new set C that contains all elements that are in A or B.
   * @param set the set to union
   * @return the set union of this set and the speciifed set
   */
  public BasicSetInterface<E> union(BasicSetInterface<E> set){
    BasicSet<E> arr = new BasicSet<>();
    arr.addAll(list);
    arr.addAll(set);
    return arr; 
  }
}