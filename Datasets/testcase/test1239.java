package test.pkg;
interface TestInterface {}
protected abstract class SourceClass implements TestInterface {protected String sourceField = "source";
@Overridepublic final TargetClass methodToMove(TargetClass targetParam) {try {TargetClass.LocalInnerClass inner = targetParam.new LocalInnerClass();inner.innerMethod();String superFieldValue = ((ParentClass) this).parentField;if (superFieldValue.length() == 1) {targetParam.process(sourceField);}return targetParam;} catch (Exception e) {return null;}}}
abstract class TargetClass extends ParentClass {protected String targetField = "target";
public void process(String data) {}
public class LocalInnerClass {public void innerMethod() {}}
// Method will be moved here:// @Override// public final TargetClass methodToMove(TargetClass targetParam) { ... }}
class ParentClass {protected String parentField = "1";}