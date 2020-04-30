package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.map.HashJMap;
import ca.jrvs.practice.dataStructure.map.JMap;
import java.util.Map;
import java.util.Set;

/**
 * Ticket URL : https://www.notion.so/How-to-compare-two-maps-49d0d88e0f5c4ec1b37ec23c110134ea
 */
public class MapComparison {

  /**
   * Big-O : O(n), where n is every key-value pair
   * Justification : iterates through entry set of each map only when their sizes are
   * equal.
   *
   * @param m1  first map
   * @param m2  second map
   * @param <K> type of key
   * @param <V> type of value
   * @return true if equal, false if not equal
   */
  public <K, V> boolean compareMapsWithApi(Map<K, V> m1, Map<K, V> m2) {
    if (m1.equals(m2)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Big-O : O(n), where n is each Key-Value pair
   * Justification : iterates through entry set of each map only when their sizes are
   * equal.
   *
   * @param m1  first map
   * @param m2  second map
   * @param <K> type of key
   * @param <V> type of value
   * @return true if equal, false if not equal
   */
  public <K, V> boolean compareMapsWithHashJMap(JMap<K, V> m1, JMap<K, V> m2) {
    HashJMap<K, V> hashJMap1 = (HashJMap) m1;
    HashJMap<K, V> hashJMap2 = (HashJMap) m2;
    Set<Map.Entry<K, V>> entrySet1 = hashJMap1.entrySet();
    Set<Map.Entry<K, V>> entrySet2 = hashJMap2.entrySet();
    if (entrySet1.equals(entrySet2)) {
      return true;
    } else {
      return false;
    }
  }
}
