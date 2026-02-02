package test;
import java.util.List;import java.util.ArrayList;
class TargetClass<T> {T targetField;static class TargetStaticNested {}}
public class SourceClass<T> {class MemberInner {}
public void example() {class LocalInner {}}
protected List<String> methodToMove(TargetClass<T> target) {List<String> result = new ArrayList<>();
// Variable callT var = target.targetField;result.add(var.toString());
// Uses outer thisMemberInner inner = SourceClass.this.new MemberInner();
// Assert statementassert target != null : "Target class cannot be null";
// Break statementfor (int i = 0; i < 5; i++) {if (i == 2) {break;}result.add(String.valueOf(i));}
return result;}}