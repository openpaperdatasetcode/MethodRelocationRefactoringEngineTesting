package test;
protected class SourceClass {public class SourceInner {public class SourceInnerRec {public final int overloadedMethod(TargetClass.TargetInner param) {assert param != null;new TargetClass.TargetInner(overrideMethod(1));param.callMethod();return 0;}
@Overridepublic int overrideMethod(int num) {return new SourceInner().new SourceInnerRec().overrideMethod(num);}}}
public static class SourceStaticNested {}}
class TargetClass extends ParentClass {public class TargetInner {public TargetInner(int value) {}
public void callMethod() {}}}
class ParentClass {}