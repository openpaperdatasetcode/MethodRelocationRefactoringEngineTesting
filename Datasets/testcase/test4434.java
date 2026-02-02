package test;
import java.util.List;import java.util.ArrayList;
protected class Source<T> {private Target<String> targetField = new Target<>();private static String sourceStaticField = "static_data";
class MemberInner {T innerData;}
private List<String> varargsMethod(T... params) {List<String> result = new ArrayList<>();
privateConstructorInvocation(targetField);
super.toString();
variableCall(targetField);
for (T param : params) {result.add(param.toString() + "_" + sourceStaticField);}
result.add(Target.StaticNested.targetStaticField);return result;}
private void privateConstructorInvocation(Target<?> target) {Target.StaticNested nested = new Target.StaticNested(2);result.add(String.valueOf(nested.nestedField));}
private void variableCall(Target<?> target) {int val = target.targetField;Target.StaticNested staticNested = new Target.StaticNested(1);result.add(String.valueOf(staticNested.nestedField));}
void methodWithAnonymous() {Runnable r = new Runnable() {@Overridepublic void run() {try {List<String> data = varargsMethod("param1", "param2");} catch (Exception e) {e.printStackTrace();}}};}}
class Target {
int targetField = 5;
static class StaticNested {static String targetStaticField = "target_static";int nestedField;
public StaticNested(int num) {this.nestedField = num;}}}