package test;
import java.io.IOException;
public class SourceClass {public static class StaticNested {}public class MemberInner {}
protected int methodToMove(int... args) throws IOException {TargetClass.MemberInner inner = new TargetClass().new MemberInner();int result = inner.targetField;
try {Label: {if (inner.targetField == 0) break Label;result = inner.targetField * 2;}
assert result > 0 : "Invalid result";String s1 = "literal1";String s2 = "literal2";OtherClass.call().chain().method();} catch (NullPointerException e) {e.printStackTrace();}
return result;}}
public class TargetClass {public class MemberInner {int targetField;}}
class OtherClass {public static OtherClass call() { return new OtherClass(); }public OtherClass chain() { return this; }public void method() {}}