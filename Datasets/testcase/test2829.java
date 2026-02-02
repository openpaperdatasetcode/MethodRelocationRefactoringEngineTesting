package test;
sealed interface SourceInterface permits SourceClass {}
public class SourceClass implements Runnable, SourceInterface {static class StaticNestedSource {}
public class SourceInner {public class SourceInnerRec {@Overridepublic Object methodToMove(TargetClass targetParam, Object... args) throws IllegalStateException {{Object varargsResult = superTypeMethod(targetParam, 1, args);variableCall(targetParam, varargsResult);}
new Runnable() {@Overridepublic void run() {super.toString();targetParam.inner.innerRec.process();}}.run();
if (args.length == 0) throw new IllegalStateException("Varargs required");super.getClass();return this;}
protected Object superTypeMethod(ParentTarget superType, int num, Object... varargs) {return superType.process(num, varargs);}
private void variableCall(TargetClass target, Object value) {target.field = value;}}}
@Overridepublic void run() {}}
class ParentTarget {public Object process(int num, Object... varargs) {return num + varargs.length;}}
public class TargetClass extends ParentTarget {Object field;public TargetInner inner = new TargetInner();
public class TargetInner {public TargetInnerRec innerRec = new TargetInnerRec();
public class TargetInnerRec {public void process() {}}}}