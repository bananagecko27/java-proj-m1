package test.java;

/**
 * DO NOT DISTRIBUTE.
 *
 * This code is intended to support the education of students associated with the Tandy School of
 * Computer Science at the University of Tulsa. It is not intended for distribution and should
 * remain within private repositories that belong to Tandy faculty, students, and/or alumni.
 */
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.junit.MatcherAssume.assumeThat;

import java.util.ArrayList;
import java.util.Iterator;
import main.java.BasicSet;
import main.java.BasicSetInterface;
import org.junit.Test;
import test.java.TUGrader.Deps;
import test.java.TUGrader.DisplayName;
import test.java.TUGrader.TestGroup;

/**
 * This class provides a set of unit tests for the {@code BasicSet} class
 * using the JUnit testing framework and the Java Reflection API.
 */
public class BasicSetTest {

  @Test
  @TestGroup("default")
  @DisplayName("BasicSet() should construct an empty set")
  @Deps({"isEmpty()", "size()"})
  public void testConstructEmptySet() {
    assertThat(new BasicSet<Integer>().isEmpty(), is(true));
    assertThat(new BasicSet<Integer>().size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName(
      "BasicSet(BasicCollectionInterface) should construct a set that contains the unique elements"
          + " of the collection")
  @Deps({"contains(E)", "size()"})
  public void testConstructSetWithUniqueElementsOfCollection() {
    MockCollection<Integer> collection = new MockCollection<>();
    collection.addArray(new Integer[] {10, 20, 30, 10, 20, 40});
    BasicSet<Integer> set = new BasicSet<>(collection);
    assertThat(set.size(), is(equalTo(4)));
    assertThat(set.contains(10), is(true));
    assertThat(set.contains(20), is(true));
    assertThat(set.contains(30), is(true));
    assertThat(set.contains(40), is(true));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicSet(BasicCollectionInterface) should not alter the input collection")
  public void testConstructorDoesNotAlterInputCollection() {
    MockCollection<Integer> collection = new MockCollection<>();
    collection.add(10);
    assumeThat(collection.size(), is(equalTo(1)));
    assumeThat(collection.contains(10), is(true));
    new BasicSet<Integer>(collection);
    assertThat(collection.size(), is(equalTo(1)));
    assertThat(collection.contains(10), is(true));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicSet(BasicSet) should clone the input set")
  @Deps({"add(E)", "size()"})
  public void testConstructCopyOfList() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assertThat(new BasicSet<Integer>(set).size(), is(equalTo(1)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicSet(BasicSet) should use a distinct store of items when cloning a set")
  @Deps({"add(E)", "size()"})
  public void testConstructCloneWithDistinctStoreOfItems() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    BasicSet<Integer> clone = new BasicSet<>(set);
    assumeThat(clone.size(), is(equalTo(1)));
    set.add(20);
    assertThat(set.size(), is(equalTo(2)));
    assertThat(clone.size(), is(equalTo(1)));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName(
      "BasicSet(BasicSet) should use shallow copies of individual elements when cloning a set")
  @Deps("contains(E)")
  public void testConstructCopyOfListWithShallowCopiesOfElements() {
    BasicSet<ArrayList<Integer>> set = new BasicSet<>();
    ArrayList<Integer> list = new ArrayList<>();
    list.add(10);
    assumeThat(set.contains(list), is(true));
    BasicSet<ArrayList<Integer>> clone = new BasicSet<>(set);
    assumeThat(clone.contains(list), is(true));
    list.add(20);
    assertThat(clone.contains(list), is(true));
  }

  @Test
  @TestGroup("constructors")
  @DisplayName("BasicSet(BasicSet) should not alter the input set")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testConstructorDoesNotAlterInputListWhenCopying() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assumeThat(set.contains(10), is(true));
    new BasicSet<Integer>(set);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(10), is(true));
  }

  @Test
  @TestGroup("add")
  @DisplayName("add(E) should add new elements to the set and increment the size")
  @Deps({"contains(E)", "size()"})
  public void testAddsNewElementToSet() {
    BasicSet<Integer> set = new BasicSet<>();
    assumeThat(set.size(), is(equalTo(0)));
    set.add(10);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(10), is(true));
    set.add(20);
    assertThat(set.size(), is(equalTo(2)));
    assertThat(set.contains(20), is(true));
    set.add(30);
    assertThat(set.size(), is(equalTo(3)));
    assertThat(set.contains(30), is(true));
  }

  @Test
  @TestGroup("add")
  @DisplayName("add(E) should return true after adding a new element to the set")
  public void testAddReturnsTrueAfterAddingNewElementToSet() {
    BasicSet<Integer> set = new BasicSet<>();
    assertThat(set.add(10), is(true));
    assertThat(set.add(20), is(true));
    assertThat(set.add(30), is(true));
  }

  @Test
  @TestGroup("add")
  @DisplayName("add(E) should not add duplicates to the set")
  @Deps({"contains(E)", "size()"})
  public void testAddDoesNotAddDuplicatesToSet() {
    BasicSet<Integer> set = new BasicSet<>();
    assumeThat(set.size(), is(equalTo(0)));
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assumeThat(set.contains(10), is(true));
    set.add(10);
    assertThat(set.size(), is(equalTo(1)));
  }

  @Test
  @TestGroup("add")
  @DisplayName("add(E) should return false when attempting to add a duplicate to the set")
  @Deps("contains(E)")
  public void testAddReturnsFalseWhenAttemptingToAddADuplicateToSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.contains(10), is(true));
    assertThat(set.add(10), is(false));
  }

  @Test
  @TestGroup("addAll")
  @DisplayName(
      "addAll(BasicCollectionInterface) should add all unique elements from the collection to the"
          + " set and update size")
  @Deps({"contains(E)", "size()"})
  public void testAddAllAddsUniqueElementsFromCollectionToSetAndUpdatesSize() {
    MockCollection<Integer> collection = new MockCollection<>();
    collection.addArray(new Integer[] {10, 20, 30});
    BasicSet<Integer> set = new BasicSet<>();
    set.addAll(collection);
    assertThat(set.size(), is(equalTo(3)));
    assertThat(set.contains(10), is(true));
    assertThat(set.contains(20), is(true));
    assertThat(set.contains(30), is(true));
  }

  @Test
  @TestGroup("addAll")
  @DisplayName("addAll(BasicCollectionInterface) should not add duplicates from the collection")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testAddAllDoesNotAddDuplicatesFromCollection() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(40);
    assumeThat(set.size(), is(equalTo(2)));
    MockCollection<Integer> collection = new MockCollection<>();
    collection.addArray(new Integer[] {10, 20, 30, 10, 20, 30});
    set.addAll(collection);
    assertThat(set.size(), is(equalTo(4)));
    assertThat(set.contains(10), is(true));
    assertThat(set.contains(20), is(true));
    assertThat(set.contains(30), is(true));
    assertThat(set.contains(40), is(true));
  }

  @Test
  @TestGroup("addAll")
  @DisplayName("addAll(BasicCollectionInterface) should not alter the input collection")
  public void testAddAllDoesNotAlterTheInputCollection() {
    MockCollection<Integer> collection = new MockCollection<>();
    collection.add(10);
    assumeThat(collection.size(), is(equalTo(1)));
    assumeThat(collection.contains(10), is(true));
    new BasicSet<>().addAll(collection);
    assertThat(collection.size(), is(equalTo(1)));
    assertThat(collection.contains(10), is(true));
  }

  @Test
  @TestGroup("clear")
  @DisplayName("clear() should empty the set of all elements")
  @Deps({"contains(E)", "isEmpty()"})
  public void testClearShouldEmptySet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.isEmpty(), is(false));
    assumeThat(set.contains(10), is(true));
    set.clear();
    assertThat(set.isEmpty(), is(true));
    assertThat(set.contains(10), is(false));
  }

  @Test
  @TestGroup("contains")
  @DisplayName(
      "contains(E) should return true if the input element has an equivalent element in the set"
          + " (using .equals)")
  @Deps("add(E)")
  public void testContainsReturnsTrueIfElementIsInSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assertThat(set.contains(10), is(true));
  }

  @Test
  @TestGroup("contains")
  @DisplayName(
      "contains(E) should return false if the input element does not have an equivalent element in"
          + " the set (using .equals)")
  public void testContainsReturnsFalseIfElementIsNotInSet() {
    assertThat(new BasicSet<>().contains(10), is(false));
  }

  @Test
  @TestGroup("difference")
  @DisplayName(
      "difference(BasicSetInterface) should return a new set C s.t. C = A when computing the"
          + " difference of A and an empty set")
  @Deps({"add(E)", "equals(BasicSetInterface)"})
  public void testDifferenceOfSetAndEmptySet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    BasicSetInterface<Integer> difference = set.difference(other);
    assertThat(set.equals(difference), is(true));
    assertThat(difference.equals(set), is(true));
    assertThat(difference, is(not(sameInstance(set))));
    assertThat(difference, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("difference")
  @DisplayName(
      "difference(BasicSetInterface) should return a new set C s.t. C is empty when computing the"
          + " difference of an empty set and A")
  @Deps({"add(E)", "isEmpty()"})
  public void testDifferenceOfEmptySetAndSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    BasicSetInterface<Integer> difference = other.difference(set);
    assertThat(difference.isEmpty(), is(true));
    assertThat(difference, is(not(sameInstance(set))));
    assertThat(difference, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("difference")
  @DisplayName(
      "difference(BasicSetInterface) should return a new set C s.t. C is empty when computing the"
          + " difference of A and itself")
  @Deps({"add(E)", "isEmpty()"})
  public void testDifferenceOfSameSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSetInterface<Integer> difference = set.difference(set);
    assertThat(difference.isEmpty(), is(true));
    assertThat(difference, is(not(sameInstance(set))));
  }

  @Test
  @TestGroup("difference")
  @DisplayName("difference(BasicSetInterface) should return a new set C s.t. C = A - B")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testDifferenceOfSets() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    set.add(40);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(40);
    BasicSetInterface<Integer> difference = set.difference(other);
    assertThat(difference.size(), is(equalTo(2)));
    assertThat(difference.contains(10), is(true));
    assertThat(difference.contains(20), is(false));
    assertThat(difference.contains(30), is(true));
    assertThat(difference.contains(40), is(false));
    assertThat(difference, is(not(sameInstance(set))));
    assertThat(difference, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("difference")
  @DisplayName("difference(BasicSetInterface) is not commutative")
  @Deps({"add(E)", "equals(BasicSetInterface)"})
  public void testDifferenceIsNotCommutative() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    set.add(40);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(40);
    assertThat(set.difference(other).equals(other.difference(set)), is(false));
  }

  @Test
  @TestGroup("difference")
  @DisplayName("difference(BasicSetInterface) should not modify either set")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testDifferenceDoesNotModifyEitherSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assumeThat(set.contains(10), is(true));
    BasicSet<Integer> other = new BasicSet<>();
    other.add(10);
    assumeThat(other.size(), is(equalTo(1)));
    assumeThat(other.contains(10), is(true));
    set.difference(other);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(10), is(true));
    assertThat(other.size(), is(equalTo(1)));
    assertThat(other.contains(10), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName("equals(BasicSetInterface) should return true when comparing empty sets")
  public void testEqualsShouldReturnTrueWhenComparingEmptySets() {
    assertThat(new BasicSet<Integer>().equals(new BasicSet<Integer>()), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicSetInterface) should return false when comparing a non-empty set and an empty"
          + " set")
  @Deps({"add(E)", "isEmpty()"})
  public void testEqualsShouldReturnFalseWhenComparingNonEmptySetAndEmptySet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.isEmpty(), is(false));
    assertThat(set.equals(new BasicSet<Integer>()), is(false));
    assertThat(new BasicSet<>().equals(set), is(false));
  }

  @Test
  @TestGroup("equals")
  @DisplayName("equals(BasicSetInterface) should return true when comparing a set to its copies")
  @Deps({"BasicSet(BasicSetInterface)", "add(E)"})
  public void testEqualsShouldReturnTrueWhenComparingASetToItsCopies() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assertThat(set.equals(new BasicSet<>(set)), is(true));
    assertThat(new BasicSet<>(set).equals(set), is(true));
    BasicSet<Integer> copy = set;
    assertThat(set.equals(copy), is(true));
    assertThat(copy.equals(set), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicSetInterface) should return true when comparing sets with equivalent elements"
          + " regardless of order")
  @Deps("add(E)")
  public void testEqualsShouldReturnTrueWhenComparingEquivalentSets() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(30);
    other.add(10);
    assertThat(set.equals(other), is(true));
    assertThat(other.equals(set), is(true));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicListInterface) should return false when comparing sets with different elements")
  @Deps("add(E)")
  public void testEqualsShouldReturnFalseWhenComparingNonEquivalentSets() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(10);
    other.add(30);
    assertThat(set.equals(other), is(false));
    assertThat(other.equals(set), is(false));
  }

  @Test
  @TestGroup("equals")
  @DisplayName(
      "equals(BasicSetInterface) should return false when comparing sets with different sizes")
  @Deps("add(int, E)")
  public void testEqualsShouldReturnFalseWhenComparingSetsWithDifferentSizes() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(10);
    other.add(20);
    assertThat(set.equals(other), is(false));
    assertThat(other.equals(set), is(false));
  }

  @Test
  @TestGroup("intersection")
  @DisplayName(
      "intersection(BasicSetInterface) should return a new set C s.t. C is empty when computing the"
          + " intersection of A and an empty set")
  @Deps({"add(E)", "isEmpty()"})
  public void testIntersectionOfSetAndEmptySet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    BasicSetInterface<Integer> intersection = set.intersection(other);
    assertThat(intersection.isEmpty(), is(true));
    assertThat(intersection, is(not(sameInstance(set))));
    assertThat(intersection, is(not(sameInstance(other))));
    intersection = other.intersection(set);
    assertThat(intersection.isEmpty(), is(true));
    assertThat(intersection, is(not(sameInstance(set))));
    assertThat(intersection, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("intersection")
  @DisplayName(
      "intersection(BasicSetInterface) should return a new set C s.t. C = A when computing the"
          + " intersection of A and itself")
  @Deps({"add(E)", "equals(BasicSetInterface)"})
  public void testIntersectionOfSameSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSetInterface<Integer> intersection = set.intersection(set);
    assertThat(intersection.equals(set), is(true));
    assertThat(set.equals(intersection), is(true));
    assertThat(intersection, is(not(sameInstance(set))));
  }

  @Test
  @TestGroup("intersection")
  @DisplayName("intersection(BasicSetInterface) should return a new set C s.t. C = A intersect B")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testIntersectionOfSets() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    set.add(40);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(40);
    BasicSetInterface<Integer> intersection = set.intersection(other);
    assertThat(intersection.size(), is(equalTo(2)));
    assertThat(intersection.contains(10), is(false));
    assertThat(intersection.contains(20), is(true));
    assertThat(intersection.contains(30), is(false));
    assertThat(intersection.contains(40), is(true));
    assertThat(intersection, is(not(sameInstance(set))));
    assertThat(intersection, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("intersection")
  @DisplayName("intersection(BasicSetInterface) is commutative")
  @Deps({"add(E)", "equals(BasicSetInterface)"})
  public void testIntersectionIsCommutative() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    set.add(40);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(40);
    assertThat(set.intersection(other).equals(other.intersection(set)), is(true));
  }

  @Test
  @TestGroup("intersection")
  @DisplayName("intersection(BasicSetInterface) should not modify either set")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testIntersectionDoesNotModifyEitherSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assumeThat(set.contains(10), is(true));
    BasicSet<Integer> other = new BasicSet<>();
    other.add(10);
    assumeThat(other.size(), is(equalTo(1)));
    assumeThat(other.contains(10), is(true));
    set.intersection(other);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(10), is(true));
    assertThat(other.size(), is(equalTo(1)));
    assertThat(other.contains(10), is(true));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("isEmpty() should return true if the set is empty (has no elements)")
  public void testIsEmptyReturnsTrueWhenSetIsEmpty() {
    assertThat(new BasicSet<>().isEmpty(), is(true));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("isEmpty() should return false if the set is not empty (has elements)")
  @Deps("add(E)")
  public void testIsEmptyReturnsFalseWhenSetIsNotEmpty() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assertThat(set.isEmpty(), is(false));
  }

  @Test
  @TestGroup("iterator")
  @DisplayName("iterator() should return an iterator with no next element for an empty set")
  public void testIteratorHasNoNextForEmptySet() {
    assertThat(new BasicSet<>().iterator().hasNext(), is(false));
  }

  @Test
  @TestGroup("iterator")
  @DisplayName("iterator() should return an iterator that iterates over the elements of the set")
  @Deps("add(E)")
  public void testIteratorIteratesOverElementsOfSet() {
    BasicSet<Integer> set = new BasicSet<>();
    Integer e1 = 10;
    set.add(e1);
    Integer e2 = 20;
    set.add(e2);
    Integer e3 = 30;
    set.add(e3);
    Iterator<Integer> iter = set.iterator();
    assertThat(iter.hasNext(), is(true));
    assertThat(iter.next(), is(anyOf(sameInstance(e1), sameInstance(e2), sameInstance(e3))));
    assertThat(iter.hasNext(), is(true));
    assertThat(iter.next(), is(anyOf(sameInstance(e1), sameInstance(e2), sameInstance(e3))));
    assertThat(iter.hasNext(), is(true));
    assertThat(iter.next(), is(anyOf(sameInstance(e1), sameInstance(e2), sameInstance(e3))));
    assertThat(iter.hasNext(), is(false));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should remove the input element from the set and decrement size")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testRemoveRemovesElementWhenPresentAndDecrementsSize() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    assumeThat(set.size(), is(equalTo(3)));
    assumeThat(set.contains(10), is(true));
    assumeThat(set.contains(20), is(true));
    assumeThat(set.contains(30), is(true));
    set.remove(10);
    assertThat(set.size(), is(equalTo(2)));
    assertThat(set.contains(10), is(false));
    set.remove(20);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(20), is(false));
    set.remove(30);
    assertThat(set.size(), is(equalTo(0)));
    assertThat(set.contains(30), is(false));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should return true after removing the element")
  @Deps({"add(E)", "contains(E)"})
  public void testRemoveReturnsTrueAfterRemovingTheElement() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.contains(10), is(true));
    assertThat(set.remove(10), is(true));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should not modify the set if the input element is not in the set")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testRemoveDoesNotModifyTheSetWhenElementIsNotContained() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assumeThat(set.contains(10), is(true));
    assumeThat(set.contains(20), is(false));
    set.remove(20);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(10), is(true));
  }

  @Test
  @TestGroup("remove")
  @DisplayName("remove(E) should return false if the input element is not in the set")
  @Deps({"add(E)", "contains(E)"})
  public void testRemoveReturnsFalseWhenElementIsNotInSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.contains(20), is(false));
    assertThat(set.remove(20), is(false));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("size() should return 0 for an empty set")
  public void testSizeReturnsZeroForEmptySet() {
    assertThat(new BasicSet<>().size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("getters")
  @DisplayName("size() should return the current size of the set")
  @Deps({"add(E)", "remove(E)"})
  public void testSizeReturnsSizeOfList() {
    BasicSet<Integer> set = new BasicSet<>();
    assumeThat(set.size(), is(equalTo(0)));
    set.add(10);
    assertThat(set.size(), is(equalTo(1)));
    set.remove(10);
    assertThat(set.size(), is(equalTo(0)));
  }

  @Test
  @TestGroup("toString")
  @DisplayName("toString() should return \"{}\" for an empty set")
  public void testToStringRepresentsEmptySet() {
    assertThat(new BasicSet<>().toString(), is(equalTo("{}")));
  }

  @Test
  @TestGroup("toString")
  @DisplayName("toString() should return \"{10}\" for a set with a single element 10")
  @Deps("add(E)")
  public void testToStringRepresentsSingleElement() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assertThat(set.toString(), is(equalTo("{10}")));
  }

  @Test
  @TestGroup("toString")
  @DisplayName(
      "toString() should return some permutation of \"{10, 20, 30}\" for a set with elements 10,"
          + " 20, and 30")
  @Deps("add(E)")
  public void testToStringRepresentsNElements() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    assertThat(
        set.toString(),
        is(
            anyOf(
                equalTo("{10, 20, 30}"),
                equalTo("{10, 30, 20}"),
                equalTo("{20, 10, 30}"),
                equalTo("{20, 30, 10}"),
                equalTo("{30, 10, 20}"),
                equalTo("{30, 20, 10}"))));
  }

  @Test
  @TestGroup("union")
  @DisplayName(
      "union(BasicSetInterface) should return a new set C s.t. C = A when computing the union of A"
          + " and an empty set")
  @Deps({"add(E)", "isEmpty()"})
  public void testUnionOfSetAndEmptySet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    BasicSetInterface<Integer> union = set.union(other);
    assertThat(union.equals(set), is(true));
    assertThat(set.equals(union), is(true));
    assertThat(union, is(not(sameInstance(set))));
    assertThat(union, is(not(sameInstance(other))));
    union = other.union(set);
    assertThat(union.equals(set), is(true));
    assertThat(set.equals(union), is(true));
    assertThat(union, is(not(sameInstance(set))));
    assertThat(union, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("union")
  @DisplayName(
      "union(BasicSetInterface) should return a new set C s.t. C = A when computing the union of A"
          + " and itself")
  @Deps({"add(E)", "equals(BasicSetInterface)"})
  public void testUnionOfSameSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSetInterface<Integer> union = set.union(set);
    assertThat(union.equals(set), is(true));
    assertThat(set.equals(union), is(true));
    assertThat(union, is(not(sameInstance(set))));
  }

  @Test
  @TestGroup("union")
  @DisplayName("union(BasicSetInterface) should return a new set C s.t. C = A union B")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testUnionOfSets() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(30);
    other.add(40);
    BasicSetInterface<Integer> union = set.union(other);
    assertThat(union.size(), is(equalTo(4)));
    assertThat(union.contains(10), is(true));
    assertThat(union.contains(20), is(true));
    assertThat(union.contains(30), is(true));
    assertThat(union.contains(40), is(true));
    assertThat(union, is(not(sameInstance(set))));
    assertThat(union, is(not(sameInstance(other))));
  }

  @Test
  @TestGroup("union")
  @DisplayName("union(BasicSetInterface) is commutative")
  @Deps({"add(E)", "equals(BasicSetInterface)"})
  public void testUnionIsCommutative() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    set.add(20);
    set.add(30);
    set.add(40);
    BasicSet<Integer> other = new BasicSet<>();
    other.add(20);
    other.add(40);
    assertThat(set.union(other).equals(other.union(set)), is(true));
  }

  @Test
  @TestGroup("union")
  @DisplayName("union(BasicSetInterface) should not modify either set")
  @Deps({"add(E)", "contains(E)", "size()"})
  public void testUnionDoesNotModifyEitherSet() {
    BasicSet<Integer> set = new BasicSet<>();
    set.add(10);
    assumeThat(set.size(), is(equalTo(1)));
    assumeThat(set.contains(10), is(true));
    BasicSet<Integer> other = new BasicSet<>();
    other.add(10);
    assumeThat(other.size(), is(equalTo(1)));
    assumeThat(other.contains(10), is(true));
    set.union(other);
    assertThat(set.size(), is(equalTo(1)));
    assertThat(set.contains(10), is(true));
    assertThat(other.size(), is(equalTo(1)));
    assertThat(other.contains(10), is(true));
  }
}
