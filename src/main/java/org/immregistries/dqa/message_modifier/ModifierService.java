package org.immregistries.dqa.message_modifier;

import java.util.concurrent.RunnableScheduledFuture;

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
