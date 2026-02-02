package test;
import java.util.function.IntSupplier;
public record SourceRecord(int id) {class InnerClass {@Overridepublic void toString() {class LocalInner {private LocalInner(TargetRecord tr) {tr.value1();tr.value2();tr.value3();}}assert id > 0 : "ID must be positive";super();TargetRecord param = new TargetRecord(10, "test");param.value1();IntSupplier supplier = () -> overloadedMethod(5);new LocalInner(param);}
private int overloadedMethod(int a) {return a * 2;}
private int overloadedMethod(String s) {return s.length();}}
{Runnable r = new Runnable() {public void run() {}};}}
abstract record TargetRecord(int value1, String value2) {int value3() {class LocalInner {}return value1 + value2.length();}}