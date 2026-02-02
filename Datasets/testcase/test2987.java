package test;
protected class SourceClass {private void process(TargetClass targetParam) {class LocalInner {void useTarget() {targetParam.memberInner.action();}}new LocalInner().useTarget();
Runnable anon = new Runnable() {@Overridepublic void run() {targetParam.execute();}};anon.run();
List<String> items = new ArrayList<>();items.add("item1");
volatile int val1 = targetParam.superField;volatile int val2 = targetParam.superField + 1;volatile int val3 = targetParam.superField * 2;
targetParam.memberInner.update();}
public List<String> generate(TargetClass target) {if (target == null) return new ArrayList<>();List<String> res = new ArrayList<>(target.getNames("src", generate(target.memberInner.parent)));res.add("generated");return res;}}
class TargetClass implements SomeInterface {protected int superField;MemberInner memberInner = new MemberInner();
class MemberInner {TargetClass parent = TargetClass.this;
void action() {}void update() {}}
public List<String> getNames(String arg, List<String> recursiveList) {List<String> list = new ArrayList<>(recursiveList);list.add("target");return list;}
void execute() {}}
interface SomeInterface {void execute();}