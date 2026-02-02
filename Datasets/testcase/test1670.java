package test;
import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class SourceClass<T> {private static int staticField;
{new Runnable() {};new Thread() {};}
@MyAnnotationvoid varargsMethod(TargetClass... params) {for (TargetClass param : params) {new TargetClass(param.field);overloadedMethod();}
do {try {super.toString();variableCall();staticField++;} catch (Exception e) {}} while (params.length > 0);}
public int overloadedMethod() {return 0;}
private void variableCall() {}
class InnerClass {public String callMethod() {staticMethod();super.toString();return "";}
public static void staticMethod() {}}}
protected class TargetClass {String field;
private TargetClass(String field) {this.field = field;}
class MemberInner {}}