package test;
import java.sql.SQLException;import java.util.function.IntSupplier;
public class SourceClass {private int outerField = 5;
public class MemberInner {public class SourceInnerRec {Object methodToMove(TargetClass target) throws SQLException {if (target == null) throw new SQLException("Target cannot be null");
TargetClass.StaticNested staticNested = new TargetClass.StaticNested();TargetClass.InnerTarget innerRec = target.new InnerTarget();innerRec.innerField = SourceClass.this.outerField;
if (innerRec.innerField > 3) {; // Empty statementvariableCall(staticNested, innerRec);} else {// Call_method in if/else: method referenceIntSupplier supplier = innerRec::process;int result = OthersClass.call(supplier);innerRec.innerField = result;}
// Local inner classclass LocalInner {void updateTarget() {innerRec.innerField += SourceClass.this.outerField;}}new LocalInner().updateTarget();
return innerRec.innerField;}
private void variableCall(TargetClass.StaticNested nested, TargetClass.InnerTarget inner) {nested.setValue(inner.innerField);}}}}
public class TargetClass {public static class StaticNested {private int value;public void setValue(int val) {this.value = val;}}
public class InnerTarget {int innerField;public int process() {return innerField * 2;}
public class InnerRec {void useField() {System.out.println(InnerTarget.this.innerField);}}}}
class OthersClass {// Call_method: others_class type, instance method referencestatic int call(IntSupplier supplier) {return supplier.getAsInt();}}
