package test;
abstract class SourceClass {protected TargetClass targetField = new TargetClass();
public Object recursiveProcess(int depth) {super();if (depth <= 0) {return targetField.getValue();}
private class LocalType1 {void updateTarget() {targetField.setValue("type1-" + depth);}}new LocalType1().updateTarget();
private class LocalType2 {void logTarget() {System.out.println(TargetClass.STATIC_FIELD + "-" + depth);}}new LocalType2().logTarget();
InnerClass inner1 = new InnerClass();inner1.handleTarget(targetField);
InnerClass inner2 = new InnerClass();inner2.handleTarget(targetField, "overload");
return recursiveProcess(depth - 1);}
static class StaticNested {void useRecursive() {class LocalInner {void execute() {SourceClass source = new SourceClass() {};Object result = source.recursiveProcess(3);}}new LocalInner().execute();}}
class InnerClass {public void handleTarget(TargetClass target) {target.setValue("handled");}
public void handleTarget(TargetClass target, String suffix) {target.setValue("handled-" + suffix);}}}
class TargetClass {public static final String STATIC_FIELD = "target-static";private String value;
public String getValue() {class LocalHelper {String fetch() {return value;}}return new LocalHelper().fetch();}
public void setValue(String value) {this.value = value;}
private TargetClass createAndProcess() {return new TargetClass().setValueAndReturn("new-instance");}
private TargetClass setValueAndReturn(String val) {this.value = val;return this;}
public TargetClass getProcessedInstance(boolean useNew) {if (useNew) {return new TargetClass().createAndProcess();} else {return createAndProcess();}}}