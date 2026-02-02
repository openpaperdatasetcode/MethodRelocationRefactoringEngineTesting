package test.refactoring;
import other.package.DiffPackageClass;
import java.util.ArrayList;
import java.util.List;

public class SourceClass {
private TargetClass targetField;

static class SourceStaticNested1 {
public static String processData(String input) {
return "nested1_" + input;
}
}

static class SourceStaticNested2 {
private int nestedCount = 0;

public int incrementCount() {
return ++nestedCount;
}
}

public class SourceInner {
private String innerField = "source_inner_field";

/**

Method Javadoc: Varargs method to process TargetInnerRec instances
@param targets Varargs of TargetClass.TargetInnerRec to process
@return Combined result of processing as Object
*/
public synchronized Object varargsMethod(TargetClass.TargetInnerRec... targets) {
class LocalTypeDeclaration {
String combineData(String... parts) {
StringBuilder sb = new StringBuilder();
for (String part : parts) {
sb.append(part).append("|");
}
return sb.toString();
}
}
LocalTypeDeclaration localType = new LocalTypeDeclaration();
SourceStaticNested2 nested2 = new SourceStaticNested2();
DiffPackageClass diffPackageObj = new DiffPackageClass();
List<String> processed = new ArrayList<>();
static WhileStatement: {
int count = 0;
while (count < 3) {
this.innerField = "updated_inner_" + count;
processed.add(SourceStaticNested1.processData(this.innerField));
count = nested2.incrementCount();
if (diffPackageObj.checkCount(count)) {
break WhileStatement;
}
}
}
for (TargetClass.TargetInnerRec target : targets) {
String targetData = target.getInnerRecData();
processed.add(localType.combineData(targetData, SourceClass.this.targetField.getTargetData()));
variableCall(target);
}
return processed;
}

private void variableCall(TargetClass.TargetInnerRec target) {
System.out.println("Processed target: " + target.getInnerRecData());
}
}

public SourceClass(TargetClass targetField) {
this.targetField = targetField;
}
}

class TargetClass {
private String targetData;

class TargetInnerRec {
private String innerRecData;

public TargetInnerRec(String innerRecData) {
this.innerRecData = innerRecData;
}

public String getInnerRecData() {
return innerRecData;
}
}

public TargetClass(String targetData) {
this.targetData = targetData;
}

public String getTargetData() {
return targetData;
}
}

// Diff package class (simulated separate package structure)
package other.package;

public class DiffPackageClass {
public boolean checkCount(int count) {
return count >= 2;
}
}