package org.immregistries.dqa.message_modifier.script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.immregistries.dqa.message_modifier.transform.Command;
import org.immregistries.dqa.message_modifier.transform.ReferenceParsed;
import org.immregistries.dqa.message_modifier.transform.SetCommand;
import org.immregistries.dqa.message_modifier.transform.CallCommand;
import org.immregistries.dqa.message_modifier.script.NewScriptTreeConstants;

/* Generated By:JJTree: Do not edit this line. SimpleNode.java Version 6.0 */
/* JavaCCOptions:MULTI=false,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class SimpleNode implements Node {

  protected Node parent;
  protected Node[] children;
  protected int id;
  protected Object value;
  protected NewScript parser;

  public SimpleNode(int i) {
    id = i;
  }

  public SimpleNode(NewScript p, int i) {
    this(i);
    parser = p;
  }

  public void testPrint() {
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        System.out.println("Node : " + n.toString());
        System.out.println("Value : [" + n.value + "]");
        if (n != null) {
          n.testPrint();
        }
      }
    }
  }
  
  public ReferenceParsed getReference(SimpleNode node){
	  ReferenceParsed reference = new ReferenceParsed();
	  SimpleNode refId, segId, fieldNum, subNum;
	  
	  refId = (SimpleNode) node.jjtGetChild(0).jjtGetChild(0);
      segId = (SimpleNode) refId.jjtGetChild(0);
      reference.setSegmentName((String) segId.jjtGetValue());
      if(segId.jjtGetNumChildren() > 0){
      	SimpleNode repeatSelector = (SimpleNode) segId.jjtGetChild(0);
      	if(repeatSelector.jjtGetValue().equals("*")){
      	  reference.setSegementAll(true);
        }else{
        	reference.setSegmentRepeat(Integer.parseInt((String) repeatSelector.jjtGetValue()));
        }
      }
      if(refId.jjtGetNumChildren() > 1){
          fieldNum = (SimpleNode) refId.children[1];
          if(fieldNum.jjtGetValue().equals("*")){
        	  // ignore, this isn't supported PID-*
          }else{
        	  reference.setFieldPos(Integer.parseInt((String) fieldNum.jjtGetValue()));
          }
          if(fieldNum.jjtGetNumChildren() > 0){
          	SimpleNode repeatSelector = (SimpleNode) fieldNum.jjtGetChild(0);
          	if(repeatSelector.jjtGetValue().equals("*")){
            	  reference.setFieldRepeatAll(true);
              }else{
            	  reference.setFieldRepeat(Integer.parseInt((String) repeatSelector.jjtGetValue()));
            	  reference.setFieldRepeatSet(true);
              }
          }
      }
      if(refId.jjtGetNumChildren() > 2){
          subNum = (SimpleNode) refId.children[2];
          if(subNum.jjtGetValue().equals("*")){
        	  // ignore, this is not valid
          }else{
        	  reference.setSubfieldPos(Integer.parseInt((String) subNum.jjtGetValue()));
        	  reference.setSubfieldSet(true);
          }
          /*if(subNum.jjtGetNumChildren() > 0){
          	SimpleNode repetSelector = (SimpleNode) subNum.jjtGetChild(0);
          	reference.setSubFieldRepeat(Integer.parseInt((String) repetSelector.jjtGetValue()));
          }*/
      }
      return reference;
  }

  public List<Command> createCommandList() {
	  List<Command> commandList = new ArrayList<>();
	  ReferenceParsed targetRef = new ReferenceParsed();
	  for (int i = 0; i < children.length; ++i) {
		  SimpleNode n = (SimpleNode)children[i];
		  if(n.id == 1) {
			  SimpleNode child = (SimpleNode) n.children[0];
			  Command command = null;
			  switch(child.id) {
              	case NewScriptTreeConstants.JJTASSIGNMENT:
              		SetCommand setCommand = new SetCommand();
              		targetRef = getReference(child);
                    SimpleNode statement = (SimpleNode) child.jjtGetChild(1);
                    if(statement.jjtGetNumChildren() > 0){
                    	ReferenceParsed sourceRef = getReference((SimpleNode) statement.jjtGetChild(0));
                        setCommand.setSourceReference(sourceRef);
	                }else{
	                    String value = (String) statement.jjtGetValue();
	                    value = value.replace("\"", "");
	                    setCommand.setStringValue(value);
                    }     
                    setCommand.setTargetReference(targetRef);
                    command = setCommand;
              		break;
              		
              	case NewScriptTreeConstants.JJTFOR:
              		CallCommand callCommand = new CallCommand();
              		targetRef = getReference((SimpleNode) child.jjtGetChild(0));
              		callCommand.setTargetReference(targetRef);
              		SimpleNode functionCall = (SimpleNode) n.jjtGetChild(1);
              		String functionName = (String) functionCall.jjtGetValue();
              		SimpleNode args = (SimpleNode) functionCall.jjtGetChild(0);
              		if(!args.jjtGetValue().equals("")){
	              		String[] args_list = ((String) args.jjtGetValue()).split(",");
	              	    Map<String, String> parameters = new LinkedHashMap<>();
	              		for(int j = 0; j <args_list.length; j++){
	              			String[] s= args_list[j].split("=>");
	              			if (s.length > 1) {
	              				String key = s[0].replace("\"", "").toUpperCase();
	              				String value = s[1].replace("\"", "");
	              				parameters.put(key, value);
	              			}
	              		}
	              		callCommand.setParameterMap(parameters);
              		}
              		callCommand.setName(functionName);
              		
			  
              		command = callCommand;
              		break;
              		
              	default:
              		command = null;
              		break;
			  }
			  commandList.add(command);
		  }
	  }
	  return commandList;
  }

  public void jjtOpen() {
  }

  public void jjtClose() {
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n, int i) {
    if (children == null) {
      children = new Node[i + 1];
    } else if (i >= children.length) {
      Node c[] = new Node[i + 1];
      System.arraycopy(children, 0, c, 0, children.length);
      children = c;
    }
    children[i] = n;
  }

  public Node jjtGetChild(int i) {
    return children[i];
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.length;
  }

  public void jjtSetValue(Object value) { this.value = value; }
  public Object jjtGetValue() { return value; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() {
    return NewScriptTreeConstants.jjtNodeName[id];
  }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        SimpleNode n = (SimpleNode)children[i];
        System.out.println("[" + n.value + "]");
        if (n != null) {
          n.dump(prefix + " ");
        }
      }
    }
  }

  public int getId() {
    return id;
  }
}

/* JavaCC - OriginalChecksum=c92004f860c88d391c641c1b89b5f8b1 (do not edit this line) */

