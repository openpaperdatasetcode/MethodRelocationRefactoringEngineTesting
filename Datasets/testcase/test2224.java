package test;
import java.util.function.Function;
public class SourceClass implements Runnable {protected int outerProtected;
class MemberInner {protected TargetClass process(TargetClass target) {TargetClass.Inner inner = target.new Inner();Function<Integer, TargetClass> func = param -> inner.createTarget(param);
try {if (inner.getValue() > 0) {switch (inner.getType()) {case 1:outerProtected = 10;break;default:outerProtected = 20;}}
privateWhileLoop(inner);} catch (Exception e) {throw new RuntimeException(synchronizedMethod());}
class LocalInner {}return func.apply(1);}
private void privateWhileLoop(TargetClass.Inner inner) {while (inner.super.field == 1) {inner.update();}}
private synchronized int synchronizedMethod() {return super.hashCode();}}
@Overridepublic void run() {}}
public class TargetClass extends SuperClass {class Inner {int type;
int getValue() {return type;}
int getType() {return type;}
void update() {new Runnable() {@Overridepublic void run() {}};}
TargetClass createTarget(int param) {return new TargetClass();}}}
class SuperClass {int field;}