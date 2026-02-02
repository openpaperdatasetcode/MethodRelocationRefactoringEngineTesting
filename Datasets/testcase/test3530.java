package source;
import target.TargetClass;import java.util.function.Supplier;
private class SourceClass {protected String outerProtected = "outer_protected";
public static class StaticNested {public static String getInfo() {return "static_nested_info";}}
protected int instanceMethod(TargetClass target) {// Access outer protected fieldString outerData = outerProtected;
// Constructor invocationTargetClass.Inner inner = target.new Inner(outerData);
// WhileStatement with super.field = 3class WhileProcessor {public void process() {int count = 0;while (count < 3) {String superField = target.getSuperField();if (!"3".equals(superField)) {throw new IllegalArgumentException("Invalid super field value");}count++;}}}new WhileProcessor().process();
// Try statementtry {variableCall: String innerField = inner.getInnerField();super.toString(); // Super keyword
// Call method in constructor parameter listOtherClass other = new OtherClass(inner::getInnerField);return other.process() == null ? 0 : innerField.length();} catch (NullPointerException e) {throw new RuntimeException("Processing failed", e);}}
{// Anonymous inner classSupplier<String> supplier = new Supplier<>() {@Overridepublic String get() {return StaticNested.getInfo();}};supplier.get();}}
package target;
/**
TargetClass with static nested class
Contains inner class and super field*/public class TargetClass extends TargetParent {private String field;
public TargetClass(String field) {super("3");this.field = field;}
public String getField() {return field;}
public class Inner {private String innerField;
public Inner(String data) {this.innerField = data + "_inner";}
public String getInnerField() {return innerField;}}
public static class Nested {public static String format(String input) {return input.toUpperCase();}}}
package target;
class TargetParent {protected String superField;
public TargetParent(String superField) {this.superField = superField;}
public String getSuperField() {return superField;}}
package source;
class OtherClass {private final Supplier<String> supplier;
public OtherClass(Supplier<String> supplier) {this.supplier = supplier;}
String process() {return supplier.get();}}