package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface TestInterface {}
protected class SourceClass<T extends CharSequence & Comparable<T>> implements TestInterface { // with_bounds// Static nested classes (source_feature)public static class StaticNested1 {}public static class StaticNested2 {}
// Varargs methodpublic int methodToMove(TargetClass... targets) {int total = 0;int index = 0;
// While statementwhile (index < targets.length) {TargetClass target = targets[index];// Variable call + contains target parameter (per_condition)target.toString();TargetClass.Inner targetInner = target.new Inner();
// Inner class with EmptyStatement (ClassName.field: 2)class InnerProcessor {public void process() {// EmptyStatement with 2 ClassName.fieldsynchronized (TargetClass.STATIC_FIELD1) {; // EmptyStatementsynchronized (TargetClass.STATIC_FIELD2) {; // EmptyStatementtotal += targetInner.getFieldLength();}}}}new InnerProcessor().process();
// If statementif (targetInner.getField() != null) {// Expression with inner_class instance method call (super.methodName())targetInner.processField(() -> super.toString());}
index++;}
// Used by reflectiontry {Method method = TargetClass.Inner.class.getMethod("getFieldLength");method.invoke(targets[0].new Inner());} catch (Exception e) {// No new exception}
return total;}
// Source static method for call_method featureprivate static List<String> sourceStaticMethod(TargetClass target) {List<String> result = new ArrayList<>();// Instance code blocks with outerInstance.new InnerClass().methodName(){SourceClass<String> outerInstance = new SourceClass<>();result.add(target.new Inner().getField() + "_processed");}return result;}}
protected class TargetClass {// Static fields (ClassName.field: 2)public static final String STATIC_FIELD1 = "static1";public static final String STATIC_FIELD2 = "static2";
// Static nested class (target_feature)public static class TargetStaticNested {}
public class Inner {private String field = "targetInnerField"; // Source contains target's field (per_condition)
// Inner_class instance method (super.methodName())protected void processField(Runnable runnable) {runnable.run();field = field.toUpperCase();}
public int getFieldLength() {return field.length();}
public String getField() {return field;}}
// Call source static methodpublic List<String> callSourceStatic() {return SourceClass.sourceStaticMethod(this);}}