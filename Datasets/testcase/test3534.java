package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import diffpackage.OthersClass;
interface ParentRecord {int moveMethod();}
public record SourceClass(String data) implements ParentRecord {protected int outerProtectedField = 10;
class MemberInner {}
static class StaticNested {}
@Overridepublic int moveMethod() {try {OthersClass.validate(targetClass.obj.field);if (targetClass.obj.field != 1) {throw new IllegalArgumentException("Field value mismatch");}
class TypeDeclaration {}new TypeDeclaration();
for (int i = 0; i < 1; i++) {variableCall();System.out.println(super.toString());}
return outerProtectedField;} catch (IllegalArgumentException e) {return -1;}}
public List<String> callMethod() {try {SourceClass source = new SourceClass("test");SourceClass.MemberInner inner = source.new MemberInner();source.moveMethod();throw new RuntimeException("Trigger call method");} catch (RuntimeException e) {return new ArrayList<>();}}
private void variableCall() {targetClass.innerClass.doTask();}
private final TargetClass targetClass = new TargetClass(1);}
public record TargetClass(int field) {public TargetObj obj = new TargetObj(field);
class TargetMemberInner {void doTask() {}}
private TargetMemberInner innerClass = new TargetMemberInner();
static class TargetObj {int field;TargetObj(int field) {this.field = field;}}
@Overridepublic int moveMethod() {return field;}}
package diffpackage;
import test.TargetClass;
public class OthersClass {public static void validate(int field) {if (field != 1) {throw new IllegalArgumentException("Invalid field value");}}}