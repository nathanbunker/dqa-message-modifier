package org.immregistries.dqa.message_modifier.transform;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.ModifyRequest;

public class InsertAfterFunction implements CallFunction {
	
	public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException{
		System.out.println("InsertAfter Function TO DO");
	}

}
