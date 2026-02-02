package test;
import java.util.ArrayList;import java.util.List;import static org.junit.Assert.*;
public class SourceClass {static class StaticNested {}
class MemberInner {protected void callInnerMethod(TargetClass target) {for (int i = 0; i < 1; i++) {target.new Inner().staticMethod(target);}}}
protected List<String> method(TargetClass targetParam) {try {synchronized (targetParam) {targetParam.new Inner().new InnerRec().field = new ArrayList<>();}
assert targetParam.superField == 1 : "Assert failed";
new TargetClass();new MemberInner().callInnerMethod(targetParam);
if (targetParam.new Inner().isEmpty()) {throw new IllegalArgumentException();}
return targetParam.new Inner().new InnerRec().field;} catch (NullPointerException e) {throw e;}}}
private class TargetClass extends ParentClass {int superField = 1;
class Inner {static void staticMethod(TargetClass target) {target.superField++;}
boolean isEmpty() {return new InnerRec().field.isEmpty();}
class InnerRec {List<String> field;}}
void someMethod() {class LocalInner {}}}
class ParentClass {int superField;}