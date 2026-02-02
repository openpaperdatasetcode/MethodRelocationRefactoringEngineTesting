package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
class OtherClass {protected List<String> createList() {return new ArrayList<>();}}
abstract class SourceClass {class MemberInner {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
@MyAnnotationprotected List<String> methodToMove(TargetClass targetParam) {SuperClass superObj = new SuperClass();List<String> list = new OtherClass().createList();
if (targetParam != null) {targetParam.variableCall();list.add("item");}
list.forEach(item -> {int count = TargetClass.StaticNested.abstractMethod();});
return list;}}
class SuperClass {public SuperClass() {}}
class TargetClass {static class StaticNested {abstract int abstractMethod();}
class Inner {class InnerRecursive {void nestedMethod() {}}}
void variableCall() {new Inner().new InnerRecursive().nestedMethod();}}