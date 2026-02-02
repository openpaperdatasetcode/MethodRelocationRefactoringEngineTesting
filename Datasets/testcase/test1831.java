package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
protected class SourceClass {public static class StaticNestedOne {}public static class StaticNestedTwo {}
public record SourceInnerRec(int code) {strictfp TargetClass normalMethod(TargetClass.NestedRec... innerRecs) {TargetClass target = new TargetClass();List<TargetClass.NestedRec> validRecs = new ArrayList<>();
// With bounds (type parameter with bound)class BoundedProcessor<T extends TargetClass.NestedRec> {void process(T rec) {validRecs.add(rec);}}BoundedProcessor<TargetClass.NestedRec> processor = new BoundedProcessor<>();
// For statementfor (int i = 0; i < innerRecs.length; i++) {processor.process(innerRecs[i]);}
// While statementint index = 0;while (index < validRecs.size()) {TargetClass.NestedRec rec = validRecs.get(index);// Expression statementint value = rec.id() + rec.data().length();target.total += value;index++;}
// Switch statementswitch (validRecs.size()) {case 0:throw new IllegalArgumentException("No valid records provided");case 1:target.status = "SINGLE";break;default:target.status = "MULTIPLE";}
// Used by reflection (depends on inner class)try {Method recMethod = TargetClass.NestedRec.class.getMethod("id");for (TargetClass.NestedRec rec : validRecs) {target.total += (int) recMethod.invoke(rec);}} catch (Exception e) {// Requires try-catchtarget.status = "REFLECTION_ERROR: " + e.getMessage();}
// Variable call (depends on inner class)target.lastProcessed = validRecs.get(validRecs.size() - 1).data();
return target;}}}
public class TargetClass {public int total = 0;public String status;public String lastProcessed;
public static class StaticNested {}
public record NestedRec(int id, String data) {}}