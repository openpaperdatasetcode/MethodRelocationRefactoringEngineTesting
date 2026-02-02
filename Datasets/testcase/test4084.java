package test;
import java.io.IOException;
interface FunctionalInterface {void execute();}
record SourceRecord(TargetRecord targetField) {class MemberInner1 {void method1() {}}
class MemberInner2 {void method2() {}}
strictfp Object recursiveMethod(int depth) throws IOException {if (depth <= 0) {return new Object();}
super();
while (depth > 1) {int var = targetField.value();depth = var;}
FunctionalInterface func = () -> TargetRecord.protectedStaticMethod(1, targetField);func.execute();
return recursiveMethod(depth - 1);}}
/**
Javadoc for TargetRecord*/sealed record TargetRecord(int value) extends ParentRecord permits SubTargetRecord {TargetRecord {Runnable runnable = new Runnable() {@Overridepublic void run() {System.out.println(value);}};runnable.run();}
protected static void protectedStaticMethod(int num, TargetRecord target) {if (num == 1) {System.out.println(target.value());}}}
abstract class ParentRecord {}
non-sealed record SubTargetRecord(int value) extends TargetRecord {SubTargetRecord(int value) {super(value);}}