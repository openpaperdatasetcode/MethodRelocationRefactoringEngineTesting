package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
interface TargetInterface<T> {}
@Retention(RetentionPolicy.RUNTIME)@interface ConstructorAnnotation {Object value() default new TargetClass<>(1).this.instanceMethod();}
public class SourceClass<T> {class InnerClass {protected TargetClass<T> moveMethod(TargetClass<T> target) {labeledBlock: {target.instanceField = 10;variableCall(target);System.out.println(target.instanceField);break labeledBlock;}return target;}}
private void variableCall(TargetClass<T> target) {target.staticNested.doTask();}}
class TargetClass<T> implements TargetInterface<T> {public T instanceField;
public TargetClass(T value) {this.instanceField = value;}
public TargetClass() {this(null);}
public TargetClass(T value1, T value2) {this.instanceField = value1;}
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public Object instanceMethod() {return instanceField;}
{OthersClass.overloadMethod(this);OthersClass.overloadMethod(this, instanceField);}}
class OthersClass {@ConstructorAnnotationpublic static <T> int overloadMethod(TargetClass<T> target) {return 1;}
public static <T> int overloadMethod(TargetClass<T> target, T value) {return 2;}}