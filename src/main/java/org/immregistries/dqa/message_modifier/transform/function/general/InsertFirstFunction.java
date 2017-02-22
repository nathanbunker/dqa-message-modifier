package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.immregistries.dqa.message_modifier.ModifyRequest;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;

public class InsertFirstFunction implements CallFunction {
	
	public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException{
		String resultText = modifyRequest.getMessageFinal();
		ReferenceParsed targetReference = callCommand.getTargetReference();
		
		String segID = callCommand.getParameterMap().get("SEGMENT ID");
		// String segmentToAdd = segID + "|";

        BufferedReader inResult = new BufferedReader(new StringReader(resultText));
        resultText = segID + "|";
        String line = inResult.readLine();
        
        while(line != null){
        	resultText += line + "\n";
        	line = inResult.readLine();
        }
        resultText = resultText.substring(0, resultText.length()-1);
		modifyRequest.setMessageFinal(resultText);
	}

}
