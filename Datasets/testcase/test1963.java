package source;
import target.TargetClass;import other.OthersClass;import java.sql.SQLException;import java.util.List;
class SourceClass {protected void method(TargetClass target) throws SQLException {// Constructor invocation of targetTargetClass newTarget = new TargetClass();
// Super constructor invocation via subclassSubTarget subTarget = new SubTarget("init");
// Variable call to target's fieldtarget.value = "processed";
// Exception handling with call to others class methodtry {List<String> result = OthersClass.process(this, target);target.items.addAll(result);} catch (Exception e) {throw new SQLException("Processing failed", e);}}}
package target;
import java.util.ArrayList;import java.util.List;
private class TargetClass {String value;List<String> items = new ArrayList<>();
TargetClass() {}
TargetClass(String initial) {value = initial;}
void addItem(String item) {// Local inner classclass Validator {boolean isValid(String s) {return s != null;}}if (new Validator().isValid(item)) {items.add(item);}}}
class SubTarget extends TargetClass {SubTarget(String initial) {super(initial); // Super constructor invocation}}
package other;
import source.SourceClass;import target.TargetClass;import java.util.ArrayList;import java.util.List;
class OthersClass {private static List<String> process(SourceClass source, TargetClass target) {List<String> result = new ArrayList<>();result.add(target.value);return result;}}