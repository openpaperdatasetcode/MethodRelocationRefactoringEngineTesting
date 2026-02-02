package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value() default SourceClass.callVarargs("a", "b", "c");}
class ParentClass {public List<String> overrideMethod() {return new ArrayList<>();}}
protected class SourceClass<T extends Number> extends ParentClass {protected String outerProtected = "protected";
class MemberInner {}
void localInnerMethod() {class LocalInner {}}
@MyAnnotationprotected List<String> overrideMethod(TargetClass target) {TargetClass.Inner targetInner = target.new Inner();List<String> result = new ArrayList<>();
synchronized (TargetClass.sharedField) {result.add(TargetClass.sharedField);}
Class<?> cls = targetInner.getClass();try {Method method = cls.getMethod("getValue");result.add((String) method.invoke(targetInner));} catch (Exception e) {}
result.add(outerProtected);result.addAll(callVarargs("x", "y", "z"));result.addAll(super.overrideMethod());
return result;}
public static List<String> callVarargs(String... args) {List<String> list = new ArrayList<>();for (String s : args) list.add(s);return list;}}
abstract class TargetClass {public static String sharedField = "shared";
class Inner {String getValue() {return "innerValue";}}}