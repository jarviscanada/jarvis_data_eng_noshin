package ca.jrvs.apps.practice;

public class RegexMain {

  public static void main(String args[]) {

    RegexExcImp regexEval = new RegexExcImp();

    System.out.println(regexEval.matchIp("192.16.0.1"));
    System.out.println(regexEval.matchIp("182.168.100.100"));
    System.out.println(regexEval.matchIp("192.168"));
    System.out.println(regexEval.matchIp("192"));
    System.out.println();
    System.out.println(regexEval.matchJpeg("abc.jpg"));
    System.out.println(regexEval.matchJpeg("abc.jpeg"));
    System.out.println(regexEval.matchJpeg(".jpg"));
    System.out.println(regexEval.matchJpeg(".jpeg"));
    System.out.println(regexEval.matchJpeg("abc.jpggx"));
    System.out.println(regexEval.matchJpeg("jpg"));
    System.out.println(regexEval.matchJpeg("jpeg"));
    System.out.println();
    System.out.println(regexEval.isEmptyLine(""));
    System.out.println(regexEval.isEmptyLine(" "));
    System.out.println(regexEval.isEmptyLine("  "));

  }

}
