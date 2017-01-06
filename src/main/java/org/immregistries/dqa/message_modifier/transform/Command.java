package org.immregistries.dqa.message_modifier.transform;

import java.io.IOException;
import java.util.Random;

import org.immregistries.dqa.message_modifier.ModifyRequest;

public abstract class Command {

  public abstract void doTransform(ModifyRequest modifyRequest) throws IOException;

  protected static Random random = new Random();
}
