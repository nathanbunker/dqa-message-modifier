package org.immregistries.dqa.message_modifier;

import org.immregistries.dqa.message_modifier.ModifierService;
import org.immregistries.dqa.message_modifier.ModifyRequest;

import junit.framework.TestCase;

public class ModifyRequestTest extends TestCase {
  public void testInitial() {
    // We testing basic commands with references
    {
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String modificationScript = "PID-5=\"\";\n";
      String messageFinal = "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String modificationScript = "PID[1]-5=\"\";\n";
      String messageFinal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String modificationScript = "PID-5[1]=\"\";\n";
      String messageFinal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }
    {
      String messageOriginal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      String modificationScript = "PID-5.1=\"\";\n";
      String messageFinal = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
      runTest(messageOriginal, modificationScript, messageFinal);
    }

  }

  private void runTest(String messageOriginal, String modificationScript, String messageFinal) {
    ModifierService modifierService = new ModifierService();
    ModifyRequest modifyRequest = new ModifyRequest();
    modifyRequest.setMessageOriginal(messageOriginal);
    modifyRequest.setModificationScript(modificationScript);
    modifierService.modify(modifyRequest);
    assertEquals(messageFinal, modifyRequest.getMessageFinal());
  }
}
