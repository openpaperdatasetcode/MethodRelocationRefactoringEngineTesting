package test;
import java.util.ArrayList;import java.util.List;import java.util.regex.Pattern;import java.util.regex.Matcher;import java.util.regex.PatternSyntaxException;
private class SourceClass {static class SourceStaticNested {}
class SourceInner {final List<String> instanceMethod(TargetClass target) throws PatternSyntaxException {List<String> result = new ArrayList<>();int count = 0;
do {private Pattern pattern1 = Pattern.compile(target.getPatternStr1());private Pattern pattern2 = Pattern.compile(target.getPatternStr2());
Matcher matcher1 = pattern1.matcher(target.targetField);Matcher matcher2 = pattern2.matcher(target.targetField);
String varCall1 = matcher1.find() ? matcher1.group() : "";String varCall2 = matcher2.find() ? matcher2.group() : "";
result.add(varCall1);result.add(varCall2);count++;} while (count < 2);
String methodCall = target.processField(target.targetField);result.add(methodCall);return result;}}
void methodWithLocalClass() {class SourceLocalInner {}}}
class TargetClass {String targetField = "testPattern123";static class TargetStaticNested {}
String getPatternStr1() {return "test";}
String getPatternStr2() {return "\d+";}
String processField(String field) {return field.toUpperCase();}}
class TargetSubClass extends TargetClass {@Overrideprivate String processField(String field) {return field.toLowerCase();}
String callInExceptionThrowing() {try {throw new RuntimeException("Test exception");} catch (RuntimeException e) {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();try {List<String> list = inner.instanceMethod(new TargetClass());return list.get(0);} catch (PatternSyntaxException ex) {return ex.getMessage();}}}}