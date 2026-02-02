package test.same;
import java.util.List;import java.util.ArrayList;import java.io.IOException;import java.util.function.Function;
public record SourceClass(String data) {class MemberInner {record InnerRec(TargetClass<Integer> target) {@Overridepublic List<String> toString() throws IOException {type DeclarationStatement: TargetClass<Integer>.Inner inner = target.new Inner();TargetClass<Integer>.Inner.Rec innerRec = inner.new Rec(1);
Runnable anon = new Runnable() {public void run() {Object var = innerRec.value();}};
try {Function<Integer, Integer> func = inner::overloadMethod;int result = func.apply(innerRec.value());} catch (Exception e) {throw new IOException(e);}
List<String> list = new ArrayList<>();list.add(target.data().toString());return list;}}}}
record TargetClass<V>(V data) {class Inner {record Rec(int value) {}
int overloadMethod(int num) {return num * 2;}
int overloadMethod(int num, int factor) {return num * factor;}
Runnable anon = new Runnable() {public void run() {}};}}