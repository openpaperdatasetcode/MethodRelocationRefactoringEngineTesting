package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
interface MyInterface {}
interface TargetInterface {}
class SourceClass<T extends Number> implements MyInterface {private int outerPrivate;T value;
protected List<String> instanceMethod(TargetClass.InnerRec targetParam) {/**
Method Javadoc*/try {int var = targetParam.field;variableCall = var;
switch (var) {case 1:SubClass sub = new SubClass();String result = sub.strictfpMethod(targetParam);break;default:break;}
synchronized (this) {outerPrivate = 5;}
Runnable r = () -> System.out.println(this.value);r.run();
class LocalInner {void useOuter() {SourceClass.this.outerPrivate++;}}new LocalInner().useOuter();
new Runnable() {};} catch (Exception e) {}return new ArrayList<>();}
int variableCall;}
private class TargetClass<S extends CharSequence> implements TargetInterface {class InnerRec {int field;}
new Runnable() {};}
class SubClass {strictfp String strictfpMethod(TargetClass.InnerRec rec) {return rec.field + "";}}
