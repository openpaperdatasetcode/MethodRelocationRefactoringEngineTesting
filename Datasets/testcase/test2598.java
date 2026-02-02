package test.same;
strictfp class SourceClass {static class StaticNested {}
class InnerClass {public int varargsMethod() {TargetClass target = new TargetClass();TargetClass.MemberInner inner = target.new MemberInner();super.toString();int result = 0;transient int count = 0;
while (count < 1) {inner.thisField = 1;result += inner.calculate();count++;}
int i = 0;while (i < 3) {assert i < 5 : "Assertion failed";i++;}
return SourceClass.this.hashCode() + result;}}
Runnable anon = new Runnable() {public void run() {}};}
public class TargetClass extends ParentClass {class MemberInner {int thisField;
int calculate() {return thisField;}}}
class ParentClass {}