/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.immregistries.dqa.message_modifier.legacy;

/**
 *
 * @author nathan
 */
public class Transform {

    protected String segment;
    protected int field;
    protected int segmentRepeat = 1;
    protected int fieldRepeat = 1;
    protected boolean fieldRepeatSet = false;
    protected String value;
    protected int subfield;
    protected boolean subfieldSet = false;
    protected int subsubfield;
    protected String boundSegment = null;
    protected int boundRepeat = 1;
    protected boolean all = false;
    protected String testCaseId = null;
    protected Transform valueTransform = null;
    
    
}
