package test;
import java.util.function.Supplier;
private abstract class SourceClass {public class MemberInner {public class InnerRec {final void process(TargetClass... targets) {for (TargetClass target : targets) {TargetClass.Inner inner = target.new Inner();
Supplier<TargetClass> supplier = inner::createInstance;TargetClass newTarget = supplier.get();
if (newTarget == null) {break;}}
Runnable lambda = () -> {TargetClass parent = new TargetClass() {@Overridevoid action() {}};parent.new Inner().useSuper();};lambda.run();}
private TargetClass create(TargetClass.Inner inner) {return inner.createInstance();}}}}
public abstract class TargetClass {protected TargetClass() {super();}
public class Inner {TargetClass createInstance() {return new TargetClass() {@Overridevoid action() {}};}
void useSuper() {Runnable r = TargetClass.super::toString;r.run();}}
abstract void action();}
