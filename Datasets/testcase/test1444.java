package test.source;
import java.util.ArrayList;import java.util.List;import test.target.TargetClass;
// Source class: public, different package from target, has type parameter/implements/local inner/member inner classpublic class SourceClass<T> implements TestInterface {// Source contains target's field (per_condition)private TargetClass targetField = new TargetClass();private String sourceVar = "source_variable";
// Member inner classpublic class SourceMemberInner {// Member inner class (source_inner_rec: method's original position)public class SourceInnerRecClass {// Target method: instance, List<String>, final, source_inner_rec positionpublic final List<String> moveTargetMethod() {List<String> result = new ArrayList<>();String var = sourceVar; // variable call
// If statementif (targetField != null) {// Expression statementresult.add(var);result.add(targetField.targetStaticNestedClass.staticField); // raw_type (no generic spec) + variable call
// return this;if (result.size() > 1) {return this; // Return current inner rec class instance (compatible with List<String> via implicit conversion)}}
// No new checked exceptionreturn result;}}}
// Local inner class (source feature)public void sourceMethod() {class SourceLocalInner {public void localMethod() {// Call method position: functional interfacesTestFunctionalInterface func = () -> new SourceMemberInner().new SourceInnerRecClass().moveTargetMethod().size();}}new SourceLocalInner().localMethod();}}
// Interface for source class implementationinterface TestInterface {}
// Functional interface for call_method posinterface TestFunctionalInterface {int getSize();}
package test.target;
import java.util.List;
// Target class: private, has static nested class (target_feature)private class TargetClass {// Target static nested class (target_feature)public static class TargetStaticNestedClass {public static String staticField = "target_static_field";}
// Call method: inner_class, protected, normal, ClassName.methodName(arguments), pos: functional interfaces, return intprotected static class CallerInnerClass {public int callMethod(SourceClass.SourceMemberInner.SourceInnerRecClass innerRec) {// target_feature: ClassName.methodName(arguments)List<String> result = innerRec.moveTargetMethod();return result.size();}}}
// Fix import for cross-package inner class reference (required for compilation)package test.source;
import test.target.TargetClass;import test.target.TargetClass.CallerInnerClass;
// Supplemental functional interface implementation for call_methodclass FunctionalInterfaceImpl implements TestFunctionalInterface {@Overridepublic int getSize() {SourceClass<String> source = new SourceClass<>();SourceClass<String>.SourceMemberInner.SourceInnerRecClass innerRec = source.new SourceMemberInner().new SourceInnerRecClass();// Call inner class call method via ClassName.methodName(arguments)return CallerInnerClass.callMethod(innerRec);}}