package test;

import java.util.List;
import java.util.ArrayList;

// Source: private normal class with anonymous inner & member inner class
private class SourceClass {
private int sourceField = 3; // "3" for VariableDeclarationStatement target_feature

// Member inner class
class SourceMemberInner {
// Private VariableDeclarationStatement (pos: inner class)
private void declareVariables(TargetClass.TargetInner targetInner) {
// Target feature: this.field (SourceClass's field) & "3"
int fieldBasedVar = SourceClass.this.sourceField;
TargetClass.TargetInner innerVar = targetInner; // Variable call
}
}

/**

Overloaded method to refactor (returns TargetClass Type)
@param targetInner Target inner class parameter
@return Processed TargetInner instance
*/
public TargetClass.TargetInner overloadedMethod(TargetClass.TargetInner targetInner) {
// Variable call
targetInner.setInnerData("processed_data");
// Raw type usage
List rawList = new ArrayList();
rawList.add(targetInner);
// Call member inner class's VariableDeclarationStatement method
new SourceMemberInner().declareVariables(targetInner);
// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous class uses target: " + targetInner.getInnerData());
}
};
anonTask.run();
return targetInner;
}

/**

Overloaded method (overload_exist feature)
@param targetInner Target inner class parameter
@param extra Extra string parameter
@return Processed TargetInner instance
*/
public TargetClass.TargetInner overloadedMethod(TargetClass.TargetInner targetInner, String extra) {
TargetClass.TargetInner processed = overloadedMethod(targetInner);
processed.setInnerData(processed.getInnerData() + "_" + extra);
return processed;
}
}

// Target: default normal class with implements & member inner class
class TargetClass implements DataProcessor {
private String targetField;

// Member inner class (target_inner)
public class TargetInner {
private String innerData;

public String getInnerData() {
return innerData;
}

public void setInnerData(String innerData) {
this.innerData = innerData;
}
}

@Override
public void processData(TargetInner inner) {
this.targetField = inner.getInnerData();
}

public String getTargetField() {
return targetField;
}
}

// Interface for TargetClass's implements feature
interface DataProcessor {
void processData(TargetClass.TargetInner inner);
}