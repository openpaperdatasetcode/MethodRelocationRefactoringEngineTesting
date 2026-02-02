package test;
import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
@Target(ElementType.METHOD)@Retention(RetentionPolicy.RUNTIME)@interface MethodAnno {}
class ParentSource {protected ParentSource() {}}
protected class SourceClass extends ParentSource {private TargetClass targetField = new TargetClass();
static class StaticNested {public StaticNested(int val) {}}
public SourceClass() {super();new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in SourceClass");}}.run();}
@MethodAnnoprotected int instanceMethod() {new StaticNested(1);TargetClass.StaticNested targetNested = new TargetClass.StaticNested();
int var = targetField.targetIntField;if (var == targetNested.staticField) {var = 0;}return var;}
@MethodAnnoprotected int instanceMethod(boolean flag) {return flag ? instanceMethod() : 1;}}
public class TargetClass {int targetIntField = 5;
static class StaticNested {public int staticField = 1;}}