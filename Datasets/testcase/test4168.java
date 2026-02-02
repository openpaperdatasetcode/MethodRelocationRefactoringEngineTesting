package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {public class FirstMemberInner {public class RecursiveInner {public List<String> process(TargetClass target) {assert target != null : "Target class cannot be null";
class LocalTypeDecl {String formatField() {return "TargetField: " + target.targetField;}}
LocalTypeDecl typeDecl = new LocalTypeDecl();List<String> result = new ArrayList<>();result.add(typeDecl.formatField());
TargetClass.MemberInner targetInner = target.new MemberInner();result.addAll(targetInner.fetchData());
if (result.isEmpty()) {throw new IllegalArgumentException("Result list is empty");}
return result;}}}
public class SecondMemberInner {public List<String> delegateProcess(TargetClass target) {return new FirstMemberInner().new RecursiveInner().process(target);}}}
class TargetClass {String targetField = "target_data";
public class MemberInner {public List<String> fetchData() {super.toString();return List.of("item1", "item2");}}}