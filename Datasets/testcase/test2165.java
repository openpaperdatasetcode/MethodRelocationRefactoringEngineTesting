package test;
import java.util.List;import java.util.ArrayList;
non-sealed enum SourceEnum {INSTANCE;
class MemberInner {}
public List<String> methodToMove(TargetEnum target) {class LocalInner {}LocalInner local = new LocalInner();
TargetEnum.MemberInner.InnerRecursive innerRec = target.new MemberInner().new InnerRecursive();
// Variable callinnerRec.variableCall();
// Depends on static fieldList<String> result = new ArrayList<>(TargetEnum.StaticNested.staticList);
// Return thisreturn this.getList();}
private List<String> getList() {return new ArrayList<>();}}
protected enum TargetEnum {VALUE;
static class StaticNested {static List<String> staticList = new ArrayList<>();}
class MemberInner {class InnerRecursive {void variableCall() {}}}}