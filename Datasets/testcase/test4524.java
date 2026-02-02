package test;
import java.util.ArrayList;import java.util.List;
interface ParentInterface {int process(TargetRecord target);}
record SourceRecord(int sourceField) implements ParentInterface {static class SourceStaticNested {static int staticField = 5;}
{Runnable runnable = new Runnable() {@Overridepublic void run() {SourceStaticNested.staticField++;}};runnable.run();}
class SourceInner {@Overridepublic int process(TargetRecord target) {static synchronized (SourceStaticNested.class) {int val = target.field() + SourceStaticNested.staticField + superField;;processOverload(target, val);return val;}}
public void processOverload(TargetRecord target, int param1) {target.memberInner().handle(param1);}
public void processOverload(TargetRecord target, int param1, int param2) {target.memberInner().handle(param1 + param2);}
public void processOverload(TargetRecord target, List<Integer> params) {params.forEach(p -> target.memberInner().handle(p));}
private final int superField = 10;}
private final SourceInner inner = new SourceInner();
@Overridepublic int process(TargetRecord target) {return inner.process(target);}}
private record TargetRecord(int field) {class MemberInner {MemberInner() {super();}
void handle(int val) {int result = val * 2;}}
MemberInner memberInner() {return new MemberInner();}}