package test;
class ParentSource {}
class ParentTarget {}
protected class SourceClass extends ParentSource {static class StaticNested {private int nestedField = 1;}
class MemberInner {void innerMethod() {}}
public final void overloadedMethod(TargetClass targetParam) {for (int i = 0; i < 1; i++) {new PrivateConstructor(i);}
StaticNested staticNested = new StaticNested();int var = staticNested.nestedField;System.out.println(var);
targetParam.anonymousAction();}
public final void overloadedMethod(TargetClass targetParam, int value) {ParentTarget superTypeRef = new ParentTarget();new PublicConstructor(superTypeRef, 1);
MemberInner inner = new MemberInner();inner.innerMethod();
targetParam.overriddenMethod();}
private class PrivateConstructor {PrivateConstructor(int num) {if (num == 1) {new MemberInner().innerMethod();}}}
public class PublicConstructor {PublicConstructor(ParentTarget superTypeRef, int superFieldVal) {if (superTypeRef instanceof ParentTarget) {System.out.println("Super type reference method call");}}}}
final class TargetClass extends ParentTarget {public void anonymousAction() {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}};runnable.run();}
public void overriddenMethod() {}}