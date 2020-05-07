package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Count-Primes-d2f647e230cb480b9478ddcd2973da43
 */
public class CountPrimes {

  /**
   * Big-O: O(nlogn)
   * Justification: the inner loop of the nested for loop decreases with each iteration
   *
   * @param n input number
   * @return number of primes less than n
   */
  public int count(int n) {
    boolean[] primes = new boolean[n];
    for (int i = 2; i < n; i++) {
      primes[i] = true;
    }
    for (int i = 2; i <= (int) Math.sqrt(n); i++) {
      if (primes[i]) {
        int num = 1;
        for (int j = i * i; j < n; j += num * i) {
          primes[j] = false;
        }
      }
    }
    int count = 0;
    for (int i = 2; i < n; i++) {
      if (primes[i]) {
        count++;
      }
    }
    return count;
  }
}
