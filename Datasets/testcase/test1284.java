package test.refactoring.movemethod;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
interface Processable {void process(TargetClass target);}
abstract class SourceClass implements Processable {private String outerPrivateField = "source_private";
static class StaticNestedSource {public static void log(String msg) {System.out.println("Static log: " + msg);}}
class MemberInnerSource {void useTarget(TargetClass target) {process(target);}}
@Overridepublic void process(TargetClass target) {// Constructor invocationTargetClass.MemberInnerTarget innerTarget = target.new MemberInnerTarget(outerPrivateField);
// Enhanced for statementList<String> dataList = new ArrayList<>(List.of("data1", "data2", "data3"));for (String data : dataList) {// Expression statement + variable callinnerTarget.updateData(data);StaticNestedSource.log(data);}
// Access outer private fieldString combined = outerPrivateField + "_" + target.getTargetField();innerTarget.setCombinedData(combined);}}
public class TargetClass {private String targetField = "target_field";
public class MemberInnerTarget {private String innerData;private String combinedData;
public MemberInnerTarget(String initData) {this.innerData = initData;}
public void updateData(String data) {this.innerData = data;}
public void setCombinedData(String combinedData) {this.combinedData = combinedData;}
public String getCombinedData() {return combinedData;}}
// Accessor methodpublic String getTargetField() {return targetField;}
public void setTargetField(String targetField) {this.targetField = targetField;}
public Object callViaTernary(boolean flag) {Supplier<Object> accessor = this::getTargetField;return flag ? accessor.get() : this::getTargetField;}}
public class ConcreteSource extends SourceClass {}
public class MoveMethodTest5194 {public static void main(String[] args) {SourceClass source = new ConcreteSource();TargetClass target = new TargetClass();
// Direct callsource.process(target);
// Call via inner classsource.new MemberInnerSource().useTarget(target);
// Call target method in ternaryObject ternaryResult = target.callViaTernary(true);System.out.println("Ternary result: " + ternaryResult);
// Used by reflectiontry {var method = SourceClass.class.getMethod("process", TargetClass.class);method.invoke(source, target);System.out.println("Reflection call successful");} catch (Exception e) {e.printStackTrace();}}}