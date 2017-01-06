package org.immregistries.dqa.message_modifier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.message_modifier.script.NewScript;
import org.immregistries.dqa.message_modifier.script.Token;
import org.immregistries.dqa.message_modifier.transform.Command;
import org.immregistries.dqa.message_modifier.transform.SetCommand;

public class ModifierService {
  public ModifierService() {
    // anything to do?
  }

  public void modify(ModifyRequest modifyRequest) {
    String messageText = modifyRequest.getMessageOriginal();
//    try {
//      NewScript newScript = new NewScript(new ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8"))); 
//      Token token;
//      while ((token = newScript.getNextToken()) != null)
//      {
//        System.out.println("--> " + token.getValue());
//      }
// 
      // Need to 
//    } catch (IOException ioe) {
//      System.err.println(ioe);
//    }
    
    // Need to generate a list of commands
    List<Command> commandList = new ArrayList<>();

    for (Command command : commandList)
    {
      if (command instanceof SetCommand)
      {
        
      }
    }
    modifyRequest.setMessageFinal(messageText);
  }

}
