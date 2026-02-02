package source;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import target.Target;
public enum Source {INSTANCE;
class SourceMemberInner {class SourceInnerRecursive {public Target instanceMethod(Target targetParam) throws IOException {private class PrivateConstructorHelper {PrivateConstructorHelper() {SuperClass superObj = new SuperClass();if (superObj.superField == 1) {new Target.TargetMemberInner();}}}new PrivateConstructorHelper();
protected List<String> instanceInInit(Target.TargetMemberInner inner) {return new ArrayList<>(List.of(inner.toString()));}
Target.TargetMemberInner targetInner = targetParam.new TargetMemberInner();List<String> initResult = instanceInInit(targetInner);
class LocalType {Target storedTarget = targetParam;}new LocalType();
Object var = targetParam;return targetParam;}
protected void callInSwitch(Target.TargetMemberInner inner) {switch (inner.getVal()) {case 1:inner.recursiveMethod();break;default:break;}}}}
public void createLocalInner() {class SourceLocalInner {void useRecursive() {SourceMemberInner.InnerHelper helper = new SourceMemberInner.InnerHelper();helper.process(Target.INSTANCE::new TargetMemberInner);}}new SourceLocalInner().useRecursive();}
class SourceMemberInner {static class InnerHelper {protected void process(Target.TargetMemberInner inner) {inner.recursiveMethod();}}}}
class SuperClass {public int superField = 1;}
package target;
import java.util.function.Supplier;
public enum Target {INSTANCE;
class TargetMemberInner {private int val = 1;
public int getVal() {return val;}
public void recursiveMethod() {if (val > 0) {val--;recursiveMethod();}}}
public void useMethodRef(Supplier<TargetMemberInner> supplier) {TargetMemberInner inner = supplier.get();inner.recursiveMethod();}}