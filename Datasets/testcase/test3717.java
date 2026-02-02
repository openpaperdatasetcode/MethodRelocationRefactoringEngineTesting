import java.util.ArrayList;import java.util.List;
strictfp class SourceClass implements DataProcessor {private TargetClass targetField;
protected int instanceMethod(TargetClass target, int depth) throws IllegalArgumentException {if (target == null) {throw new NullPointerException("Target cannot be null");}if (depth < 0) {throw new IllegalArgumentException("Depth cannot be negative");}if (depth == 0) {return 0;}
for (int i = 0; i < depth; i++) {if (i == 1) {TargetClass.StaticNested.field1 = "skip_1";continue;}
TargetClass newTarget = new TargetClass();newTarget.staticNested = new TargetClass.StaticNested();
expressionStatement: {newTarget.staticNested.setField2("expr_data_" + i);}
variableCall(newTarget.staticNested);
TargetClass.StaticNested.field1 = "val1_" + i;TargetClass.StaticNested.field2 = "val2_" + i;TargetClass.StaticNested.field3 = "val3_" + i;;}
return depth + instanceMethod(target, depth - 1);}
private void variableCall(TargetClass.StaticNested nested) {nested.setField3(SourceClass.this.targetField.staticNested.getField3() + "_updated");}
@Overridepublic void processData(String data) {this.targetField = new TargetClass();targetField.staticNested.setField1(data);}
@CustomAnnotation(value = TargetClass.getSynchronizedList())public void callInAnnotation() throws IllegalArgumentException {instanceMethod(new TargetClass(), 3);}}
interface DataProcessor {void processData(String data);}
public class TargetClass {public StaticNested staticNested = new StaticNested();
public static class StaticNested {public static String field1 = "default1";public static String field2 = "default2";public static String field3 = "default3";
public void setField2(String val) {field2 = val;}
public void setField3(String val) {field3 = val;}
public String getField3() {return field3;}}
public static synchronized List<String> getSynchronizedList() {List<String> list = new ArrayList<>();list.add(StaticNested.field1);list.add(StaticNested.field2);list.add(StaticNested.field3);return list;}}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface CustomAnnotation {List<String> value();}