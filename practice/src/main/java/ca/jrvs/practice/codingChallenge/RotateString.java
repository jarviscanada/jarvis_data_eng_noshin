package ca.jrvs.practice.codingChallenge;

public class RotateString {

  /**
   * Big-O: O(n^2), where n is the length of the strings
   * Justification: contains() method is O(n^2)
   *
   * @param A first string
   * @param B second string
   * @return true/false
   */
  public boolean rotateString(String A, String B) {
      if(A.length()!=B.length()){
        return false;
      }else{
        if ((A+A).contains(B)){
          return true;
        }else{
          return false;
        }
      }
  }

}
