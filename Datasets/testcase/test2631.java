package test.same;
import java.io.IOException;
public class SourceClass extends ParentClass {private int privateField = 10;
class MemberInner {record InnerRec() {protected int varargsMethod(TargetClass... targets) throws IOException {if (targets == null) {throw new NullPointerException();}
TargetClass.MemberInner inner = new TargetClass().new MemberInner();Object var = inner.targetField;
if (var == null) {SourceClass.this.privateField = 20;var = SourceClass.this.privateField;}
inner.targetField = var;return (int) var;}}}
Runnable anon = new Runnable() {public void run() {}};}
class ParentClass {}
class TargetClass {class MemberInner {Object targetField;}}