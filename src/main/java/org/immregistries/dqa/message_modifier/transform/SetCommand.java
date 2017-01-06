package org.immregistries.dqa.message_modifier.transform;

public class SetCommand extends Command {
  private ReferenceParsed targetReference = null;
  private String stringValue = "";
  private ReferenceParsed sourceReference = null;
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
}
