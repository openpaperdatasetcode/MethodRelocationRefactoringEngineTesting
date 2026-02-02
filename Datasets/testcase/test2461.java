package test.refactoring.movemethod;
import java.io.IOException;import java.util.Arrays;import java.util.List;
protected class TargetClass {public int id;public String name;
public TargetClass(int id, String name) {this.id = id;this.name = name;// Local inner class in targetclass Initializer {void setup() {if (name == null) name = "default";}}new Initializer().setup();}
public String getInfo() {return id + ":" + name;}}
class SubTargetClass extends TargetClass {public SubTargetClass(int id, String name) {super(id, name);}
@Overridepublic String getInfo() {return "sub_" + super.getInfo();}}
abstract class AbstractProcessor {public abstract TargetClass process(TargetClass target);}
class SourceClass {static class SourceStaticNested {}
protected static int process(TargetClass target) throws IOException {if (target == null) {throw new IOException("Target cannot be null"); // Requires throws}
// Variable callObject varCall = target.name;
// Empty statement;
// Assert statementassert target.id >= 0 : "ID must be non-negative";
// NullLiteral (2 occurrences)TargetClass nullTarget1 = null;String nullStr = null;
// Enhanced for statementList<TargetClass> targets = Arrays.asList(new TargetClass(1, "one"),new TargetClass(2, "two"),target);int totalId = 0;for (TargetClass t : targets) {totalId += t.id;}
// SwitchStatement with obj.field (2 occurrences)switch (target.id) {case 1:target.name = "modified_one";break;case 2:target.name = "modified_two";break;default:target.name = "modified_other";}
// While loop with sub class instance methods (3)int count = 0;TargetClass subResult = null;while (count < 3) {subResult = new SubTargetClass(count, "sub_" + count).getInfo();count++;}
return totalId;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3155 {@Testpublic void testStaticMethod() throws IOException {TargetClass target = new TargetClass(3, "three");int result = SourceClass.process(target);assertEquals(6, result); // 1 + 2 + 3assertEquals("modified_other", target.name);}}