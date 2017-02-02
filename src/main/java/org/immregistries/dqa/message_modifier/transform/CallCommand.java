package org.immregistries.dqa.message_modifier.transform;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.immregistries.dqa.message_modifier.ModifyRequest;
import org.immregistries.dqa.message_modifier.transform.CallFunction;;

public class CallCommand extends Command {
  private ReferenceParsed targetReference = null;

  private String name = "";
  private Map<String, String> parameterMap = new HashMap<>();
  
  public ReferenceParsed getTargetReference() {
    return targetReference;
  }

  public void setTargetReference(ReferenceParsed targetReference) {
    this.targetReference = targetReference;
  }
  
  public Map<String, String> getParameterMap() {
    return parameterMap;
  }
  
  public void setParameterMap(Map<String, String> parameters) {
	this.parameterMap = parameters;
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
	  CallFunction function;
	  switch(this.name){
	  	case "map":
	  		function = new MapFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "trunc":
	  		function = new TruncFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "clear":
	  		function = new ClearFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "clean":
	  		function = new CleanFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "insertbefore":
	  		function = new InsertBeforeFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "insertafter":
	  		function = new InsertAfterFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "insterfirst":
	  		function = new InsertFirstFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	case "insterlast":
	  		function = new InsertLastFunction();
	  		function.doTransform(modifyRequest, this);
	  		break;
	  	default:
	  		break;
	  }
  }

}
