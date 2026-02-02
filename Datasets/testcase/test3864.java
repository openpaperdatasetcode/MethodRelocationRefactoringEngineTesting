package test.refactoring;
import java.util.Arrays;import java.util.List;
class SourceClass {// Anonymous inner class 1private Runnable anonRunnable1 = new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass();TargetClass result = processTarget(target, "anon1_data");}};
// Anonymous inner class 2private TargetClass.StaticNested anonNested = new TargetClass.StaticNested() {@Overridepublic String getNestedData() {return super.getNestedData() + "_anon2";}};
// Overloading method 1: returns TargetClass Type, protected accessprotected TargetClass processTarget(TargetClass target, String data) {// Expression statementtarget.setData(data);
// For statementfor (int i = 0; i < 3; i++) {variableCall(target, "for_" + i);}
// Enhanced for statementList<String> items = Arrays.asList("enh1", "enh2");for (String item : items) {target.getStaticNested().addItem(item);}
return target;}
// Overloading method 2: matches method type "overloading"protected TargetClass processTarget(TargetClass target, int num, String data) {target.setData(data + "_" + num);return processTarget(target, data);}
// Variable call featureprivate void variableCall(TargetClass target, String suffix) {target.setData(target.getData() + "_" + suffix);}
// Call method: inner_class, overriding, Lambda, pos: Lambda expressionspublic String callInnerOverload(TargetClass target) {TargetClass.StaticNested inner = target.getStaticNested();// Lambda expression (parameters -> methodBody) using inner class's overriding methodjava.util.function.Function<String, String> lambda = (param) -> inner.process(param);return lambda.apply("lambda_input");}}
private class TargetClass {private String data;private StaticNested staticNested = new StaticNested();
// Static nested class (target_feature)public static class StaticNested {private List<String> itemList = new ArrayList<>();
// Overriding-ready method (for call_method's "overriding" feature)public String process(String input) {return input.toUpperCase();}
public void addItem(String item) {itemList.add(item);}
public String getNestedData() {return String.join(",", itemList);}}
public String getData() {return data;}
public void setData(String data) {this.data = data;}
public StaticNested getStaticNested() {return staticNested;}}