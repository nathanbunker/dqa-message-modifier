/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.immregistries.dqa.message_modifier.transform;

import org.immregistries.dqa.message_modifier.legacy.Transform;

/**
 *
 * @author nathan
 */
public class ReferenceParsed {

  protected String segment;
  protected int field;
  protected int segmentRepeat = 1;
  protected int fieldRepeat = 1;
  protected boolean fieldRepeatSet = false;
  protected int subfield;
  protected boolean subfieldSet = false;
  protected int subsubfield;
  protected String boundSegment = null;
  protected int boundRepeat = 1;
  protected boolean all = false;
  protected String testCaseId = null;

  public String getSegment() {
    return segment;
  }

  public void setSegment(String segment) {
    this.segment = segment;
  }

  public int getField() {
    return field;
  }

  public void setField(int field) {
    this.field = field;
  }

  public int getSegmentRepeat() {
    return segmentRepeat;
  }

  public void setSegmentRepeat(int segmentRepeat) {
    this.segmentRepeat = segmentRepeat;
  }

  public int getFieldRepeat() {
    return fieldRepeat;
  }

  public void setFieldRepeat(int fieldRepeat) {
    this.fieldRepeat = fieldRepeat;
  }

  public boolean isFieldRepeatSet() {
    return fieldRepeatSet;
  }

  public void setFieldRepeatSet(boolean fieldRepeatSet) {
    this.fieldRepeatSet = fieldRepeatSet;
  }

  public int getSubfield() {
    return subfield;
  }

  public void setSubfield(int subfield) {
    this.subfield = subfield;
  }

  public boolean isSubfieldSet() {
    return subfieldSet;
  }

  public void setSubfieldSet(boolean subfieldSet) {
    this.subfieldSet = subfieldSet;
  }

  public int getSubsubfield() {
    return subsubfield;
  }

  public void setSubsubfield(int subsubfield) {
    this.subsubfield = subsubfield;
  }

  public String getBoundSegment() {
    return boundSegment;
  }

  public void setBoundSegment(String boundSegment) {
    this.boundSegment = boundSegment;
  }

  public int getBoundRepeat() {
    return boundRepeat;
  }

  public void setBoundRepeat(int boundRepeat) {
    this.boundRepeat = boundRepeat;
  }

  public boolean isAll() {
    return all;
  }

  public void setAll(boolean all) {
    this.all = all;
  }

  public String getTestCaseId() {
    return testCaseId;
  }

  public void setTestCaseId(String testCaseId) {
    this.testCaseId = testCaseId;
  }

}
