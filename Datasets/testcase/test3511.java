package test;
import java.io.IOException;import java.util.Arrays;import java.util.List;
protected class SourceClass {public TargetClass instanceMethod(TargetClass target) throws IOException {class TypeDecl {TargetClass process(TargetClass t) {t.setField(t.getField() + "_processed");return t;}}
TypeDecl typeDecl = new TypeDecl();TargetClass processedTarget = typeDecl.process(target);
List<TargetClass> targetList = Arrays.asList(new TargetClass("data1"),new TargetClass("data2"),new TargetClass("data3"));
for (TargetClass t : targetList) {OtherClass other = new OtherClass();other.callSuperMethod(t);processedTarget.setField(processedTarget.getField() + "_" + t.getField());}
if (processedTarget.getField().isEmpty()) {throw new IOException("Target field cannot be empty");}
return processedTarget;}}
class TargetClass {private String field;
public TargetClass(String field) {this.field = field;}
public String getField() {return field;}
public void setField(String field) {this.field = field;}
public static class StaticNested {public static void formatField(TargetClass target) {target.setField(target.getField().toUpperCase());}}}
class ParentOtherClass {protected void processTarget(TargetClass target) {TargetClass.StaticNested.formatField(target);}}
class OtherClass extends ParentOtherClass {protected void callSuperMethod(TargetClass target) {super.processTarget(target);}}