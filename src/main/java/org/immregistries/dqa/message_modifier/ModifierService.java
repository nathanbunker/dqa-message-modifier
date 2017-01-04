package org.immregistries.dqa.message_modifier;

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
