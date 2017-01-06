package org.immregistries.dqa.message_modifier.transform;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.immregistries.dqa.message_modifier.ModifyRequest;

public class CallCommand extends Command {

  private String name = "";
  private Map<String, String> parameterMap = new HashMap<>();
  

  public Map<String, String> getParameterMap() {
    return parameterMap;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void doTransform(ModifyRequest modifyRequest) throws IOException {
    // TODO need to call the function that is associated with the name, 
    // but we also have to think about "context"

  }

}
