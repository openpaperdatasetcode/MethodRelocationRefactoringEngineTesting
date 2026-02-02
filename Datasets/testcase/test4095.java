package test;
import java.io.IOException;
public class SourceClass {protected int outerProtectedField = 10;
protected int outerInstanceMethod() {return 20;}
class MemberInnerClass {protected int instanceMethod(TargetClass target) throws IOException {try {TargetClass.StaticNested nested = new TargetClass.StaticNested();super.toString();
int varCall = target.targetField;varCall += outerProtectedField;varCall += outerInstanceMethod();
class LocalInnerClass {int processTarget(TargetClass t) {return t.targetMethod() + varCall;}}
return new LocalInnerClass().processTarget(target);} catch (NullPointerException e) {throw new IOException("Target is null", e);}}}}
non-sealed class TargetClass {int targetField = 5;
int targetMethod() {return 30;}
static class StaticNested {void nestedMethod() {}}}