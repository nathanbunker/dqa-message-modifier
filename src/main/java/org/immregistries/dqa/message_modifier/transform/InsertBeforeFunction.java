package org.immregistries.dqa.message_modifier.transform;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.ModifyRequest;

public class InsertBeforeFunction implements CallFunction {
	
	public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException{
		System.out.println("InsertBefore Function TO DO");
	}

}
