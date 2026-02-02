package test;
public interface TargetInterface {}
private class SourceClass {public static int staticField = 10;
public class SourceInner {public class SourceInnerRec extends TargetClass.TargetInnerRec {@Overrideprotected Object overridingMethod(int num) {TargetClass target = new TargetClass();int val = target.innerRec.targetField * SourceClass.staticField;return val + num;}}}
public static class SourceStaticNested {}}
public class TargetClass implements TargetInterface {public TargetInnerRec innerRec = new TargetInnerRec();
public class TargetInnerRec {int targetField = 5;
protected Object overridingMethod(int num) {class TargetLocalInner {}TargetLocalInner local = new TargetLocalInner();return num;}}}
private class OthersClass {private TargetClass.TargetInnerRec callMethod() {TargetClass.TargetInnerRec innerRec = new SourceClass.SourceInner().new SourceInnerRec();return (TargetClass.TargetInnerRec) innerRec.overridingMethod(3);}
public OthersClass() {this(callMethod());}
private OthersClass(TargetClass.TargetInnerRec innerRec) {}}