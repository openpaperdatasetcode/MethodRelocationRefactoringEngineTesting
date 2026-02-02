package test.refactoring;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.ElementType;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.annotation.Target;
// Annotation for has_annotation feature@Retention(RetentionPolicy.RUNTIME)@Target(ElementType.METHOD)@interface MyMethodAnnotation {}
// Functional interface for lambda expression@FunctionalInterfaceinterface AbstractInnerFunctional {void execute();}
// Source class: normal, default modifier, same package, has type parameter/implements/static nested/local inner classclass SourceClass<T> implements TestInterface {// Static field for depends_on_static_field featurepublic static String staticField = "source_static_field";
// Static nested class (source feature)static class SourceStaticNested {}
// Member inner class (parent of source_inner_rec)class SourceInnerClass {// Member inner class (source_inner_rec: method's original position)class SourceInnerRecClass {// Target method: varargs, base type (int), default access, source_inner_rec position// per_condition: contains target parameter (TargetClass)@MyMethodAnnotation // has_annotationint moveTargetMethod(TargetClass targetParam, String... varargs) {int result = 0;String var = staticField; // variable call + depends_on_static_field
// Try statement + no_new_exceptiontry {// Abstract feature: public abstract inner class method + lambda (parameters) -> methodBodyAbstractInnerClass inner = new AbstractInnerClass() {@Overridepublic void abstractInnerMethod() {// pos: for statementfor (int i = 0; i < varargs.length; i++) {result += varargs[i].length();}}};// Lambda expression: (parameters) -> methodBodyAbstractInnerFunctional func = () -> inner.abstractInnerMethod();
// Do statementdo {result++;} while (result < 3);
// Enhanced for statementfor (String arg : varargs) {System.out.println(arg + targetParam.targetField);}} catch (RuntimeException e) {// No new checked exception}
return result;}
// Abstract inner class (method feature: abstract, public, inner_class)public abstract class AbstractInnerClass {// Abstract method (method_feature: 1, abstract)public abstract void abstractInnerMethod();}}}
// Local inner class (source feature)public void sourceLocalMethod() {class SourceLocalInner {public void localMethod() {new SourceInnerClass().new SourceInnerRecClass().moveTargetMethod(new TargetClass(), "a", "b");}}new SourceLocalInner().localMethod();}
// Call method: source class, default modifier, normal, super.methodName(), pos: switch, return List<String>List<String> callMethod(int choice) {List<String> result = new ArrayList<>();SourceInnerClass.SourceInnerRecClass innerRec = new SourceInnerClass().new SourceInnerRecClass();
switch (choice) { // call_method pos: switchcase 1:innerRec.moveTargetMethod(new TargetClass(), "x", "y");result.add("Case 1");break;default:// target_feature: super.methodName()super.toString();innerRec.moveTargetMethod(new TargetClass(), "z");result.add("Default");}return result;}}
// Interface for source class implementationinterface TestInterface {}
// Target class: normal, default modifier, has member inner class (target_feature)class TargetClass {// Target field referenced by sourceString targetField = "target_field";
// Member inner class (target_feature)class TargetInnerClass {}}