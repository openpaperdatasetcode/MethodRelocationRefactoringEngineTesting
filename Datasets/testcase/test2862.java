package test;
import java.util.List;import java.util.ArrayList;
private class SourceClass {private TargetClass.TargetNested nestedField;
void varargsMethod(TargetClass... targets) {private TargetClass current = targets[0];variableCall(current);
if (targets.length > 1) {List<String> result = new Helper().recursiveMethod(targets, 1);} else {List<String> result = new ArrayList<>();}}
private void variableCall(TargetClass target) {nestedField = target.new TargetNested();}
protected class Helper extends SuperHelper {@OverrideList<String> recursiveMethod(TargetClass[] targets, int index) {if (index >= targets.length) {return super.recursiveMethod(targets, index);}return recursiveMethod(targets, index + 1);}}}
private class TargetClass {static class TargetNested {}}
class SuperHelper {protected List<String> recursiveMethod(TargetClass[] targets, int index) {return new ArrayList<>();}}