package test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

// Diff-package others class (for DoStatement pos: diff_package_others)
package other.diff;

import test.TargetClass;

public class DiffPackageHelper {
public static int processWithDoStatement (TargetClass target) {
int count = 0;
do {

target.setTargetCount (target.getTargetCount () + 1);
count++;
} while (target.getTargetCount () < 2);
return count;
}
}

class OthersClass {

public static final String staticFinalProcess (String input) {
return "others_static_processed:" + input;
}

public final String instanceFinalProcess (String input) {
return "others_instance_processed:" + input;
}
}

public class SourceClass {
private TargetClass targetField;


public class SourceInner {

int instanceMethod (TargetClass target, String initData) {
SourceClass.this.targetField = target;
int baseResult = 0;

target.setTargetData (initData);
String targetData = target.getTargetData ();
baseResult += targetData.length ();
String staticNestedResult = TargetClass.TargetStatic.staticNestedProcess (targetData);
baseResult += staticNestedResult.length ();
int doStatementResult = other.diff.DiffPackageHelper.processWithDoStatement (target);
baseResult += doStatementResult;

String [] dataArray = {targetData, staticNestedResult, "array_item_3"};
Function<String, String> methodRef = OthersClass::staticFinalProcess;
for (String item : dataArray) {
String processedItem = methodRef.apply (item);
baseResult += processedItem.length ();
}

class SourceLocalInner {

int calculateListLengthSum (List<String> list) {
int sum = 0;
for (String s : list) {
sum += s.length();
}
return sum;
}
}
SourceLocalInner localInner = new SourceLocalInner();
List<String> tempList = new ArrayList<>();
tempList.add(targetData);
tempList.add(staticNestedResult);
baseResult += localInner.calculateListLengthSum(tempList);
Runnable anonRunnable = new Runnable () {
@Override
public void run () {
System.out.println ("Anonymous inner class: process completed, result=" + baseResult);
}
};
anonRunnable.run ();
return baseResult;
}

int instanceMethod (TargetClass target, int initNum) {

return instanceMethod (target, "init_num_" + initNum);
}
}

public SourceInner createSourceInner () {
return new SourceInner ();
}

public TargetClass getTargetField () {
return targetField;
}
}

public class TargetClass {

private int targetCount;
private String targetData;


public static class TargetStatic {

public static String staticNestedProcess (String input) {
return "target_static_processed:" + input;
}
}


public TargetClass () {
this.targetCount = 0;
this.targetData = "default_target_data";
}

public int getTargetCount () {
return targetCount;
}

public void setTargetCount(int targetCount) {
this.targetCount = targetCount;
}

public String getTargetData() {
return targetData;
}

public void setTargetData(String targetData) {
this.targetData = targetData;
}
}