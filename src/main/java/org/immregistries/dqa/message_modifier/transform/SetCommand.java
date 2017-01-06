package org.immregistries.dqa.message_modifier.transform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.immregistries.dqa.message_modifier.ModifyRequest;


public class SetCommand extends Command {
  private ReferenceParsed targetReference = null;
  private String stringValue = "";
  private ReferenceParsed sourceReference = null;
  
  @Override
  public void doTransform(ModifyRequest modifyRequest) throws IOException {
    String resultText = modifyRequest.getMessageOriginal();
      
      int count = 1;
      if (targetReference.isAll()) {
        count = countSegments(resultText, targetReference);
      }
      for (int i = 1; i <= count; i++) {
        if (targetReference.isAll()) {
          targetReference.segmentRepeat = i;
        }
        
        String value;
        if (sourceReference != null) {
          value = getValueFromHL7(resultText, sourceReference, modifyRequest);
        } else {
          value = stringValue;
        }
        
        resultText = setValueInHL7(value, resultText, targetReference, modifyRequest);
      }
    modifyRequest.setMessageFinal(resultText);
    
  }
  
  private String setValueInHL7(String newValue, String resultText, ReferenceParsed t, ModifyRequest modifyRequest) throws IOException {

    BufferedReader inResult = new BufferedReader(new StringReader(resultText));
    boolean foundBoundStart = false;
    boolean foundBoundEnd = false;
    int boundCount = 0;
    resultText = "";
    String lineResult;
    int repeatCount = 0;
    String prepend = "";
    while ((lineResult = inResult.readLine()) != null) {
      lineResult = lineResult.trim();
      if (lineResult.length() > 0) {
        if (t.boundSegment != null && !foundBoundEnd) {
          boolean skip = false;
          if (lineResult.startsWith(t.boundSegment + "|")) {
            boundCount++;
            if (!foundBoundStart) {
              if (boundCount == t.boundRepeat) {
                foundBoundStart = true;
              }
            } else if (foundBoundStart) {
              foundBoundEnd = true;
            }
            skip = true;
          } else if (foundBoundStart) {
            if (!lineResult.startsWith(t.segment + "|")) {
              skip = true;
            }
          } else {
            skip = true;
          }
          if (skip) {
            resultText += lineResult + modifyRequest.getSegmentSeparator();
            continue;
          }
        }
        if (lineResult.startsWith(t.segment + "|")) {
          repeatCount++;
          if (t.segmentRepeat == repeatCount) {
            int pos = lineResult.indexOf("|");
            int count = (lineResult.startsWith("MSH|") || lineResult.startsWith("FHS|") || lineResult.startsWith("BHS|")) ? 2 : 1;
            while (pos != -1 && count < t.field) {
              pos = lineResult.indexOf("|", pos + 1);
              count++;
            }
            if (pos == -1) {
              while (count < t.field) {
                lineResult += "|";
                count++;
              }
              pos = lineResult.length();
              lineResult += "||";
            }

            boolean isMSH2 = lineResult.startsWith("MSH|") && t.field == 2;
            count = 1;
            pos++;
            int tildePos = pos;
            while (tildePos != -1 && count < t.fieldRepeat) {
              int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", tildePos);
              int endPosBar = lineResult.indexOf("|", tildePos);
              if (endPosBar == -1) {
                endPosBar = lineResult.length();
              }
              if (endPosTilde == -1 || endPosTilde >= endPosBar) {
                tildePos = -1;
                pos = endPosBar;
              } else {
                tildePos = endPosTilde + 1;
                pos = tildePos;
                count++;
              }
            }
            if (tildePos == -1) {
              while (count < t.fieldRepeat) {
                prepend = "~" + prepend;
                count++;
              }
            }

            count = 1;
            while (pos != -1 && count < t.subfield) {
              int posCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
              int endPosBar = lineResult.indexOf("|", pos);
              if (endPosBar == -1) {
                endPosBar = lineResult.length();
              }
              int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", pos);
              if (endPosTilde == -1) {
                endPosTilde = lineResult.length();
              }
              if (posCaret == -1 || (posCaret > endPosBar || posCaret > endPosTilde)) {
                // there's no caret, so add it to value, keep same
                // position
                while (count < t.subfield) {
                  prepend = prepend + "^";
                  count++;
                }
                if (endPosTilde < endPosBar) {
                  pos = endPosTilde;
                } else {
                  pos = endPosBar;
                }
                break;
              } else {
                pos = posCaret + 1;
              }
              count++;
            }
            if (pos != -1) {
              if (t.subsubfield > 0) {
                count = 1;
                while (pos != -1 && count < t.subsubfield) {
                  int posAmper = isMSH2 ? -1 : lineResult.indexOf("&", pos);
                  int endPosBar = lineResult.indexOf("|", pos);
                  if (endPosBar == -1) {
                    endPosBar = lineResult.length();
                  }
                  int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", pos);
                  if (endPosTilde == -1) {
                    endPosTilde = lineResult.length();
                  }
                  int endPosCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
                  if (endPosCaret == -1) {
                    endPosCaret = lineResult.length();
                  }
                  int endPos = endPosCaret;
                  if (endPosTilde < endPos) {
                    endPos = endPosTilde;
                  }
                  if (endPosBar < endPos) {
                    endPos = endPosBar;
                  }

                  if (posAmper == -1 || (posAmper > endPos)) {
                    // there's no ampersand, so add it to the value, keep the
                    // same position
                    while (count < t.subsubfield) {
                      prepend = prepend + "&";
                      count++;
                    }
                    pos = endPos;
                  } else {
                    pos = posAmper + 1;
                  }
                  count++;
                }
              }

              int endPosBar = lineResult.indexOf("|", pos);
              if (endPosBar == -1) {
                endPosBar = lineResult.length();
                lineResult += "|";
              }
              int endPosCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
              int endPosRepeat = isMSH2 ? -1 : lineResult.indexOf("~", pos);
              int endPos = endPosBar;
              if (endPosRepeat != -1 && endPosRepeat < endPos) {
                endPos = endPosRepeat;
              }
              if (endPosCaret != -1 && endPosCaret < endPos) {
                endPos = endPosCaret;
              }
              if (t.subsubfield > 0) {
                int endPosAmper = isMSH2 ? -1 : lineResult.indexOf("&", pos);
                if (endPosAmper != -1 && endPosAmper < endPos) {
                  endPos = endPosAmper;
                }
              }
              String lineNew = lineResult.substring(0, pos);

              if (!newValue.equals("")) {
                lineNew += prepend + newValue;
              }
              lineNew += lineResult.substring(endPos);
              lineResult = lineNew;
            }
          }
        }
        resultText += lineResult + modifyRequest.getSegmentSeparator();
      }
    }
    return resultText;
  }

  
  public int countSegments(String resultText, ReferenceParsed t) throws IOException {
    BufferedReader inResult = new BufferedReader(new StringReader(resultText));
    String lineResult;
    int count = 0;
    while ((lineResult = inResult.readLine()) != null) {
      lineResult = lineResult.trim();
      if (lineResult.startsWith(t.segment + "|")) {
        count++;
      }
    }
    return count;
  }




  
  public ReferenceParsed getTargetReference() {
    return targetReference;
  }
  public void setTargetReference(ReferenceParsed targetReference) {
    this.targetReference = targetReference;
  }
  public String getStringValue() {
    return stringValue;
  }
  public void setStringValue(String stringValue) {
    this.stringValue = stringValue;
  }
  public ReferenceParsed getSourceReference() {
    return sourceReference;
  }
  public void setSourceReference(ReferenceParsed sourceReference) {
    this.sourceReference = sourceReference;
  }

  protected static String getValueFromHL7(final String resultText, ReferenceParsed t, ModifyRequest modifyRequest) throws IOException {
    BufferedReader inResult;
    {
      if (t.testCaseId != null) 
      {
        Map<String, String> messageMap = modifyRequest.getMessageMap();
        if (messageMap == null || !messageMap.containsKey(t.testCaseId))
        {
          return "";
        }
        inResult = new BufferedReader(new StringReader(messageMap.get(t.testCaseId)));
      }
      else {
      inResult = new BufferedReader(new StringReader(resultText));
      }
    }
    boolean foundBoundStart = false;
    boolean foundBoundEnd = false;
    int boundCount = 0;
    String lineResult;
    int repeatCount = 0;
    while ((lineResult = inResult.readLine()) != null) {
      lineResult = lineResult.trim();
      if (lineResult.length() > 0) {
        if (t.boundSegment != null && !foundBoundEnd) {
          boolean skip = false;
          if (lineResult.startsWith(t.boundSegment + "|")) {
            boundCount++;
            if (!foundBoundStart) {
              if (boundCount == t.boundRepeat) {
                foundBoundStart = true;
              }
            } else if (foundBoundStart) {
              foundBoundEnd = true;
            }
            skip = true;
          } else if (foundBoundStart) {
            if (!lineResult.startsWith(t.segment + "|")) {
              skip = true;
            }
          } else {
            skip = true;
          }
          if (skip) {
            continue;
          }
        }
        if (lineResult.startsWith(t.segment + "|")) {
          repeatCount++;
          if (t.segmentRepeat == repeatCount) {
            int pos = lineResult.indexOf("|");
            int count = (lineResult.startsWith("MSH|") || lineResult.startsWith("FHS|") || lineResult.startsWith("BHS|")) ? 2 : 1;
            while (pos != -1 && count < t.field) {
              pos = lineResult.indexOf("|", pos + 1);
              count++;
            }
            if (pos == -1) {
              return "";
            }

            pos++;
            count = 1;
            boolean isMSH2 = lineResult.startsWith("MSH|") && t.field == 2;
            while (pos != -1 && count < t.subfield) {
              int posCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
              int endPosBar = lineResult.indexOf("|", pos);
              if (endPosBar == -1) {
                endPosBar = lineResult.length();
              }
              int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", pos);
              if (endPosTilde == -1) {
                endPosTilde = lineResult.length();
              }
              if (posCaret == -1 || (posCaret > endPosBar || posCaret > endPosTilde)) {
                if (count < t.subfield) {
                  return "";
                }
                if (endPosTilde < endPosBar) {
                  pos = endPosTilde;
                } else {
                  pos = endPosBar;
                }
                break;
              } else {
                pos = posCaret + 1;
              }
              count++;
            }
            if (t.subsubfield > 0) {
              count = 1;
              while (pos != -1 && count < t.subsubfield) {
                int posAmper = isMSH2 ? -1 : lineResult.indexOf("&", pos);
                int endPosCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
                if (endPosCaret == -1) {
                  endPosCaret = lineResult.length();
                }
                int endPosBar = lineResult.indexOf("|", pos);
                if (endPosBar == -1) {
                  endPosBar = lineResult.length();
                }
                int endPosTilde = isMSH2 ? -1 : lineResult.indexOf("~", pos);
                if (endPosTilde == -1) {
                  endPosTilde = lineResult.length();
                }
                if (posAmper == -1 || (posAmper > endPosBar || posAmper > endPosTilde || posAmper > endPosCaret)) {
                  if (count < t.subsubfield) {
                    return "";
                  }
                  if (endPosCaret < endPosTilde) {
                    pos = endPosCaret;
                  } else if (endPosTilde < endPosBar) {
                    pos = endPosTilde;
                  } else {
                    pos = endPosBar;
                  }
                  break;
                } else {
                  pos = posAmper + 1;
                }
                count++;
              }
            }
            if (pos != -1) {
              int endPosBar = lineResult.indexOf("|", pos);
              if (endPosBar == -1) {
                endPosBar = lineResult.length();
              }
              int endPosAmper = isMSH2 ? -1 : lineResult.indexOf("&", pos);
              int endPosCaret = isMSH2 ? -1 : lineResult.indexOf("^", pos);
              int endPosRepeat = isMSH2 ? -1 : lineResult.indexOf("~", pos);
              int endPos = endPosBar;
              if (endPosRepeat != -1 && endPosRepeat < endPos) {
                endPos = endPosRepeat;
              }
              if (endPosCaret != -1 && endPosCaret < endPos) {
                endPos = endPosCaret;
              }
              if (endPosAmper != -1 && endPosAmper < endPos) {
                endPos = endPosAmper;
              }
              return lineResult.substring(pos, endPos);
            }
          }
        }
      }
    }
    return "";
  }

  
}
