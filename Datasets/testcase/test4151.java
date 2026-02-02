package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
final class SourceClass {private TargetClass targetField = new TargetClass();
static class SourceStaticNested {String processString(String s) {return s.toUpperCase();}}
class SourceInner {public List<String> varargsMethod(TargetClass.TargetStaticNested... nestedArgs) {List<String> result = new ArrayList<>();
private TargetClass target = SourceClass.this.targetField;private String field1Val = target.thisField1;private String field2Val = target.thisField2;private String field3Val = target.thisField3;
try {Method reflectMethod = SourceInner.class.getMethod ("varargsMethod", TargetClass.TargetStaticNested [].class);reflectMethod.invoke (this, (Object) nestedArgs);} catch (Exception e) {e.printStackTrace ();}
for (TargetClass.TargetStaticNested nested : nestedArgs) {String varCall = nested.getNestedData ();String processed = (varCall != null)? SourceClass.this.new SourceInnerHelper ().helperMethod (varCall): field1Val;result.add (processed);}
result.add(field2Val);result.add(field3Val);return result;}}
private class SourceInnerHelper {String helperMethod (String input) {return new SourceStaticNested ().processString (input);}}}
protected class TargetClass {String thisField1 = "targetField1";String thisField2 = "targetField2";String thisField3 = "targetField3";
static class TargetStaticNested {private String nestedData;
public TargetStaticNested (String nestedData) {this.nestedData = nestedData;}
String getNestedData() {return nestedData;}}}