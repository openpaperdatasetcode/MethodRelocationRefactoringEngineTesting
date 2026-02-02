import java.util.Objects;
class SourceClass {private TargetClass targetField = new TargetClass();
public class SourceInnerClass {@FunctionalInterfaceinterface TargetProcessor {TargetClass process(TargetClass... targets);}
public final TargetClass varargsMethod(TargetClass... targets) {assert targets != null : "Varargs parameter cannot be null";
labeledStmt: {if (targets.length == 0) {break labeledStmt;}for (TargetClass target : targets) {variableCall(target);; // Empty statement}}
TargetProcessor processor = this::varargsOverloadedMethod;return processor.process(targets);}
public final TargetClass varargsOverloadedMethod(TargetClass... targets) {raw_type TargetClass rawTarget = new TargetClass();super; // Super keyword usage
if (targets.length >= 3) {return targets[2];}return rawTarget;}
private void variableCall(TargetClass target) {target.staticNestedClass.updateCount();}}
{new Runnable() {@Overridepublic void run() {SourceInnerClass inner = new SourceInnerClass();inner.varargsMethod(targetField, new TargetClass(), new TargetClass());}}.run();}}
protected class TargetClass extends SuperTargetClass {public static class StaticNestedClass {private static int count = 0;
public static void updateCount() {count++;}
public static int getCount() {return count;}}}
class SuperTargetClass {}