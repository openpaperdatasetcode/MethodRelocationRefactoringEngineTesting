package test.refactoring;
import java.lang.reflect.Method;import java.io.IOException;
record SourceRecord(String sourceData) {private TargetRecord targetField = new TargetRecord("initTargetData");
public static class SourceStaticNested {void invokeSourceMethod(SourceRecord source) throws IOException {source.processTarget(source.targetField);}}
void createLocalInner() throws IOException {class SourceLocalInner {void useProcessMethod() throws IOException {processTarget(targetField);}}new SourceLocalInner().useProcessMethod();}
strictfp void processTarget(TargetRecord target) throws IOException {super.toString();
TargetRecord.TargetStaticNested targetStatic = new TargetRecord.TargetStaticNested();expressionStatement(target, targetStatic);
variableCall(target, "processed");
try {Method method = TargetRecord.class.getMethod("updateData", String.class);method.invoke(target, "reflectionData");} catch (Exception e) {throw new IOException("Reflection call failed", e);}}
private void variableCall(TargetRecord target, String suffix) {target.updateData(target.targetData() + "_" + suffix);}
private void expressionStatement(TargetRecord target, TargetRecord.TargetStaticNested staticNested) {staticNested.setNestedData(target.targetData() + "_expr");}}
public record TargetRecord(String targetData) {public static class TargetStaticNested {private String nestedData;
public void setNestedData(String data) {this.nestedData = data;}
public String getNestedData() {return nestedData;}}
public void updateData(String newData) {this.targetData = newData;}}