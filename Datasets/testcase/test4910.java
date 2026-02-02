package test;

import java.util.ArrayList;
import java.util.List;

// Parent interface for super keywords
interface ParentInterface {
String getParentData();
}

// Source: interface with static nested class and member inner class
interface SourceInterface extends ParentInterface {
// Static nested class (source_feature)
static class SourceStaticNested {
private String staticPrivateField = "static_private_data"; // For access_outer_private

public String getStaticData() {
return staticPrivateField;
}
}

// Member inner class (source_inner)
class SourceInner {
// Final instance method to be refactored (return base type: int)
public final int instanceMethod(TargetInterface.TargetInner targetInner) {
// NullPointerException
if (targetInner == null) {
throw new NullPointerException("TargetInner cannot be null");
}

int result = 0;
// Variable call: use target parameter
String innerData = targetInner.getInnerData();
result += innerData.length();

// Super keywords (call parent interface method)
result += super.getParentData().length();

// Access outer private (access SourceStaticNested's private field via method)
SourceStaticNested staticNested = new SourceStaticNested();
result += staticNested.getStaticData().length();

// Try statement
try {
int parsedValue = Integer.parseInt(targetInner.getInnerId());
result += parsedValue;
} catch (NumberFormatException e) {
// No new exception thrown
result += 0;
}

// Switch case
switch (innerData.length() % 3) {
case 0:
result += 5;
break;
case 1:
result += 10;
break;
case 2:
result += 15;
break;
default:
result += 0;
}

return result;
}
}

// Default method to get SourceInner instance
default SourceInner getSourceInner() {
return new SourceInner();
}

// Implement ParentInterface method
@Override
default String getParentData() {
return "parent_interface_data";
}
}

// Target: default interface with local inner class (target_feature)
default interface TargetInterface {
// Member inner class (target_inner)
class TargetInner {
private String innerData;
private String innerId;

public TargetInner(String data, String id) {
this.innerData = data;
this.innerId = id;
}

public String getInnerData() {
// Local inner class (target_feature)
class TargetLocalInner {
public String formatData() {
return "formatted_" + innerData;
}
}
return new TargetLocalInner().formatData();
}

public String getInnerId() {
return innerId;
}
}

// Default method to create TargetInner instance
default TargetInner createTargetInner(String data, String id) {
return new TargetInner(data, id);
}
}