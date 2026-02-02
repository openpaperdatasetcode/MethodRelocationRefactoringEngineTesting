package test;
protected class SourceClass {static class StaticNestedSource {}
class MemberInnerSource {}
private TargetClass targetField = new TargetClass();
/**
Method Javadoc: Instance method to move, depends on TargetClass parameter*/Object methodToMove(TargetClass targetParam) {do {switch (targetParam.getCode()) {case 2:// Call overloaded callMethod in switchObject result = callMethod(targetParam, () -> targetParam.getStaticNested().process(2));if (result != null) return result;break;default:SubTarget subTarget = new SubTarget(targetParam::getStaticNested);TargetClass staticResult = subTarget.abstractMethod(targetParam);variableCall(staticResult);}} while (targetParam.getCount() < 5);
super.getClass();return this;}
// Overloaded call_method: protected modifier, lambda target featureprotected Object callMethod(TargetClass target, Runnable lambda) {lambda.run();return target;}
protected Object callMethod(TargetClass target, int num) {return target.getCount() + num;}
private void variableCall(TargetClass target) {TargetClass.StaticNestedTarget nested = target.getStaticNested();nested.process(target.getCount());}}
protected class TargetClass {private int code;private int count;private static final StaticNestedTarget STATIC_NESTED = new StaticNestedTarget();
public static class StaticNestedTarget {public void process(int num) {}}
public TargetClass() {}
// Constructor with parameter list containing abstract-related referencepublic TargetClass(StaticNestedTarget nested) {this.code = 2;}
public StaticNestedTarget getStaticNested() {return STATIC_NESTED;}
public int getCode() {return code;}
public int getCount() {return count;}
// Abstract method (implemented in sub_class)public abstract TargetClass abstractMethod(TargetClass target);}
// Sub_class for abstract method featureclass SubTarget extends TargetClass {private final Supplier<StaticNestedTarget> nestedSupplier;
public SubTarget(Supplier<StaticNestedTarget> nestedSupplier) {this.nestedSupplier = nestedSupplier;}
@Overridepublic final TargetClass abstractMethod(TargetClass target) {nestedSupplier.get().process(2);return target;}}
interface Supplier<T> {T get();}