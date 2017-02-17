package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.ModifyRequest;
import org.immregistries.dqa.message_modifier.transform.CallCommand;

public class FixAmpersandFunction implements CallFunction {
	
	public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException{
	    String resultText = modifyRequest.getMessageFinal();
	    StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultText.length(); i++) {
          char c = resultText.charAt(i);
          if (i < 8 || c != '&') {
            sb.append(c);
          } else {
            sb.append("\\T\\");
          }
        }
        resultText = sb.toString();
	    modifyRequest.setMessageFinal(resultText);

	}

}
