 
package test;
import java.util.function.Function;
class SourceClass {public class InnerSource {public final TargetClass varargsMethod(TargetClass.InnerTarget... innerTargets) {TargetClass result = new TargetClass();
Function<TargetClass.InnerTarget[], TargetClass> func = this::processVarargs;result = func.apply(innerTargets);
assert innerTargets.length >= 3 : "At least 3 inner targets required";
labeledBlock: {for (TargetClass.InnerTarget inner : innerTargets) {System.out.println(inner.getField());if (inner.getField() == null) break labeledBlock;}}
; // Empty statement
return result;}
public TargetClass processVarargs(TargetClass.InnerTarget... inners) {TargetClass.StaticNested staticNested = new TargetClass.StaticNested();return staticNested.process(inners);}
public TargetClass processVarargs(TargetClass target, TargetClass.InnerTarget... inners) {super.toString();return target;}}
{new Runnable() {@Overridepublic void run() {TargetClass.InnerTarget rawInner = new TargetClass().new InnerTarget();new InnerSource().varargsMethod(rawInner);}}.run();}}
protected class TargetClass extends ParentClass {public class InnerTarget {private String field;
public String getField() {return field;}
public void setField(String field) {this.field = field;}}
public static class StaticNested {public TargetClass process(TargetClass.InnerTarget... inners) {TargetClass target = new TargetClass();for (TargetClass.InnerTarget inner : inners) {inner.setField("processed");}return target;}}}
class ParentClass {protected String parentField = "parent_data";}