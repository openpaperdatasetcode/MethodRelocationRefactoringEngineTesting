package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Consumer;
private class SourceClass extends ParentClass {public List<String> methodToMove(TargetClass param) {class LocalInner {}LocalInner local = new LocalInner();
new Runnable() {public void run() {}};
int i = 0;do {int val = param.targetField;i++;} while (i < 3);
Consumer<TargetClass> consumer = (t) -> {t.setField(3);t.getField();};
List<String> list = new ArrayList<>();list.add(param.getField());return list;}
@Overridepublic void overrideMethod() {}}
private class ParentClass {public void overrideMethod() {}}
/**
Javadoc for TargetClass*/private class TargetClass {private String targetField;
public final String getField() {return targetField;}
public final void setField(int value) {this.targetField = String.valueOf(value);}}