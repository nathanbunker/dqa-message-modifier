package org.immregistries.dqa.message_modifier;

import java.util.HashMap;
import java.util.Map;

public class ModifyRequest {
  private String messageOriginal = "";
  private String messageFinal = "";
  private String modificationScript = "";
  private Map<String, String> messageMap = new HashMap<>();
  private Exception exception = null;
  private ModifyRequestStatus modifyRequestStatus = ModifyRequestStatus.INITIALZED;

  public String getModificationScript() {
    return modificationScript;
  }
  
  public void setModificationScript(String modificationScript) {
    this.modificationScript = modificationScript;
  }
  
  public Exception getException() {
    return exception;
  }

  protected void setException(Exception exception) {
    this.exception = exception;
  }

  public ModifyRequestStatus getModifyRequestStatus() {
    return modifyRequestStatus;
  }

  protected void setModifyRequestStatus(ModifyRequestStatus modifyRequestStatus) {
    this.modifyRequestStatus = modifyRequestStatus;
  }

  public String getMessageOriginal() {
    return messageOriginal;
  }

  public void setMessageOriginal(String messageOriginal) {
    this.messageOriginal = messageOriginal;
  }

  public String getMessageFinal() {
    return messageFinal;
  }

  protected void setMessageFinal(String messageFinal) {
    this.messageFinal = messageFinal;
  }

  public void addToMessageMap(String messageKey, String message) {
    messageMap.put(messageKey, message);
  }

  public Map<String, String> getMessageMap() {
    return messageMap;
  }
}
