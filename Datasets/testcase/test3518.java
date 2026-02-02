package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected class SourceClass {public static class StaticNested {public static String formatField(String field) {return field.trim().toUpperCase();}}
public class MemberInner {public String getOuterData() {return "outer_instance_data";}}
List<String> varargsMethod(TargetClass... targets) throws NoSuchMethodException {List<String> results = new ArrayList<>();MemberInner inner = new MemberInner();
for (TargetClass target : targets) {TargetClass.Inner targetInner = target.new Inner();
// Uses outer this (SourceClass's MemberInner instance)String outerData = inner.getOuterData();results.add(outerData + "_" + target.field);
// Switch statementswitch (target.field.length()) {case 1:continue; // Continue statementcase 2:targetInner.updateField(target.field + "_case2");break;default:targetInner.updateField(target.field + "_default");}
// Expression statementexpressionStmt: System.out.println(targetInner.getInnerField());
// Constructor invocationTargetClass newTarget = new TargetClass(target.field + "_new");results.add(newTarget.field);
// Used by reflectionMethod innerMethod = TargetClass.Inner.class.getMethod("getInnerField");try {results.add(innerMethod.invoke(targetInner).toString());} catch (Exception e) {e.printStackTrace();}}
return results;}}
final class TargetClass {String field;
public TargetClass(String field) {this.field = field;}
public class Inner {private String innerField;
public void updateField(String input) {class LocalInner {String process(String s) {return SourceClass.StaticNested.formatField(s);}}this.innerField = new LocalInner().process(input);}
public String getInnerField() {return innerField;}}}
