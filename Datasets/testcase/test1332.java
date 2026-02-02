package test.refactor.movemethod;
import java.util.function.Consumer;
// Interface for target_class implements featureinterface Printable {void printValue();}
// Source enum class (default modifier, same package, anonymous inner + static nested class)enum SourceEnum {INSTANCE;
// Feature: static nested classpublic static class SourceStaticNested {public static <T extends CharSequence> void staticProcess(T data) {System.out.println("Static processed: " + data);}}
// Method to be refactored: instance, protected, void returnprotected void processTarget(TargetEnum targetParam) { // per_condition// Per_condition: contains target parameterif (targetParam == null) {throw new IllegalArgumentException("Target cannot be null");}
// With_bounds (generic with bounds)Consumer<? extends CharSequence> boundedConsumer = SourceStaticNested::staticProcess;
// Expression statementtargetParam.printValue();boundedConsumer.accept(targetParam.getValue());
// Lambda expression: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(this.name());
// Try statement + requires_try_catchtry {// Variable call: target's local inner class (target_feature)targetParam.useLocalInner();
// Variable call: target's implemented methodtargetParam.printValue();
// Execute lambdalambda.run();} catch (IllegalStateException e) {// Requires_try_catch handlingSystem.err.println("Handled exception: " + e.getMessage());}
// Feature: anonymous inner classRunnable anon = new Runnable() {@Overridepublic void run() {System.out.println("Source anonymous: " + targetParam.getValue());}};anon.run();}}
// Target enum class (public, target_feature: implements + local inner class)public enum TargetEnum implements Printable {TARGET_INSTANCE("targetValue");
private final String value;
TargetEnum(String value) {this.value = value;}
public String getValue() {return value;}
// Target_feature: local inner classpublic void useLocalInner() {class TargetLocalInner {public void printTargetValue() {System.out.println("Target local inner: " + value);}}new TargetLocalInner().printTargetValue();}
// Implemented method from Printable interface@Overridepublic void printValue() {System.out.println("Target implemented: " + value);}}
// Test classpublic class MoveMethodTest5282 {public static void main(String[] args) {SourceEnum source = SourceEnum.INSTANCE;TargetEnum target = TargetEnum.TARGET_INSTANCE;source.processTarget(target);}}