package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.ModifyRequest;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;

public class InsertFirstFunction implements CallFunction {
	
	public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException{
		String resultText = modifyRequest.getMessageFinal();
		ReferenceParsed targetReference = callCommand.getTargetReference();
		
		String segID = callCommand.getParameterMap().get("SEGMENT ID");
        resultText = segID + "|" + "\n" + resultText + "\n" ; 
		modifyRequest.setMessageFinal(resultText);
	}

}
