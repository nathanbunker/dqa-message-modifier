package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.ModifyRequest;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;
import org.immregistries.dqa.message_modifier.transform.SetCommand;

public class ClearFunction implements CallFunction {
	
	public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException{
		System.out.println("clear Function IN PROGRESS");

		ReferenceParsed targetReference = callCommand.getTargetReference();
		String resultText = modifyRequest.getMessageFinal();
		resultText = SetCommand.clearValueInHL7(resultText, targetReference);
		System.out.println(resultText);
		modifyRequest.setMessageFinal(resultText);
	}

}
