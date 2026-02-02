package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;import java.util.List;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {String value() default "";}
interface MyInterface {List<String> method();}
private class SourceClass {class SourceInner implements MyInterface {@MyAnn(value = "anno with " + new TargetClass().overrideMethod(1).toString())public TargetClass normalMethod(TargetClass targetParam) {try {Method method = SourceInner.class.getMethod("normalMethod", TargetClass.class);method.invoke(this, targetParam);
labeled: {for (TargetClass raw : new ArrayList()) {variableCall = raw.field;break labeled;}}} catch (Exception e) {}return targetParam;}
@Overridepublic List<String> overrideMethod(int num) {return new ArrayList<>();}
String variableCall;}}
public class TargetClass {String field;
void someMethod() {class LocalInner {}}}