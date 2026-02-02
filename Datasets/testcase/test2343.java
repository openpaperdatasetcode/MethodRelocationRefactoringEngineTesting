package test;
import java.lang.reflect.Method;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
record SourceClass(int sourceField) {static class SourceStaticNested {}
{new Object() {}; // Anonymous inner class}
class SourceInner {@MyAnnotationprivate void methodToMove(TargetClass targetParam) {// SwitchStatementswitch (1) {case 0:int val = this.hashCode();break;case 1:break;}
// do statementdo {targetParam.targetField();} while (false);
// type declaration statementclass LocalType {}LocalType lt = new LocalType();
// variable callint var = sourceField();
// access instance fieldint tf = targetParam.targetField();
// used by reflectiontry {Method m = SourceInner.class.getMethod("methodToMove", TargetClass.class);} catch (NoSuchMethodException e) {}}
@Overridepublic String toString() {SubClass sub = new SubClass();return sub.overrideMethod();}}
static class SubClass {public String overrideMethod() {return this.toString();}}}
private record TargetClass(int targetField) {void someMethod() {class TargetLocalInner {} // Local inner class}}