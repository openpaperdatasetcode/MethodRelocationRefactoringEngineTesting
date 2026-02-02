package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
protected class SourceClass {static class StaticNested {}
class MemberInner {}
@MethodAnnotationprotected void process(TargetClass target) {super(); // Super constructor invocationvariableCall(target);new StaticNested();new MemberInner();}
private void variableCall(TargetClass target) {if (target.isValid()) { // if/else conditions positionString result = target::format; // instanceReference::methodNameSystem.out.println(result);} else {String result = target.defaultFormat();System.out.println(result);}}}
class TargetClass implements Formattable {private String data = "test";
public boolean isValid() {return data != null;}
// Call_method: normal typepublic String format() {return "Formatted: " + data;}
public String defaultFormat() {// Local inner class (target_feature)class LocalFormatter {String getDefault() {return "Default: " + data;}}return new LocalFormatter().getDefault();}}
interface Formattable {}