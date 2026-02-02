package same.pkg;
import java.util.List;import java.util.ArrayList;
protected class SourceClass {static class InnerClass {final List<String> recursiveMethod(int depth, TargetClass target) {if (depth <= 0) {return new ArrayList<>();}List<String> list = new ArrayList<>();list.add(target.toString());list.addAll(recursiveMethod(depth - 1, target));return list;}}
synchronized List<String> varargsMethod(TargetClass targetParam, String... args) {variableCall(targetParam);access_instance_method(targetParam);
List<String> result = new ArrayList<>();for (String arg : args) {result.add(arg);}
if (args.length == 0) {assert false : "No arguments provided";}
try {InnerClass inner = new InnerClass();result.addAll(new ArrayList<>(inner.recursiveMethod(2, targetParam)));} catch (Exception e) {}
OthersClass others = new OthersClass();String ternaryResult = (args.length > 0) ? others.instanceMethod(args[0]) : "";result.add(ternaryResult);
return result;}
synchronized List<String> varargsMethod(TargetClass targetParam) {return varargsMethod(targetParam, "default");}
private void variableCall(TargetClass param) {String localVar = param.field;}
private void access_instance_method(TargetClass param) {param.instanceMethod();}
{new Runnable() {@Overridepublic void run() {}};}}
strictfp class TargetClass {String field = "targetField";
void instanceMethod() {}}
class OthersClass {String instanceMethod(String arg) {return arg.toUpperCase();}}