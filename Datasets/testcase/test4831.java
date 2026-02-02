package test.refactoring;
import java.util.ArrayList;
import java.util.List;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface RefactorTestMethod {}

sealed strictfp class SourceClass permits SourceSubClass {
private String outerField = "source_outer_data";

static class SourceStaticNested {
public String nestedMethod(String input) {
return "nested_" + input;
}
}

class SourceMemberInner {
public String getInnerData() {
return SourceClass.this.outerField + "_inner";
}
}

@RefactorTestMethod
default List<String> varargsMethod(TargetClass.TargetInnerRec... targetParams) {
List<String> result = new ArrayList<>();
final String[] staticArray = {"init_val_1", "init_val_2", "init_val_3"};

SourceMemberInner inner = new SourceMemberInner();
result.add(inner.getInnerData());

for (int i = 0; i < staticArray.length; i++) {
result.add(staticArray[i] + "_" + i);
}

for (TargetClass.TargetInnerRec target : targetParams) {
String targetData = target.getTargetRecData();
String nestedResult = SourceStaticNested.nestedMethod(targetData);
result.add(nestedResult);
variableCall(target);
}

result.add(SourceClass.this.outerField);
return result;
}

private void variableCall(TargetClass.TargetInnerRec target) {
System.out.println("Variable call: " + target.getTargetRecData());
}
}

non-sealed class SourceSubClass extends SourceClass {}

protected class TargetClass {
static class TargetInnerRec {
private String targetRecData;

public TargetInnerRec(String targetRecData) {
this.targetRecData = targetRecData;
}

public String getTargetRecData() {
return targetRecData;
}
}
}