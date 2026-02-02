package test;
import java.util.List;
/**
TargetClass with javadoc and local inner class*/class TargetClass {public static int staticField = 5;private int instanceField;
public TargetClass(int val) {this.instanceField = val;}
public int getInstanceField() {return instanceField;}
public void createLocalInner() {class TargetLocalInner {int process() {return instanceField * 2;}}}}
public class SourceClass {private TargetClass targetField = new TargetClass(10);raw_type rawObj = new raw_type();
class SourceMemberInner {protected int instanceMethod(TargetClass targetParam) {try {SourceAbstract innerAbstract = new SourceConcrete();Object result = innerAbstract.abstractMethod1();result = innerAbstract.abstractMethod2();
if (TargetClass.staticField > 0) {targetParam.getInstanceField();} else if (targetField.getInstanceField() < 20) {rawObj.setValue("test");}
TargetLocalUsage helper = new TargetLocalUsage();return helper.useTargetInner(targetParam);} catch (Exception e) {throw new RuntimeException(e);}}}
private abstract class SourceAbstract {protected abstract Object abstractMethod1();protected abstract Object abstractMethod2();
@Overridepublic String toString() {return super.toString();}}
private class SourceConcrete extends SourceAbstract {@Overrideprotected Object abstractMethod1() {return "abstract1";}
@Overrideprotected Object abstractMethod2() {return 123;}}
public void createLocalInner() {class SourceLocalInner {void printTarget(TargetClass target) {System.out.println(target.getInstanceField());}}}
class TargetLocalUsage {int useTargetInner(TargetClass target) {target.createLocalInner();return target.getInstanceField() + TargetClass.staticField;}}}
class raw_type {private String value;public void setValue(String val) {this.value = val;}}