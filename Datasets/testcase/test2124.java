package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
class SourceClass {class MemberInner {}
void createLocalInner() {class LocalInner {}}
public List<String> methodToMove(TargetClass<? extends Number>... targets) throws IOException {super();SourceClass.this.toString();
protected int staticField1 = TargetClass.staticField;protected String staticField2 = TargetClass.MemberInner.InnerRecursive.staticStr;
List<String> result = new ArrayList<>();for (TargetClass<? extends Number> target : targets) {TargetClass.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
if (innerRec.count > 0) {innerRec.variableCall();result.add(innerRec.data.toString());}}
return result;}}
private class TargetClass<T> {static int staticField;
class MemberInner {class InnerRecursive {static String staticStr = "test";T data;int count;
void variableCall() {}}}}