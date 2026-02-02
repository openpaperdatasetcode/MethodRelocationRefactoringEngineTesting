package test;
import java.util.ArrayList;import java.util.List;import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface TrackMethod {}
class SourceClass {public static class Nested1 {}public static class Nested2 {}
@TrackMethodList<String> process(TargetClass target) {class LocalType {String data;}LocalType local = new LocalType();
// EmptyStatement with super.field referenceprivate ;target.superField = 10;
try {Class<?> cls = Class.forName("test.SourceClass");Method method = cls.getMethod("process", TargetClass.class);method.invoke(this, target);} catch (Exception e) {// used_by_reflection, no new exception}
SubTarget sub1 = new SubTarget();SubTarget sub2 = new SubTarget();SubTarget sub3 = new SubTarget();
// Ternary operator with abstract method callsString result = (target.isValid() ? sub1.action() : sub2.action());sub3.action();
List<String> list = new ArrayList<>();list.add(result);return list;}}
public class TargetClass extends ParentClass implements Runnable {@Overridepublic void run() {}
boolean isValid() {return true;}}
class ParentClass {protected int superField;}
abstract class SubTarget {protected abstract void action();}
class ConcreteSubTarget extends SubTarget {@Overrideprotected void action() {}}