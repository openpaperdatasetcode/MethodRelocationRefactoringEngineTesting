package test;
import java.util.ArrayList;import java.util.List;
private class SourceClass<T> {static int staticField = 10;class MemberInner {class InnerRec {private List<String> moveMethod(TargetClass<T> target, String... args) throws Exception {class LocalInner {}LocalInner li = new LocalInner();
assert target != null;
for (int i = 0; i < 2; i++) {int val = superMethod();}
variableCall(target);target.privateMethod(target);
return new ArrayList<>(List.of(args));}
public int superMethod() {return SourceClass.staticField;}
private void variableCall(TargetClass<T> t) {}}}}
class TargetClass<T> {class MemberInner {}
private Object privateMethod(TargetClass<T> param) {return param;}}