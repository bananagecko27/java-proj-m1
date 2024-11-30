package main.java;

import java.util.Random;

/** Driver class to execute an experiment with {@code BasicSet}. */
public class Main {

  // TODO: Add the following field to BasicSet to use this experiment
  // public static int sigOps = 0;

  private interface OpInterface {
    int run();
  }

  public static void main(String[] args) {
    Random rng = new Random();
    int trials = 10;

    int[] sizes = new int[] {100, 250, 500, 1_000};
    for (int size : sizes) {
      BasicSet<Integer> setA = new BasicSet<>();
      while (setA.size() < size) setA.add(rng.nextInt(1_000));
      BasicSet<Integer> setB = new BasicSet<>();
      while (setB.size() < size) setB.add(rng.nextInt(1_000));

      Main.test(trials, "Set Difference", size, () -> {
          BasicSet.sigOps = 0;
          setA.difference(setB);
          return BasicSet.sigOps;
        });

      Main.test(trials, "Set Intersection", size, () -> {
            BasicSet.sigOps = 0;
            setA.intersection(setB);
            return BasicSet.sigOps;
          });

      Main.test(trials, "Set Union", size, () -> {
            BasicSet.sigOps = 0;
            setA.union(setB);
            return BasicSet.sigOps;
          });
    }
    
    System.out.println("Done!");
  }

  private static void test(int trials, String title, int size, OpInterface func) {
    double totalTime = 0., totalOps = 0.;
    for (int trial = 0; trial < trials; trial++) {
      long start = System.nanoTime();
      totalOps += func.run();
      long end = System.nanoTime();
      totalTime += (end - start);
    }

    System.out.println(title);
    System.out.printf("Size: %d\n", size);
    System.out.printf("Time (ns): %.2f\n", totalTime / ((double) trials));
    System.out.printf("Ops: %.2f\n", totalOps / ((double) trials));
    System.out.println();
  }
}
