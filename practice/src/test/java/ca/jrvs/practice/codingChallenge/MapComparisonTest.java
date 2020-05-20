package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import ca.jrvs.practice.dataStructure.map.HashJMap;
import ca.jrvs.practice.dataStructure.map.JMap;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class MapComparisonTest {

  @Test
  public void testCompareMapsWithApi() {
    MapComparison mapComparison = new MapComparison();
    Map<Integer, String> m1 = new HashMap<Integer, String>();
    m1.put(1, "one");
    m1.put(4, "one");
    m1.put(2, "two");
    m1.put(3, "three");
    Map<Integer, String> m2 = new HashMap<Integer, String>();
    m2.put(1, "one");
    m2.put(2, "two");
    m2.put(3, "three");
    Map<Integer, String> m3 = new HashMap<Integer, String>();
    m2.put(1, "one");
    m2.put(2, "two");
    m2.put(3, "three");

    assertEquals(false, mapComparison.compareMapsWithApi(m1, m2));
    assertEquals(false, mapComparison.compareMapsWithApi(m2, m3));
  }

  @Test
  public void testCompareMapsWithHashJMap() {
    MapComparison mapComparison = new MapComparison();
    JMap<Integer, String> m1 = new HashJMap<Integer, String>();
    m1.put(1, "one");
    m1.put(4, "one");
    m1.put(2, "two");
    m1.put(3, "three");
    JMap<Integer, String> m2 = new HashJMap<Integer, String>();
    m2.put(1, "one");
    m2.put(2, "two");
    m2.put(3, "three");
    JMap<Integer, String> m3 = new HashJMap<Integer, String>();
    m2.put(1, "one");
    m2.put(2, "two");
    m2.put(3, "three");

    assertEquals(false, mapComparison.compareMapsWithHashJMap(m1, m2));
    assertEquals(false, mapComparison.compareMapsWithHashJMap(m2, m3));
  }
}
