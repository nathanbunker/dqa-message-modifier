package org.immregistries.dqa.message_modifier.transform.function.general;

import java.io.IOException;

import org.immregistries.dqa.message_modifier.ModifyRequest;
import org.immregistries.dqa.message_modifier.transform.CallCommand;

public interface CallFunction {
  public void doTransform(ModifyRequest modifyRequest, CallCommand callCommand) throws IOException;
}
