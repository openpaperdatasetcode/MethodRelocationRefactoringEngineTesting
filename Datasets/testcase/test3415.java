package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
protected class SourceClass {// Member inner class (source feature)public class MemberInner {}
// Static nested class (source feature)public static class StaticNested {}
// Instance code blocks (pos for call_method){OtherClass other = new OtherClass();TargetClass target = other.getFirst().getSecond().getThird();}
protected List<String> instanceMethod(TargetClass targetParam) {List<String> result = new ArrayList<>();
try {// Constructor invocation: target's parent class relatedTargetParentClass parent = new TargetParentClass();TargetClass.TargetInner inner = new TargetClass.TargetInner();
// EnhancedForStatement (private, target_feature: super.field x2, pos: inner class)private class EnhancedForInner {public void process() {for (String item : List.of("a", "b")) {result.add(parent.superField1);result.add(parent.superField2);}}}new EnhancedForInner().process();
// Instance method feature (2, parent_class, instance, (parameters) -> methodBody, pos: for)Consumer<TargetParentClass> consumer1 = p -> p.parentMethod1();Consumer<TargetParentClass> consumer2 = p -> p.parentMethod2();for (TargetParentClass p : List.of(parent)) {consumer1.accept(p);consumer2.accept(p);}
// Switch case + variable callswitch (targetParam.getCode()) {case 1:targetParam.variableCall();break;case 2:inner.innerVariableCall();break;}
// Override violation: inner class method overrides without @OverrideTargetClass.TargetInner overrideInner = new TargetClass.TargetInner() {public void innerVariableCall() {}};} catch (Exception e) {e.printStackTrace();}
return result;}}
/**
Javadoc for TargetClass (target_feature: javadoc)
Abstract class extending TargetParentClass (target_feature: extends)*/abstract class TargetClass extends TargetParentClass {public int getCode() {return 1;}
public void variableCall() {}
public class TargetInner {public void innerVariableCall() {}}}
// Target parent class (for method_feature: parent_class)class TargetParentClass {protected String superField1 = "super1";protected String superField2 = "super2";
public void parentMethod1() {}public void parentMethod2() {}}
// Others class for call_methodstrictfp class OtherClass {public First getFirst() {return new First();}
public class First {public Second getSecond() {return new Second();}}
public class Second {public TargetClass getThird() {return new TargetClass() {};}}}