package test;
import java.util.List;import java.util.ArrayList;
class TargetSubClass extends TargetClass {@Overridepublic List<String> process(String arg) {return new ArrayList<>();}}
protected class SourceClass {static class StaticNested {static String staticField = "static";}
class FirstInner {class InnerRecursive {protected void methodToMove(TargetClass targetParam) {class LocalType {}LocalType local = new LocalType();
try {System.out.println(SourceClass.StaticNested.staticField);targetParam.variableCall();System.out.println(super.toString());} finally {// Cleanup}
int i = 0;do {List<String> result = TargetSubClass.process("test");i++;} while (i < 3);
Runnable r = new Runnable() {public void run() {}};}}}}
private class TargetClass {class MemberInner {}
void variableCall() {}
public List<String> process(String arg) {return new ArrayList<>();}}