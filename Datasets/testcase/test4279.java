package same;
abstract class SuperAbstractClass {public abstract Object overrideMethod(Target targetParam);}
abstract class Source extends SuperAbstractClass {class SourceMemberInner {public void useTarget(Target.TargetMemberInner inner) {inner.doSomething();}}
Runnable sourceAnonInner = new Runnable() {@Overridepublic void run() {new SourceMemberInner().useTarget(new Target().new TargetMemberInner());}};
@Overrideprotected Object overrideMethod(Target targetParam) {SourceMemberInner inner = new SourceMemberInner();Target.TargetMemberInner targetInner = targetParam.new TargetMemberInner();
Object var = targetParam;inner.useTarget(targetInner);
return var;}}
abstract class Target {class TargetMemberInner {public void doSomething() {}}}