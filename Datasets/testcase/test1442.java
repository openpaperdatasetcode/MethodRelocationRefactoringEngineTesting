package test;
import java.util.List;import java.util.ArrayList;import java.io.IOException;
public class SourceClass {class MemberInner {protected abstract List<String> process(TargetClass target) throws IOException;}
class ConcreteInner extends MemberInner {@Overrideprotected List<String> process(TargetClass target) throws IOException {List<String> result = new ArrayList<>();TargetClass.LocalHolder holder = new TargetClass.LocalHolder();
String chainResult = holder.createBuilder().setField(target.field).build().getValue();result.add(chainResult);
return result;}}}
protected class TargetClass {int field;
class LocalHolder {Builder createBuilder() {class Builder {private int value;
Builder setField(int field) {this.value = field;return this;}
Result build() {return new Result(value);}}return new Builder();}
class Result {private int value;
Result(int value) {this.value = value;}
String getValue() {return String.valueOf(value);}}}}