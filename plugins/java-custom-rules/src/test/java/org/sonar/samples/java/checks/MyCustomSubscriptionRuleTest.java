package org.sonar.samples.java.checks;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.sonar.java.ast.JavaAstScanner;
import org.sonar.java.model.VisitorsBridge;
import org.sonar.samples.java.MyCustomSubscriptionRule;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifierRule;

public class MyCustomSubscriptionRuleTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void check() throws Exception {

    MyCustomSubscriptionRule customRule = new MyCustomSubscriptionRule();
    SourceFile file = JavaAstScanner
      .scanSingleFile(new File("src/test/files/MyCustomRule.java"), new VisitorsBridge(customRule));

    // Check the message raised by the check
    checkMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(4)
      .next().atLine(7)
      .next().atLine(8);

  }
}
