package test;
final class SourceClass {private TargetClass targetField;private int sourceField;
private class InnerClass {void innerMethod() {}}
private TargetClass recursiveMethod(int value) {assert value >= 0 : "Value must be non-negative";if (value == 0) {throw new NullPointerException("Value is zero");}InnerClass inner = new InnerClass();inner.innerMethod();sourceField++;
for (String s : new String[]{"a", "b"}) {if (s.equals("a")) continue;}
TargetClass result;if (value > 1) {result = recursiveMethod(value - 1);} else {result = new TargetClass() {@Overridevoid targetMethod() {}};}return result;}
void anotherMethod() {new Runnable() {@Overridepublic void run() {sourceField = 5;}};new Thread() {@Overridepublic void run() {targetField = new TargetClass();}};}}
class TargetClass {void targetMethod() {}}