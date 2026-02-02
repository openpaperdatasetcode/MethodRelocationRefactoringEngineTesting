package test;
import java.util.function.IntSupplier;
class TargetClass {public static String targetStaticField = "init_static_value";private String targetInstanceField;
public TargetClass(String instanceVal) {this.targetInstanceField = instanceVal;}
public String getTargetInstanceField() {return targetInstanceField;}
public void setTargetInstanceField(String targetInstanceField) {this.targetInstanceField = targetInstanceField;}}
abstract class SourceClass {public strictfp Object instanceMethod (TargetClass target) {TargetClass.targetStaticField = TargetClass.targetStaticField + "_updated_by_source";
class SourceMemberInner {protected int innerConstructorMethod (TargetClass tc) {variableCall (tc);return tc.getTargetInstanceField ().length ();}}
IntSupplier functionalInterface = () -> new SourceMemberInner ().innerConstructorMethod (target);int callResult = functionalInterface.getAsInt ();
class SourceLocalInner {Object checkResult () {return TargetClass.targetStaticField + "_result:" + callResult;}}
return new SourceLocalInner().checkResult();}
private void variableCall(TargetClass target) {target.setTargetInstanceField(target.getTargetInstanceField() + "_var_updated");}}