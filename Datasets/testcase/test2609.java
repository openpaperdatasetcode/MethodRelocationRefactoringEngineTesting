package test.same;
import java.util.Objects;
protected class SourceClass {{staticMethod(3);}
@DeprecatedObject varargsMethod(TargetClass... targets) {TargetClass target = new TargetClass();Object var = target.inner.field;
switch (TargetClass.staticField) {case 1:var = target.inner;break;default:break;}
try {for (TargetClass t : targets) {var = t.inner.field;}} catch (Exception e) {}
return var;}
private static final Object staticMethod(int num) {return super.toString();}
Runnable anon1 = new Runnable() {public void run() {}};
Runnable anon2 = new Runnable() {public void run() {}};}
/**
Javadoc for TargetClass*/class TargetClass extends ParentClass {static int staticField = 1;MemberInner inner = new MemberInner();
class MemberInner {Object field;}}
class ParentClass {}