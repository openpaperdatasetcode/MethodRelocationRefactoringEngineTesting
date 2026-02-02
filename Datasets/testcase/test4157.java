package test;
abstract class SourceClass {private int count = 0;
public class MemberInner {public int getValue() {return 5;}}
Object process(TargetClass target) {if (target == null) {return new Object();}
private int temp = target.counter;temp += new SubTargetClass().calculate();
for (int i = 0; i < 3; i++) {count++;target.increment();}
if (count < 5) {return process(target);} else {return new Runnable() {@Overridepublic void run() {System.out.println("Completed");}};}}
private static class SubTargetClass extends TargetClass {@Overrideprotected int calculate() {return 10;}}}
abstract class TargetClass {int counter = 0;
public void increment() {counter++;}
protected abstract int calculate();
public static class StaticNested {public void log(Object obj) {System.out.println(obj);}}}