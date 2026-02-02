package test;
import java.util.ArrayList;import java.util.List;
// Protected source class with extends, anonymous inner, and member inner classesprotected class SourceClass extends ParentClass {// Member inner class (source_class feature)class MemberInner {class InnerRec {// Default access instance method (position: source_inner_rec)List<String> process(TargetClass target) {// Type declaration statementclass LocalType {}new LocalType();
// this(arguments) - constructor chaining in inner classthis("initial");
List<String> result = new ArrayList<>();
// Switch statementswitch (target.getType()) {case 1:result.add("Type 1");break;case 2:result.add("Type 2");break;default:result.add("Default");}
// Collection operations (static method_feature position)result.forEach(item -> {Object data = InnerHelper.staticProcess(SourceClass.this, target);result.add(data.toString());});
// Access instance methodtarget.updateData("processed");
// Depends on inner classtarget.staticNested.process();
variableCall(target);return result;}
// Constructor for this(arguments)InnerRec() {this("default");}
InnerRec(String param) {}
private void variableCall(TargetClass target) {target.doTask();}}}
// Anonymous inner class (source_class feature)private final Runnable anonymousTask = new Runnable() {@Overridepublic void run() {System.out.println("Anonymous task executed");}};
// Static inner class for method_featureprotected static class InnerHelper {// Static method_feature (1 parameter, inner_class, static)protected static Object staticProcess(SourceClass outer, TargetClass target) {// outerInstance.new InnerClass().methodName()return outer.new MemberInner().new InnerRec().process(target).size();}}}
// Parent class for source_class extends featureclass ParentClass {}
// Protected target class with static nested class (target_feature)protected class TargetClass {private int type;private String data;
public static class StaticNested {public void process() {}}
public StaticNested staticNested = new StaticNested();
public int getType() {return type;}
public void updateData(String newData) {this.data = newData;}
public void doTask() {}}