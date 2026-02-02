package test.same;
strictfp class SourceClass permits SubSourceClass {static class StaticNested {}
class MemberInner {record InnerRec(TargetClass target) {@Deprecatedprivate int instanceMethod() {TargetClass.Inner inner = target.new Inner();Object var = inner.field;int result = 0;
switch ((int) var) {case 1:result = 10;break;case 2:result = 20;break;default:result = 0;}
try {result += (int) var;} catch (ClassCastException e) {}
return result;}}}
Runnable anon = new Runnable() {public void run() {}};}
class SubSourceClass extends SourceClass {}
class TargetClass {class Inner {Object field;}}