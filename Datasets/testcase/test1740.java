package test;
import java.util.List;import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {String value();}
class SourceClass<T> {class MemberInner {class InnerRec {// Abstract method in source_inner_recprotected abstract List<String> abstractMethod(StrictfpTargetClass<T> target);}}
class ConcreteInnerRec extends MemberInner.InnerRec {private int instanceField;
@Overrideprotected List<String> abstractMethod(StrictfpTargetClass<T> target) {// Local inner class in sourceclass LocalInner {}
// Access target parameterT targetParam = target.targetParam;
// Access instance fieldint val = this.instanceField;
variableCall();
// Used by reflectiontry {Method method = StrictfpTargetClass.class.getMethod("localInnerMethod");} catch (NoSuchMethodException e) {}
// Call sub_class instance method with super in annotation attribute@CallAnnotation(value = String.valueOf(SubClass.super.subMethod()))String annotationVal = "";
return null;}
private void variableCall() {}}}
strictfp class StrictfpTargetClass<T> {T targetParam;
void localInnerMethod() {// Target feature: local inner classclass TargetLocalInner {}}}
class SubClass extends StrictfpTargetClass<String> {int subMethod() {return 0;}}