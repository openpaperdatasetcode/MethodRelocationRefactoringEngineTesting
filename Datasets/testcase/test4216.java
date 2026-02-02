package com.source;
import com.target.TargetClass;import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface RefactorTestAnno {}
class SourceParentClass {protected String parentField = "parent_base_data";}
protected class SourceClass extends SourceParentClass {static class SourceStaticNested {}
class SourceMemberInner {<T extends TargetClass.TargetInner> void genericMethod(T innerObj) throws IOException {if (innerObj.getInnerField() == null) {throw new IOException("Inner field is null");}innerObj.setInnerField(innerObj.getInnerField() + "_generic_updated");}}
@RefactorTestAnnoprotected List<String> instanceMethod(TargetClass target) {List<String> result = new ArrayList<>();if (target == null) {throw new NullPointerException("Target cannot be null");}
TargetClass.TargetInner targetInner = target.new TargetInner("init_inner_field");assert targetInner.getInnerField() != null : "Target inner field should not be null";result.add(super.parentField);
try {Method innerGetMethod = TargetClass.TargetInner.class.getMethod("getInnerField");String reflectedVal = (String) innerGetMethod.invoke(targetInner);result.add(reflectedVal);} catch (Exception e) {result.add("Reflection error: " + e.getMessage());}
for (int i = 0; i < 2; i++) {if (i == 1) {continue;}variableCall(targetInner);result.add(targetInner.getInnerField());}
try {new SourceMemberInner().genericMethod(targetInner);} catch (IOException e) {result.add("IO error: " + e.getMessage());}
return result;}
private void variableCall(TargetClass.TargetInner inner) {inner.setInnerField(inner.getInnerField() + "_var_updated");}}
// com/target/TargetClass.javapackage com.target;
public class TargetClass {public class TargetInner {private String innerField;
public TargetInner(String innerField) {this.innerField = innerField;}
public String getInnerField() {return innerField;}
public void setInnerField(String innerField) {this.innerField = innerField;}}
public TargetClass() {Runnable anonymousInner = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous inner class executed");}};anonymousInner.run();}}