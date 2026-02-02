package source;
import target.TargetClass;import java.util.ArrayList;import java.util.List;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Processed {}
public abstract class SourceClass {public static class StaticNested {public class Inner {@Processedprivate List<String> process(TargetClass.Inner.Rec... recs) {List<String> result = new ArrayList<>();// Final ThisExpression (1 occurrence)final SourceClass.ThisExpression thisExpr = new SourceClass.ThisExpression();
// Private EnhancedForStatement with 3 super.field accessesprivate for (TargetClass.Inner.Rec rec : recs) {result.add(rec.getSuperField()); // 1st super.fieldresult.add(rec.getSuperField() + "_suffix"); // 2nd super.fieldresult.add("len:" + rec.getSuperField().length()); // 3rd super.field}
// For statementfor (int i = 0; i < recs.length; i++) {// Variable call - access target inner rec's fieldresult.add(recs[i].data);if (recs[i].data == null) {// Throw statementthrow new IllegalArgumentException("Null data at index " + i);}}
return result;}}}
public class ThisExpression {}}
package target;
import java.util.List;
public abstract class TargetClass extends SuperClass {public static class Inner {public class Rec {public String data;
public String getSuperField() {return superField; // Access super class field}}}}
package target;
public class SuperClass {protected String superField = "super_data";}