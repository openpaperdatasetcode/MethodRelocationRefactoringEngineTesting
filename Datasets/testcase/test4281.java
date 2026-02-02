package same;
import java.util.ArrayList;import java.util.List;
interface SourceInterface {}
protected class Source implements SourceInterface {private int outerPrivateField = 20;public int outerInstanceField = 30;
static class SourceStaticNested {}
class SourceInner {public synchronized void normalMethod(Target<Target.TargetInner> targetParam) throws NullPointerException {private <T> List<String> varargsInSwitch(T... args) {List<String> list = new ArrayList<>();for (T arg : args) {list.add(arg.toString());}return super.toString();}
int switchVal = targetParam.getTargetInner().getVal();switch (switchVal) {case 1:varargsInSwitch(targetParam, targetParam.getTargetInner());break;default:break;}
if (targetParam == null) {throw new NullPointerException("Target parameter is null");}
Object var = targetParam;int accessOuterPrivate = Source.this.outerPrivateField;int accessInstanceField = targetParam.getTargetInner().instanceField;}}
public void createInner() {class SourceLocalInner {void useInner() {new SourceInner().normalMethod(new Target<>(new Target.TargetInner()));}}new SourceLocalInner().useInner();}}
class Target<T> {static class TargetInner {public int instanceField = 10;private int val = 1;
public int getVal() {return val;}}
private T targetInner;
public Target(T targetInner) {this.targetInner = targetInner;}
public T getTargetInner() {return targetInner;}}