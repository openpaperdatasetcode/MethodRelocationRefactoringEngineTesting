package same;
import java.util.List;import java.util.ArrayList;
final class SourceClass {static class StaticNestedA {}static class StaticNestedB {}
class InnerRec extends TargetClass.InnerRec {@Overridepublic List<String> process() {return new ArrayList<>();}}
strictfp int calculate(TargetClass target) {TargetClass.InnerRec inner = target.new InnerRec();int result = 0;
switch (inner.type) {case "ADD":result = inner.getField1() + inner.getField2();break;case "MULTIPLY":result = inner.getField1() * inner.getField2();break;default:result = super.hashCode();break;}
List<String> data = inner.prepare().filter().collect();result += data.size();
return result;}}
package same;
import java.util.List;
/**
A strictfp target class with member inner class
/
strictfp class TargetClass {
/*
Member inner class handling calculation data*/class InnerRec {String type = "ADD";int field1 = 5;int field2 = 3;
int getField1() { return field1; }int getField2() { return field2; }
InnerRec prepare() { return this; }InnerRec filter() { return this; }List<String> collect() { return List.of("a", "b"); }
public List<String> process() {return List.of();}}}