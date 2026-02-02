package test;
import java.util.List;import java.util.ArrayList;
public sealed class SourceClass permits SubSource {public class MemberInner {}
private TargetClass targetField;
/**
Accessor method to get string list
@return List<String> containing values*/public List<String> getStringList() {super();class LocalType {}LocalType typeDecl = new LocalType();
new Runnable() {@Overridepublic void run() {targetField.process();}};
TargetClass.Processor processor = new TargetClass.Processor();processor.process(this);
List<String> list = new ArrayList<>();list.add(targetField.name);return list;}
default Object defaultMethod() {return new Object();}}
final class SubSource extends SourceClass {}
public class TargetClass {String name;
public void process() {}
public class Processor {public void process(SourceClass source) {}}
void someMethod() {class TargetLocalInner {void doWork() {do {Runnable r = SourceClass::defaultMethod;Object obj = new SourceClass();} while (false);}}}}