package test;
import java.util.function.Supplier;import other.OthersClass;import java.io.IOException;
non-sealed abstract class SourceClass {public abstract void abstractMethod();
private int process(TargetClass.Inner.Rec... recs) throws IOException {int total = 0;
// Anonymous inner class 1Runnable init = new Runnable() {@Overridepublic void run() {for (TargetClass.Inner.Rec rec : recs) {rec.value = 0;}}};init.run();
// Anonymous inner class 2Supplier<Integer> counter = new Supplier<Integer>() {@Overridepublic Integer get() {return total;}};
// Super constructor invocation in target innerTargetClass.Inner inner = new TargetClass().new Inner();
for (TargetClass.Inner.Rec rec : recs) {// Super keywordSystem.out.println(super.toString());
// Variable call - access target inner rec's fieldtotal += rec.value;
// Access instance fieldrec.count++;
// Empty statement;
// Call others_class's synchronized varargs method in functional interfaceSupplier<Object> processor = () -> OthersClass.process(rec, "processed", total);processor.get();}
if (total < 0) {// Requires throwsthrow new IOException("Invalid total");}
return counter.get();}}
abstract class TargetClass {public class Inner {public class Rec {public int value;public int count;
public Rec() {super(); // Super constructor invocation}}}}
package other;
import test.TargetClass;
public class OthersClass {// Synchronized varargs methodpublic synchronized static Object process(TargetClass.Inner.Rec rec, String... args) {rec.value = args.length;return rec.count;}}