package test.refactoring;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
abstract class SourceClass {private TargetClass targetField = new TargetClass();
static class StaticNested {static List<String> getInfo(TargetClass target) {return TargetClass.getDetails(target);}}
final int recursiveMethod(int n) {if (n <= 0) return 0;
TargetClass localTarget = targetField;int val = localTarget.count;val++;
Runnable func = () -> StaticNested.getInfo(localTarget);func.run();
variableCall();
try {if (n % 2 == 0) throw new IOException();} catch (IOException e) {// handle}
return val + recursiveMethod(n - 1);}
private void variableCall() {}
void methodWithLocalInner() {class LocalInner {void useRecursive() {recursiveMethod(5);}}new LocalInner().useRecursive();}}
private class TargetClass implements SomeInterface {int count;
protected static List<String> getDetails(TargetClass target) {return new ArrayList<>(List.of(String.valueOf(target.count)));}}
interface SomeInterface {}