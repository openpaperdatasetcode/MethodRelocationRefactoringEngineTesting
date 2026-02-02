package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
interface RecordProcessor {void process(TargetRecord<String> target);}
record SourceRecord(String data) implements RecordProcessor {public static class NestedFirst {public static TargetRecord<String> createTarget(String value) {return new TargetRecord<>(value);}}
public static class NestedSecond {public static int sumLengths(List<String> list) {return list.stream().mapToInt(String::length).sum();}}
public class InnerSource {public class DeepInner {private void normalMethod(TargetRecord<String> target) {class LocalHandler {private List<String> createList(TargetRecord<String> t) {super();return new ArrayList<>(List.of(t.value()));}}
LocalHandler handler = new LocalHandler();Supplier<List<String>> listSupplier = handler::createList;List<String> result = target.value().isEmpty()? listSupplier.get(target): new ArrayList<>(List.of(target.value() + "_modified"));
for (String s : result) {int len = target::computeLength;System.out.println(len);}
TargetRecord.STATIC_FIELD = 1;System.out.println(TargetRecord.STATIC_FIELD);}}}
@Overridepublic void process(TargetRecord<String> target) {new InnerSource().new DeepInner().normalMethod(target);}}
public record TargetRecord<T>(T value) {public static int STATIC_FIELD;
public int computeLength() {class LocalCalculator {int calculate() {return value.toString().length();}}return new LocalCalculator().calculate();}
@Overridepublic String toString() {return "TargetRecord[" + value + "]";}}