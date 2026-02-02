package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
interface TargetInterface {}
private class TargetClass implements TargetInterface {String field1;int field2;boolean field3;
static class TargetStaticNested {String nestedField;
TargetStaticNested(String s) {nestedField = s;}}
TargetClass(String f1, int f2, boolean f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;}}
protected class SourceClass {@MethodAnnopublic final List<String> instanceMethod(TargetClass target, List<String> params) {List<String> result = new ArrayList<>();volatile int count = 0;TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested(callMethod(target));
do {target.field1 = params.get(count);target.field2 = count;target.field3 = count % 2 == 0;result.add(target.field1 + target.field2 + target.field3);count++;} while (count < params.size() && count < 3);
variableCall(target, staticNested);return result;}
private String callMethod(TargetClass target) {return target.field1 + "_processed";}
private void variableCall(TargetClass target, TargetClass.TargetStaticNested nested) {target.field1 = nested.nestedField;}}