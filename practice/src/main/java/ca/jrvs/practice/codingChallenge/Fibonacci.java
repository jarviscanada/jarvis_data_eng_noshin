package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-77d79a5c6c1c49fd8cf348eea1323f45
 */
public class Fibonacci {

  /**
   * Big-O: O(N)
   * Justification: only the array of length N+1
   *
   * @param N the postion of the number in the fibonacci sequence which needs to be found
   * @return the number at N-th position
   */
  public int fibonacciWithRecursion(int N) {
    int[] memo = new int[N + 1];
    return memoizedFibonacci(N, memo);
  }

  public int memoizedFibonacci(int N, int[] memo) {
    int result;
    if (memo[N] != 0) {
      return memo[N];
    }
    if (N <= 1) {
      result = N;
    } else {
      result = memoizedFibonacci(N - 1, memo) + memoizedFibonacci(N - 2, memo);
    }
    memo[N] = result;
    return result;
  }

  /**
   * Big-O: O(N)
   * Justification: only N values of the array are accessed
   *
   * @param N the postion of the number in the fibonacci sequence which needs to be found
   * @return the number at N-th position
   */
  public int fibonacciWithDP(int N) {
    if (N <= 1) {
      return N;
    } else {
      int[] fibonacciArray = new int[N + 1];
      fibonacciArray[1] = 1;
      fibonacciArray[2] = 1;
      for (int i = 3; i < fibonacciArray.length; i++) {
        fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
      }
      return fibonacciArray[N];
    }
  }
}
