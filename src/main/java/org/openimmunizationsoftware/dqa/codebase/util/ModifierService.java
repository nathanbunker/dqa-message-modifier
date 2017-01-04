package org.openimmunizationsoftware.dqa.codebase.util;

public class ModifierService {
  public ModifierService()
  {
    // anything to do?
  }
  
  public void modify(ModifyRequest modifyRequest)
  {
    modifyRequest.setMessageFinal(modifyRequest.getMessageOriginal());
  }
}
