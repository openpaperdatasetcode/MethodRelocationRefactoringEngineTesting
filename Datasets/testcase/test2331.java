package same.pkg;
import java.util.List;import java.util.ArrayList;
private enum Source {INSTANCE;
class MemberInner {}
void methodWithLocal() {class LocalInner {}}
List<String> instanceMethod(List<String> targetListParam) {// Method types parameter is:List (using targetListParam)Target.StaticNested.Rec rec = Target.StaticNested.Rec.fromList(targetListParam);
// Variable callList<String> varCall = rec.getValues();
// Access instance methodint size = targetListParam.size();String first = rec.getValue(0);
// Depends on inner classTarget.StaticNested nested = new Target.StaticNested();List<String> innerList = nested.process(rec);
return innerList;}}
public enum Target {INSTANCE;
static class StaticNested {record Rec(List<String> values) {static Rec fromList(List<String> list) {return new Rec(new ArrayList<>(list));}
List<String> getValues() {return values;}
String getValue(int index) {return values.get(index);}}
List<String> process(Rec rec) {return rec.getValues();}}
protected void handleException() {try {throw new Exception();} catch (Exception e) {List<String> result = StaticNested.Rec::getValues;}}}