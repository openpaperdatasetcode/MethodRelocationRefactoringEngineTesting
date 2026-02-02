import java.util.ArrayList;import java.util.List;import java.util.Objects;
// TargetClass: normal, package-private, with anonymous inner class (meets target_class specs)class TargetClass {// Target inner class for recursion (target_inner_rec)public class TargetInner {private String innerData;
public TargetInner(String data) {this.innerData = data;}
// Method for variable callpublic String getInnerData() {return innerData;}
// Method for variable callpublic void setInnerData(String innerData) {this.innerData = innerData;}}
// Static field for "depends_on_static_field" featurepublic static final String TARGET_STATIC_FIELD = "TARGET_STATIC_DATA";
// Anonymous inner class (target_feature)private final DataHandler dataHandler = new DataHandler() {@Overridepublic int processData(String data) {return data.length();}};
// Functional interface for anonymous inner class@FunctionalInterfaceprivate interface DataHandler {int processData(String data);}
// Instance method 1 for "2 target instance method calls" featurepublic int getStaticFieldLength() {return TARGET_STATIC_FIELD.length();}
// Instance method 2 for "2 target instance method calls" featurepublic int handleInnerData(TargetInner inner) {return dataHandler.processData(inner.getInnerData());}}
// SourceClass: abstract normal, same package as target, with 2 anonymous inner classes (meets source_class specs)abstract class SourceClass {// 1st anonymous inner class (source_feature: Runnable for init)private final Runnable initRunner = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner("init_data");// Call recursive method (contains target parameter: per_condition)List<String> result = recursiveProcess(target, inner, 2);System.out.println("Init recursive result: " + result);}};
// 2nd anonymous inner class (source_feature: Function for data transform)private final java.util.function.Function<TargetClass.TargetInner, String> transformFunc = new java.util.function.Function<>() {@Overridepublic String apply(TargetClass.TargetInner inner) {// uses_outer_this: access outer SourceClass instance's helper methodreturn SourceClass.this.transformData(inner.getInnerData());}};
// Helper method for "uses_outer_this" featureprotected String transformData(String data) {return data + "_transformed";}
/**
Recursive method: meets method specs (recursion, List<String> return, final, source position)
@param target TargetClass instance (per_condition: contains target parameter)
@param inner TargetInner instance for recursion
@param depth Recursion depth
@return List<String> Recursive processing result*/public final List<String> recursiveProcess(TargetClass target, TargetClass.TargetInner inner, int depth) {List<String> result = new ArrayList<>();// NullPointerException: check null for target parameterObjects.requireNonNull(target, "TargetClass instance cannot be null");Objects.requireNonNull(inner, "TargetInner instance cannot be null");
// Base case: recursion terminationif (depth <= 0) {result.add(inner.getInnerData());return result;}
// super constructor invocation: implicit call to Object super constructor (via new)TargetClass.TargetInner newInner = target.new TargetInner(inner.getInnerData() + "depth" + depth);
// Property assignment (pos for 2 target instance method calls)// 1st target instance method call: ClassName.methodName(arguments)int staticLen = target.getStaticFieldLength();// 2nd target instance method call: ClassName.methodName(arguments)int innerDataLen = target.handleInnerData(newInner);// Property assignment (use method call results)newInner.setInnerData(newInner.getInnerData() + "_len[" + staticLen + "," + innerDataLen + "]");
// Variable call: invoke target inner class methodString transformed = transformFunc.apply(newInner);result.add(transformed);
// depends_on_static_field: use target's static fieldresult.add(TargetClass.TARGET_STATIC_FIELD + "depth" + depth);
// Recursion callresult.addAll(recursiveProcess(target, newInner, depth - 1));
return result; // no_new_exception}
// Method to trigger init (via 1st anonymous inner class)public void init() {initRunner.run();}
// Abstract method to enforce subclass implementationpublic abstract void doBusinessLogic();}
// Concrete subclass of SourceClassclass ConcreteSource extends SourceClass {@Overridepublic void doBusinessLogic() {TargetClass target = new TargetClass();TargetClass.TargetInner inner = target.new TargetInner("business_data");List<String> result = recursiveProcess(target, inner, 3);System.out.println("Business recursive result: " + result);}}
// Test entrypublic class SourceTest {public static void main(String[] args) {SourceClass source = new ConcreteSource();source.init();source.doBusinessLogic(); // Verify no exception (no_new_exception)}}