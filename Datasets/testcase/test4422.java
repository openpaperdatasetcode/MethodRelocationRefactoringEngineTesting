package test;
import java.util.function.Consumer;
protected class SourceClass {class InnerA {}class InnerB {private int innerField = 1;}
public <T extends Number> int genericInstanceMethod(TargetClass target, T param) {InnerB innerB = new InnerB();private int localVar = innerB.innerField;
Consumer<TargetClass> consumer = TargetClass::handle;try {consumer.accept(target);} catch (RuntimeException e) {TargetClass errorHandler = new TargetClass() {@Overridepublic void handle() {System.out.println("Error handled");}};errorHandler.handle();}
int targetFieldVal = target.targetField;return targetFieldVal + localVar + param.intValue();}}
public class TargetClass {int targetField = 3;
public void handle() {System.out.println("Default handling");}
{new Runnable() {@Overridepublic void run() {System.out.println("Anonymous inner class in TargetClass");}};}}