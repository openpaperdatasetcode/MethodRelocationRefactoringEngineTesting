package test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Retention (RetentionPolicy.RUNTIME)
@Target (ElementType.METHOD)
@interface TargetProcessAnnotation {
String desc () default "Process target class inner instance";
}

interface DataProcessor {
void process (String data);
String getProcessedResult ();
}

class SourceParentClass {
protected String parentProtectedField = "parent_protected_data_1"; 
protected List<String> parentInstanceMethod(int count) {
List<String> result = new ArrayList<>();
for (int i = 0; i < count; i++) {
result.add("parent_item_" + i);
}
return result;
}
}

package other.diff;

import test.TargetClass;
import test.TargetClass.TargetInner;

public class DiffPackageConstructorHelper {

public static TargetInner createTargetInnerInstance (TargetClass target, String initData) {
return target.new TargetInner (initData);
}
}
abstract class SourceClass extends SourceParentClass {
private TargetClass targetField;

public class SourceMemberInner {
private String innerData;
public SourceMemberInner(String data) {
this.innerData = data;
}
public String getInnerData () {
return innerData;
}
public List<String> processTargetInner(TargetClass.TargetInner targetInner) {
List<String> result = new ArrayList<>();
result.add("SourceMemberInner process: " + targetInner.getInnerData());
return result;
}
}

@TargetProcessAnnotation (desc = "Instance method to process TargetClass and its inner class")
@Override 
public void instanceMethod (TargetClass target, int processCount) {
this.targetField = target;
target.setTargetBaseData ("source_init_data");
String targetBaseData = target.getTargetBaseData ();
System.out.println ("Target base data:" + targetBaseData);
TargetClass.TargetInner targetInner = other.diff.DiffPackageConstructorHelper.createTargetInnerInstance (
target,
"inner_init_data_1");
String outerProtectedData = super.parentProtectedField;
System.out.println ("Access outer protected field:" + outerProtectedData);
Supplier<List<String>> lambdaSupplier = () -> {
SourceMemberInner memberInner = new SourceMemberInner ("lambda_member_inner_1");
List<String> lambdaResult = memberInner.processTargetInner (targetInner);
lambdaResult.addAll (super.parentInstanceMethod (1));
return lambdaResult;
};
List<String> lambdaProcessResult = lambdaSupplier.get();
System.out.println("Lambda process result: " + lambdaProcessResult);
int currentCount = 0;
while (currentCount < processCount) {
targetInner.setInnerData ("updated_data_" + currentCount);
System.out.println ("While loop process (" + currentCount + "):" + targetInner.getInnerData ());
targetInner.process (targetInner.getInnerData ());
System.out.println ("TargetInner processed result:" + targetInner.getProcessedResult ());
currentCount++;
}
try {
Method reflectMethod = TargetClass.TargetInner.class.getMethod ("getInnerData");
String reflectedData = (String) reflectMethod.invoke (targetInner);
System.out.println ("Reflection get inner data:" + reflectedData);
Method processMethod = TargetClass.TargetInner.class.getMethod ("process", String.class);
processMethod.invoke (targetInner, "reflection_process_data");
} catch (Exception e) {
System.out.println ("Reflection handled exception:" + e.getMessage ());
}
Runnable anonRunnable = new Runnable () {
@Override
public void run () {
System.out.println ("Anonymous inner class execute: process completed");
SourceMemberInner anonMemberInner = new SourceMemberInner ("anon_member_inner_data");
System.out.println ("Anonymous inner class use SourceMemberInner:" + anonMemberInner.getInnerData ());
}
};
anonRunnable.run ();
}
public abstract String getSourceAbstractData ();

public TargetClass getTargetField () {
return targetField;
}
}

protected class TargetClass implements DataProcessor {
private String targetBaseData;

public class TargetInner implements DataProcessor {
private String innerData;
private String processedResult;
public TargetInner (String initData) {
this.innerData = initData;
this.processedResult = "init_with_base:" + TargetClass.this.targetBaseData;
}
public String getInnerData () {
return innerData;
}
public void setInnerData (String innerData) {
this.innerData = innerData;
}
@Override
public void process (String data) {
this.processedResult = "TargetInner processed:" + data;
}
@Override
public String getProcessedResult () {
return processedResult;
}
}

public TargetClass () {
this.targetBaseData = "default_target_base_data";
}

public String getTargetBaseData () {
return targetBaseData;
}

public void setTargetBaseData (String targetBaseData) {
this.targetBaseData = targetBaseData;
}

@Override
public void process (String data) {
this.targetBaseData = "TargetClass processed:" + data;
}

@Override
public String getProcessedResult () {
return this.targetBaseData;
}
}