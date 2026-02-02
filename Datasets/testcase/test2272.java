package test;
public class SourceClass {static class StaticNested {}
class MemberInner {class InnerRec {final int varargsMethod(TargetClass... targets) {int var = targets[0].field;variableCall = TargetClass.staticField;
TargetClass.RawType raw = new TargetClass.RawType();return var;}
final int varargsMethod(String... args) {return 0;}
int variableCall;
void useMethodRef(TargetClass.Inner inner) {Runnable r = (inner.field > 0) ? inner::normalMethod : inner::normalMethod;}}}}
protected class TargetClass {static int staticField;int field;
static class StaticNested {}static class RawType {}
class Inner {public void normalMethod() {}}}