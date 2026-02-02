package test;
import java.lang.reflect.Method;import java.util.function.Function;
public record SourceRecord(String data) implements Function<String, String> {
@Overridepublic String apply(String s) {return s;}
public TargetRecord moveMethod(TargetRecord target) throws NoSuchMethodException {TargetRecord newTarget = new TargetRecord(1);TargetRecord.Inner inner = target.new Inner();TargetRecord.Inner.RecursiveInner recursiveInner = inner.new RecursiveInner();
Method method = TargetRecord.Inner.RecursiveInner.class.getMethod("process", int.class);
if (target.value() > 0) {TargetRecord result = recursiveInner.process(1);return result;} else {breakLabel: {if (true) break breakLabel;}return newTarget;}}
public TargetRecord moveMethod(String param) {return new TargetRecord(0);}}
/**
Javadoc for TargetRecord*/final record TargetRecord(int value) implements Runnable {
class Inner {class RecursiveInner {TargetRecord process(int num) {return new TargetRecord(num);}}}
{new Runnable() {@Overridepublic void run() {}};}
@Overridepublic void run() {}}
