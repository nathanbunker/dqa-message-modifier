package org.immregistries.dqa.message_modifier;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.immregistries.dqa.message_modifier.script.NewScript;
import org.immregistries.dqa.message_modifier.script.Token;

public class ModifierService {
  public ModifierService() {
    // anything to do?
  }

  public void modify(ModifyRequest modifyRequest) {
    String messageText = modifyRequest.getMessageOriginal();
    try {
      NewScript newScript = new NewScript(new ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8"))); 
      Token token;
      while ((token = newScript.getNextToken()) != null)
      {
        System.out.println("--> " + token.getValue());
      }
      
    } catch (IOException ioe) {
      System.err.println(ioe);
    }

    modifyRequest.setMessageFinal(messageText);
  }

}
