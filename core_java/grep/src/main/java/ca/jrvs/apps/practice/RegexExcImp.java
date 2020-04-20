package ca.jrvs.apps.practice;

import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc {

  /**
   * return true if filename extension is jpg or jpeg (case insensitive)
   *
   * @param filename
   * @return
   */
  @Override
  public boolean matchJpeg(String filename) {
    return Pattern.matches("(.+\\.jpe*g)$", filename);
  }

  /**
   * return true if ip is valid
   *
   * @param ip
   * @return
   */
  @Override
  public boolean matchIp(String ip) {
    return Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", ip);
  }

  /**
   * return true if line is empty (e.g empty, whitespace, tabs, etc..)
   *
   * @param line
   * @return
   */
  @Override
  public boolean isEmptyLine(String line) {
    return Pattern.matches("[\\t\\n\\s]*", line);
  }
}
