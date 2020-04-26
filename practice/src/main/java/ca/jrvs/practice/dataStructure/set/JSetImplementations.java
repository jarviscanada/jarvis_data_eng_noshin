package ca.jrvs.practice.dataStructure.set;

public class JSetImplementations {

  public static void main(String[] args) {
    System.out.println("Creating HashJSet..");
    HashJSet<Integer> hashJSet = new HashJSet<>();

    System.out.println("Size of Set : "+ hashJSet.size());

    System.out.println("Adding elements...");
    hashJSet.add(1);
    hashJSet.add(2);
    hashJSet.add(3);
    hashJSet.add(4);
    hashJSet.add(5);

    System.out.println("Size of Set : "+ hashJSet.size());

    System.out.println("Set contains 1 : "+hashJSet.contains(1));
    System.out.println("Set contains 0 : "+hashJSet.contains(0));

    System.out.println("Removing 1...");
    hashJSet.remove(1);
    System.out.println("Set contains 1 : "+hashJSet.contains(1));

    System.out.println("Size of Set : "+ hashJSet.size());

    System.out.println("Clearing set...");
    hashJSet.clear();
    System.out.println("Size of Set : "+ hashJSet.size());

    System.out.println("Creating TreeJSet..");
    TreeJSet<Integer> treeJSet = new TreeJSet<>();

    System.out.println("Size of Set : "+ treeJSet.size());

    System.out.println("Adding elements...");
    treeJSet.add(1);
    treeJSet.add(2);
    treeJSet.add(3);
    treeJSet.add(4);
    treeJSet.add(5);

    System.out.println("Size of Set : "+ treeJSet.size());

    System.out.println("Set contains 1 : "+treeJSet.contains(1));
    System.out.println("Set contains 0 : "+treeJSet.contains(0));

    System.out.println("Removing 1...");
    treeJSet.remove(1);
    System.out.println("Set contains 1 : "+treeJSet.contains(1));

    System.out.println("Size of Set : "+ treeJSet.size());

    System.out.println("Clearing set...");
    treeJSet.clear();
    System.out.println("Size of Set : "+ treeJSet.size());
  }

}
