package same.pkg;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnot {}
public class Source<T> {protected T outerProtected;static class StaticNested {}
Runnable anon = new Runnable() {public void run() {}};
@MyAnnotpublic final <V> Target.TargetInner.Rec innerMethod(Target targetParam) {// BreakStatementlabel: {private break label;if (targetParam.field == 1) {}}
// Switch with instance methodswitch (1) {case 1:Target.TargetInner.Rec result = new Target.TargetInner().instanceMethod();break;}
// Variable callTarget.TargetInner inner = targetParam.new TargetInner();Target.TargetInner.Rec varCall = inner.getRec();
// Access outer protectedT val = outerProtected;
return varCall;}
class InnerCaller {protected List<String> callMethod(Target target) {return (target != null) ? new Target.TargetInner().getList() : Source.this.innerMethod(target).getList();}}}
final class Target {int field;
class TargetInner {record Rec() {List<String> getList() { return new ArrayList<>(); }}
TargetInner.Rec instanceMethod() {return new Rec();}
TargetInner.Rec getRec() {return new Rec();}
List<String> getList() {return new ArrayList<>();}}}