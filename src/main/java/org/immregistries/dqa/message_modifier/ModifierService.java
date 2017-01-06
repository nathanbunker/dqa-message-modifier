package org.immregistries.dqa.message_modifier;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.immregistries.dqa.message_modifier.script.CharStream;
import org.immregistries.dqa.message_modifier.script.NewScript;
import org.immregistries.dqa.message_modifier.script.SimpleNode;
import org.immregistries.dqa.message_modifier.transform.Command;
import org.immregistries.dqa.message_modifier.transform.SetCommand;

public class ModifierService {
  public ModifierService() {
    // anything to do?
  }

  public void modify(ModifyRequest modifyRequest) {
    String messageText = modifyRequest.getMessageOriginal();

    List<Command> commandList = new ArrayList<>();
    try {
//      NewScript parser = new NewScript(
//          new ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8")));
//      SimpleNode n = parser.ExpressionList();
//      SetCommand setCommand = n.createSetCommand();
//      commandList.add(setCommand);
//      // Need to generate a list of commands

      for (Command command : commandList) {
        if (command instanceof SetCommand) {
          command.doTransform(modifyRequest);
        }
      }
    } catch (Exception ioe) {
      ioe.printStackTrace();
    }
    modifyRequest.setMessageFinal(messageText);
  }

}
