package com.source;
import com.target.TargetClass;
// Source class: strictfp modifier, different package from target, with member/anonymous inner classesstrictfp class SourceClass {// Contains target's field (per condition)private TargetClass targetField = new TargetClass();
// Member inner class (source_class feature)class SourceInner {// Inner-rec class (source_inner_rec position)class SourceInnerRec {// Instance method: private modifier, returns base type (String)private String method() {variableCall();
// NullPointerException: check target field nullityif (targetField == null) {throw new NullPointerException("TargetClass instance cannot be null");}
// Empty statement (semicolon as standalone statement);
// Anonymous inner class (source_class feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceInnerRec");}};anonRunnable.run();
// Return base type result (uses target field's data)return targetField.getTargetData();}
// Variable call (uses target's field)private void variableCall() {String localVar = targetField.getTargetData();}}}}
// Target class: protected modifier, different package from source, with local inner classpackage com.target;
protected class TargetClass {private String targetData = "targetBaseData";
public String getTargetData() {return targetData;}
// Local inner class (target_class feature)public void methodWithLocalClass() {class TargetLocalInner {void processData() {System.out.println("Local inner class processes: " + targetData);}}new TargetLocalInner().processData();}}