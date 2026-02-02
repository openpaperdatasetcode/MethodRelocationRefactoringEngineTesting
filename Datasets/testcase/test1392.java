package test;
import java.util.List;import java.util.ArrayList;
protected class SourceClass {class SourceInner { // source_innerprotected Object methodToMove(TargetClass.TargetParam param) { // contains target parameter (per_condition)// super constructor invocationSourceInner inner = new SourceInner() {};
// expression statementString expr = "test: " + param.getValue();
// variable callinner.doSomething();TargetClass target = new TargetClass();
// depends_on_inner_classSourceInnerDependency dep = new SourceInnerDependency();dep.run();
// SynchronizedStatement (private, super.field, 1, pos:same_package_target)synchronized (target) {SuperClass superObj = new SuperClass();superObj.superField = 1; // super.field, 1}
// call_method: others_class, protected, abstract, super.methodName(), pos:forAbstractOthers others = new ConcreteOthers();List<String> resultList = new ArrayList<>();for (int i = 0; i < 1; i++) {resultList = others.callSuperMethod();}
return new Object();}
private void doSomething() {}
// Inner class for depends_on_inner_classprivate class SourceInnerDependency {void run() {}}}}
public class TargetClass {static class TargetParam {String getValue() { return "paramValue"; }}
// anonymous inner class (target_feature)Runnable anonymous = new Runnable() {@Overridepublic void run() {}};}
class SuperClass {protected int superField;}
abstract class AbstractOthers { // others_class, abstractprotected List<String> callSuperMethod() {super.toString(); // super.methodName()return new ArrayList<>();}}
class ConcreteOthers extends AbstractOthers {}