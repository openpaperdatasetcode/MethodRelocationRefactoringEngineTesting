package same.pkg;
import java.util.function.Supplier;
interface DataProcessor {TargetClass process(String data);}
class ParentTargetClass {protected String parentField = "parent_data";}
private class TargetClass extends ParentTargetClass implements DataProcessor {private int field1;private String field2;private boolean field3;static int staticField = 0;
public TargetClass(int f1, String f2, boolean f3) {this.field1 = f1;this.field2 = f2;this.field3 = f3;staticField++;}
@Overridepublic TargetClass process(String data) {class LocalInnerRec {TargetClass recursiveUpdate(int depth) {if (depth <= 0) {return new TargetClass(field1, field2 + data, field3);}return new LocalInnerRec().recursiveUpdate(depth - 1);}}return new LocalInnerRec().recursiveUpdate(2);}}
private class SourceClass {private TargetClass targetField;private String sourceData = "source_val";
class SourceMemberInner {TargetClass createTarget() {return new TargetClass(1, sourceData, true);}}
protected Object handleTarget() {targetField = new SourceMemberInner().createTarget();int count = 0;
private DoHandler doHandler = new DoHandler();do {TargetClass processed = doHandler.processTarget(targetField, sourceData);count++;if (count >= 3) break;} while (targetField.field3);
class LocalInner {Object getCombinedData() {return SourceClass.this.targetField.field2 + "" +
SourceClass.this.sourceData + "" +TargetClass.staticField;}}
Supplier<TargetClass> creator = TargetClass::new;TargetClass newTarget = creator.get(2, "supplied", false);return new LocalInner().getCombinedData();}
private class DoHandler {public TargetClass processTarget(TargetClass target, String data) {return DataProcessor.process(data);}}}