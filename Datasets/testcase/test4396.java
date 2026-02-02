package test;
import java.util.List;import java.util.ArrayList;
class ParentTargetClass {protected void parentNormalMethod(TargetClass.TargetStaticNested nested) {nested.nestedField += "_fromParent";}}
protected class TargetClass extends ParentTargetClass {String targetField;
public static class TargetStaticNested {String nestedField;
public TargetStaticNested(String initVal) {this.nestedField = initVal;}
public void nestedInstanceMethod() {nestedField += "_nestedProcessed";}}}
private class SourceClass {private Object instanceMethod(List<TargetClass> targetList) {TargetClass.TargetStaticNested targetInner = new TargetClass.TargetStaticNested(targetList.get(0).targetField);ParentTargetClass parent = new ParentTargetClass();
enhancedForLoop: {for (TargetClass target : targetList) {variableCall(target);target.targetField = targetInner.nestedField + "_assigned";parent.parentNormalMethod(targetInner);}}
int count = 0;while (count < 1) {accessInstanceMethod(targetInner);count++;}
return targetInner.nestedField;}
private void variableCall(TargetClass target) {target.targetField += "_varUpdated";}
private void accessInstanceMethod(TargetClass.TargetStaticNested nested) {nested.nestedInstanceMethod();}
static class SourceStaticNested {void useInstanceMethod() {List<TargetClass> list = new ArrayList<>();list.add(new TargetClass());new SourceClass().instanceMethod(list);}}
void methodWithLocalInner() {class SourceLocalInner {void helper(List<TargetClass> list) {new SourceClass().instanceMethod(list);}}}}