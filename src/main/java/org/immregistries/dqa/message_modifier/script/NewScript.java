package org.immregistries.dqa.message_modifier.script;

/* NewScript.java */
/* Generated By:JJTree&JavaCC: Do not edit this line. NewScript.java */
import org.immregistries.dqa.message_modifier.script.*;


/** New line translator. */
public class NewScript/*@bgen(jjtree)*/implements NewScriptTreeConstants, NewScriptConstants {/*@bgen(jjtree)*/
  protected JJTNewScriptState jjtree = new JJTNewScriptState();
  /** Main entry point. */
  public static void main(String args[]) throws ParseException {
    NewScript parser = new NewScript(System.in);
    try {
          SimpleNode n = parser.ExpressionList();
          //n.dump("");
          n.testPrint();
        } catch (Exception e) {
          System.out.println("Oops.");
          System.out.println(e.getMessage());
          e.printStackTrace();
        }
  }

  final public SimpleNode ExpressionList() throws ParseException {/*@bgen(jjtree) EXPRESSIONS */
  SimpleNode jjtn000 = new SimpleNode(JJTEXPRESSIONS);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);String s;
  String result="";
    try {
System.out.println("");
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case IF:
        case FOR:
        case USE:
        case CALL:
        case ID:{
          ;
          break;
          }
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        s = command();
        jj_consume_token(22);
result+=s+";"+"\u005cn";
      }
      jj_consume_token(0);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
System.out.println("");
      System.out.println(result);
      {if ("" != null) return jjtn000;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String command() throws ParseException {/*@bgen(jjtree) COMMAND */
  SimpleNode jjtn000 = new SimpleNode(JJTCOMMAND);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);String string;
  String s = "";
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case FOR:
      case CALL:
      case ID:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case FOR:{
          string = forSelector();
s += string;
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case IF:{
          string = ifCommand();
s += string;
          break;
          }
        case CALL:
        case ID:{
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case ID:{
            string = assignment();
s += string;
            break;
            }
          case CALL:{
            string = functionCall();
s += string;
            break;
            }
          default:
            jj_la1[2] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
          }
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
        }
      case USE:{
        s = useCommand();
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
{if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String useCommand() throws ParseException {/*@bgen(jjtree) USE_COMMAND */
    SimpleNode jjtn000 = new SimpleNode(JJTUSE_COMMAND);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token token;
  Token token2;
  String s;
    try {
      jj_consume_token(USE);
      token = jj_consume_token(UNQUOTED_STRING);
      jj_consume_token(CONTEXT_SELECTOR);
      token2 = jj_consume_token(UNQUOTED_STRING);
jjtree.closeNodeScope(jjtn000, true);
        jjtc000 = false;
jjtn000.value = token.image+"_"+token2.image;
        s = "use " + token.image + " :: " + token2.image;
        {if ("" != null) return s;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String assignment() throws ParseException {/*@bgen(jjtree) ASSIGNMENT */
    SimpleNode jjtn000 = new SimpleNode(JJTASSIGNMENT);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String ref;
    String sta;
    String s;
    try {
      ref = reference();
      jj_consume_token(23);
      sta = statement();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
s = ref + "=" + sta;
        {if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String ifCommand() throws ParseException {/*@bgen(jjtree) IF */
    SimpleNode jjtn000 = new SimpleNode(JJTIF);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String s, comp, func, ref;
    Token token, token2;
    try {
      jj_consume_token(IF);
      jj_consume_token(LEFT_PARENTHESIS);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ID:{
        ref = reference();
s = "if" + "(" + ref + ")";
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case EQUAL:
        case DIFFERENT:
        case GREATER_THAN:
        case LESSER_THAN:{
          comp = comparison();
          token = jj_consume_token(STRING);
s = "if (" + ref + " " + comp + " " + token.image + ")";
                                jjtn000.value = token.image;
          break;
          }
        default:
          jj_la1[5] = jj_gen;
          ;
        }
        break;
        }
      case STRING:{
        token = jj_consume_token(STRING);
        comp = comparison();
        token2 = jj_consume_token(STRING);
s = "if" + "(" + token.image + comp + token.image + ")";
            jjtn000.value = token.image + "_" + token2.image;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(RIGHT_PARENTHESIS);
      jj_consume_token(THEN);
      func = functionCall();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
s += "then" + func;
        {if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String comparison() throws ParseException {/*@bgen(jjtree) COMP */
    SimpleNode jjtn000 = new SimpleNode(JJTCOMP);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token token;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUAL:{
        token = jj_consume_token(EQUAL);
        break;
        }
      case DIFFERENT:{
        token = jj_consume_token(DIFFERENT);
        break;
        }
      case GREATER_THAN:{
        token = jj_consume_token(GREATER_THAN);
        break;
        }
      case LESSER_THAN:{
        token = jj_consume_token(LESSER_THAN);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return token.image;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String forSelector() throws ParseException {/*@bgen(jjtree) FOR */
    SimpleNode jjtn000 = new SimpleNode(JJTFOR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String ref;
    try {
      jj_consume_token(FOR);
      ref = referencePointer();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return ref;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String referencePointer() throws ParseException {/*@bgen(jjtree) REF_POINTER */
    SimpleNode jjtn000 = new SimpleNode(JJTREF_POINTER);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String s;
    try {
      jj_consume_token(24);
      s = reference();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return "$"+s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String reference() throws ParseException {/*@bgen(jjtree) REFERENCE */
  SimpleNode jjtn000 = new SimpleNode(JJTREFERENCE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);String s, ref, id;
    try {
      id = refId();
s = id;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case CONTEXT_SELECTOR:{
        ref = refContext();
s += ref;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
{if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String refContext() throws ParseException {/*@bgen(jjtree) REF_CONTEXT */
    SimpleNode jjtn000 = new SimpleNode(JJTREF_CONTEXT);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String id, s;
    try {
      jj_consume_token(CONTEXT_SELECTOR);
      id = refId();
jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
s = " :: " + id;
            {if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String refId() throws ParseException {/*@bgen(jjtree) REF_ID */
SimpleNode jjtn000 = new SimpleNode(JJTREF_ID);
boolean jjtc000 = true;
jjtree.openNodeScope(jjtn000);String id, field, sub, s;
    try {
      id = segId();
s = id;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 25:{
        jj_consume_token(25);
        field = fieldNum();
s += "-" + field;
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 26:{
          jj_consume_token(26);
          sub = subNum();
s+= "." + sub;
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          ;
        }
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
{if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  final public String functionCall() throws ParseException {/*@bgen(jjtree) FUNCTION */
  SimpleNode jjtn000 = new SimpleNode(JJTFUNCTION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token name;
  String s;
    try {
      jj_consume_token(CALL);
      name = jj_consume_token(UNQUOTED_STRING);
      jj_consume_token(LEFT_PARENTHESIS);
      s = arguments();
      jj_consume_token(RIGHT_PARENTHESIS);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.value = name.image;
      {if ("" != null) return name.image+"("+s+")";}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String arguments() throws ParseException {/*@bgen(jjtree) ARGS */
    SimpleNode jjtn000 = new SimpleNode(JJTARGS);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token arg_name;
  Token arg_value;
  String s="";
    try {
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case STRING:{
          ;
          break;
          }
        default:
          jj_la1[11] = jj_gen;
          break label_2;
        }
        arg_name = jj_consume_token(STRING);
        jj_consume_token(27);
        arg_value = jj_consume_token(STRING);
s+= arg_name.image + "=>" + arg_value.image;
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 28:{
          jj_consume_token(28);
s += ",";
          break;
          }
        default:
          jj_la1[12] = jj_gen;
          ;
        }
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
jjtn000.value = s;
    {if ("" != null) return s;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String statement() throws ParseException {/*@bgen(jjtree) STATEMENT */
  SimpleNode jjtn000 = new SimpleNode(JJTSTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token token;
  String s;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case STRING:{
        token = jj_consume_token(STRING);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.value = token.image;
      {if ("" != null) return token.image;}
        break;
        }
      case 24:{
        s = referencePointer();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return s;}
        break;
        }
      case PRESET_VALUE:{
        token = jj_consume_token(PRESET_VALUE);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.value = token.image;
      {if ("" != null) return token.image;}
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String segId() throws ParseException {/*@bgen(jjtree) SEG_ID */
  SimpleNode jjtn000 = new SimpleNode(JJTSEG_ID);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token id;
  String s="";
    try {
      id = jj_consume_token(ID);
jjtn000.value = id.image;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 29:{
        s = repetitionSelector();
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
s = id.image + s;
        {if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String fieldNum() throws ParseException {/*@bgen(jjtree) FIELD_NUM */
  SimpleNode jjtn000 = new SimpleNode(JJTFIELD_NUM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token num;
  String s, rep;
    try {
      num = jj_consume_token(NUM);
s = (String) num.image;
        jjtn000.value = num.image;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 29:{
        rep = repetitionSelector();
s += rep;
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String subNum() throws ParseException {/*@bgen(jjtree) SUB_NUM */
  SimpleNode jjtn000 = new SimpleNode(JJTSUB_NUM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token num;
  String s, rep;
    try {
      num = jj_consume_token(NUM);
s = (String) num.image;
        jjtn000.value = num.image;
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 29:{
        rep = repetitionSelector();
s += rep;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
{if ("" != null) return s;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public String repetitionSelector() throws ParseException {/*@bgen(jjtree) REPETION_SELECTOR */
    SimpleNode jjtn000 = new SimpleNode(JJTREPETION_SELECTOR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token num;
    String s;
    try {
      jj_consume_token(29);
      num = jj_consume_token(NUM);
      jj_consume_token(30);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
s = "[" + num.image + "]";
        jjtn000.value = num.image;
        {if ("" != null) return s;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public String comments() throws ParseException {/*@bgen(jjtree) COMMENTS */
    SimpleNode jjtn000 = new SimpleNode(JJTCOMMENTS);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);String s="";
    try {
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
s = "\u005c\u005c" + s;
        {if ("" != null) return s;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public NewScriptTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[17];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x760,0x40,0x600,0x620,0x760,0xf0000,0xc00,0xf0000,0x8000,0x4000000,0x2000000,0x800,0x10000000,0x1002800,0x20000000,0x20000000,0x20000000,};
   }

  /** Constructor with InputStream. */
  public NewScript(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public NewScript(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new NewScriptTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public NewScript(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new NewScriptTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public NewScript(NewScriptTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(NewScriptTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 17; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[31];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 17; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 31; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
