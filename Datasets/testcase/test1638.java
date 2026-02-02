package test;
import java.util.ArrayList;import java.util.List;import other.OthersClass;
interface DataProcessor {List<String> process(TargetClass target);}
final final class SourceClass implements DataProcessor {public class MemberInner {public TargetClass.Inner createInner(String data) {return new TargetClass().new Inner(data);}}
// Anonymous inner class implementing abstract methodprivate DataProcessor helper = new DataProcessor() {@Overridepublic List<String> process(TargetClass target) {return SourceClass.this.process(target);}};
@Overridepublic List<String> process(TargetClass target) {List<String> result = new ArrayList<>();MemberInner innerCreator = new MemberInner();
// 3 default VariableDeclarationExpressionsString val1 = target.name;int val2 = target.id;TargetClass.Inner innerVal = innerCreator.createInner(val1);
// If statement with private varargs method from others_class (3 parameters)if (target.id > 0) {TargetClass processed = OthersClass.transform(this, target, val1, val2 + "");result.add(processed.name);} else {result.add("invalid_id");}
public class TargetClass {public String name;public int id;
public class Inner {private String data;
public Inner(String data) {this.data = data;}
public String getData() {return data;}}}
package other;
import test.SourceClass;import test.TargetClass;
public class OthersClass {// Private varargs methodprivate static TargetClass transform(SourceClass source, TargetClass target, String... args) {target.name = args[0] + "_" + args[1];return target;}}