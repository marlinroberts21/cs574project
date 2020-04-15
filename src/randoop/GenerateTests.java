package randoop;

import randoop.main.Main;

public class GenerateTests {
  public static void main(String[] args) {
    String[] randoopArgs = {
      "gentests",
      "--classlist=randoop/myclasses.txt",
      "--junit-output-dir=src",
      "--junit-package-name=dk.brics.string.tests",
      "--time-limit=120"
    };
    Main.main(randoopArgs);
  }
}
