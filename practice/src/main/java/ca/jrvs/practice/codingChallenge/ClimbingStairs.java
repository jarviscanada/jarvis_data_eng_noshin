package ca.jrvs.practice.codingChallenge;

/**
 * Ticket URL: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-77d79a5c6c1c49fd8cf348eea1323f45
 */
public class ClimbingStairs {

  /**
   * Big-O: O(n), where n = stairs
   * Justification: only n values of the array are accessed
   *
   * @param stairs number of stairs
   * @return number of ways to climb n stairs
   */
  public int climbStairs(int stairs) {
    if(stairs<=2){
      return stairs;
    }else{
      int [] stepCount = new int[stairs+1];
      stepCount[1] = 1;
      stepCount[2] = 2;
      for(int way=3; way<stepCount.length; way++){
        stepCount[way]=stepCount[way-1]+stepCount[way-2];
      }
      return stepCount[stairs];
    }
  }
}
