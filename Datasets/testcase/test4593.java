package test;
interface SourceInterface {}
public class SourceClass implements SourceInterface {public static class FirstStaticNested {void nestedMethod1() {}}
public static class SecondStaticNested {void nestedMethod2() {}}
private void instanceMethod(TargetClass targetParam) {if (targetParam == null) {throw new NullPointerException("Target parameter is null");}
int doCount = 0;do {if (doCount == targetParam.innerField) {break;}doCount++;} while (doCount < 2);
int whileCount = 0;while (whileCount < 2) {strictfp TargetClass.TargetStaticNested nested = new TargetClass.TargetStaticNested();nested.nestedMethod();whileCount++;}
for (int i = 0; i < targetParam.innerField; i++) {if (i == 1) {break;}System.out.println(targetParam.innerField);}
FirstStaticNested first = new FirstStaticNested();first.nestedMethod1();SecondStaticNested second = new SecondStaticNested();second.nestedMethod2();}}
protected class TargetClass {int innerField = 2;
public static class TargetStaticNested {void nestedMethod() {}}}