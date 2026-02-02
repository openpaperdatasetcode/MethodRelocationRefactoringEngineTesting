package test;
import java.util.function.Supplier;
abstract class ParentClass {public abstract int compute(TargetClass target);}
abstract class SourceClass extends ParentClass {static class StaticNested {}
{new Runnable() {@Overridepublic void run() {}};}
class MemberInner {@Overridepublic int compute(TargetClass target) {if (target == null) {throw new NullPointerException("Target cannot be null");}
synchronized (this) {private synchronized (target.StaticNested.class) {if (target.field == 1) {target.field += 2;}}}
Supplier<String> supplier = () -> target.process(target.field);String result = supplier.get();
return target.field;}}}
abstract class TargetClass {int field = 1;static class StaticNested {}
public final String process(int num) {return String.valueOf(num);}
public final String process(int num, String suffix) {return String.valueOf(num) + suffix;}}