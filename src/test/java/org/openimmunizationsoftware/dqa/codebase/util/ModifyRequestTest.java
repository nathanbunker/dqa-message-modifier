package org.openimmunizationsoftware.dqa.codebase.util;

import junit.framework.TestCase;

public class ModifyRequestTest extends TestCase {
  public void initialTests() {
    ModifierService modifierService = new ModifierService();
    ModifyRequest modifyRequest = new ModifyRequest();
    modifyRequest.setMessageOriginal("Hello!");
    modifyRequest.setModificationScript("whatever");
    modifierService.modify(modifyRequest);
    assertEquals("Hello!", modifyRequest.getMessageFinal());
  }
}
