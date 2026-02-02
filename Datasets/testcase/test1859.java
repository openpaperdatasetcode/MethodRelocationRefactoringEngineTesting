package test;
record SourceRecord(String data) {private String outerPrivate = "private_data";
// Member inner classpublic class SourceInner extends BaseProcessor {/**
Overrides process method to handle TargetRecord
@param target TargetRecord instance
@return Processed integer result*/@Overrideint process(TargetRecord target) {// Constructor invocationTargetRecord.StaticNested nested = new TargetRecord.StaticNested(target.value());
// Variable callint result = target.value().length() + nested.calculate();
// Super keywordsresult += super.baseValue();
// Depends on inner classSourceInnerHelper helper = new SourceInnerHelper();result += helper.getOffset();
// Do-while with private accessor methodint i = 0;do {TargetRecord rec = SourceInner.getTargetRecord(target, i);result += rec.value().length();i++;} while (i < 2);
// Throw statementif (result < 0) {throw new IllegalArgumentException("Invalid result");}
// Access outer privateresult += outerPrivate.length();
// Anonymous inner classRunnable logger = new Runnable() {@Overridepublic void run() {System.out.println("Result: " + result);}};logger.run();
return result;}
// Private accessor methodprivate static TargetRecord getTargetRecord(TargetRecord base, int offset) {return new TargetRecord(base.value() + offset);}
// Inner helper classprivate class SourceInnerHelper {int getOffset() {return 5;}}}}
abstract class BaseProcessor {protected int baseValue() {return 10;}
abstract int process(TargetRecord target);}
record TargetRecord(String value) extends TargetParent {// Static nested classpublic static class StaticNested {private String data;
public StaticNested(String data) {this.data = data;}
public int calculate() {return data.length() * 2;}}}
class TargetParent {}