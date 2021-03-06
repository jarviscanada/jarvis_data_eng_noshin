package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new LinkedList<>();
    for (File file : listFiles(getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    LinkedList<File> files = new LinkedList<>();
    File file = new File(rootDir);
    if (file.exists()) {
      if (file.isFile()) {
        files.add(file);
        return files;
      } else if (file.isDirectory()) {
        File[] contents = file.listFiles();
        if (contents != null) {
          for (File f : contents) {
            List<File> moreFiles = listFiles(f.getAbsolutePath());
            if (moreFiles != null) {
              files.addAll(moreFiles);
            }
          }
        }
      }
    }
    return files;
  }

  @Override
  public List<String> readLines(File inputFile) {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException(inputFile + " is not a file");
    }
    List<String> lines = new LinkedList<>();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(inputFile));
      String newLine = reader.readLine();
      while (newLine != null) {
        lines.add(newLine);
        newLine = reader.readLine();
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          logger.error(e.getMessage(), e);
        }
      }
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.matches(getRegex(), line);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    BufferedWriter writer = null;
    try {
      FileOutputStream fos = new FileOutputStream(new File(getOutFile()));
      OutputStreamWriter osw = new OutputStreamWriter(fos);
      writer = new BufferedWriter(osw);
      for (String line : lines) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    } finally {
      if (writer != null) {
        writer.flush();
        writer.close();
      }
    }

  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
