package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
interface TargetInterface {}
private class TargetClass implements TargetInterface {public String thisField1 = "field1";public String thisField2 = "field2";
void targetMethod() {}}
public class SourceClass {static class StaticNested {}
public class MemberInner {@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnot {}
@MethodAnnotprivate Object instanceMethod (TargetClass target) {
 this.fieldprotected for (int i = 0; i < 2; i++) {System.out.println (target.thisField1);System.out.println (target.thisField2);}
variableCall (target);new StaticNested ();
return target.thisField1;}
private void variableCall(TargetClass target) {target.targetMethod();}}}