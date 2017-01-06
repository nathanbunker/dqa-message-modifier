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
    // NewScript newScript = new NewScript(new
    // ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8")));
    // Token token;
    // while ((token = newScript.getNextToken()) != null)
    // {
    // System.out.println("--> " + token.getValue());
    // }
    //
    // Need to

    List<Command> commandList = new ArrayList<>();
    try {
      NewScript parser = new NewScript(new CharStream() {
        
        @Override
        public void setTrackLineColumn(boolean trackLineColumn) {
          // TODO Auto-generated method stub
          
        }
        
        @Override
        public void setTabSize(int i) {
          // TODO Auto-generated method stub
          
        }
        
        @Override
        public char readChar() throws IOException {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public boolean getTrackLineColumn() {
          // TODO Auto-generated method stub
          return false;
        }
        
        @Override
        public int getTabSize() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public int getLine() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public int getEndLine() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public int getEndColumn() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public int getColumn() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public int getBeginLine() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public int getBeginColumn() {
          // TODO Auto-generated method stub
          return 0;
        }
        
        @Override
        public void backup(int amount) {
          // TODO Auto-generated method stub
          
        }
        
        @Override
        public char[] GetSuffix(int len) {
          // TODO Auto-generated method stub
          return null;
        }
        
        @Override
        public String GetImage() {
          // TODO Auto-generated method stub
          return null;
        }
        
        @Override
        public void Done() {
          // TODO Auto-generated method stub
          
        }
        
        @Override
        public char BeginToken() throws IOException {
          // TODO Auto-generated method stub
          return 0;
        }
      };
          new ByteArrayInputStream(modifyRequest.getModificationScript().getBytes("UTF-8")));
      SimpleNode n = parser.ExpressionList();
      SetCommand setCommand = n.createSetCommand();
      commandList.add(setCommand);
      // Need to generate a list of commands

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
