package test.refactoring.movemethod;
import java.util.ArrayList;import java.util.List;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {}
/**
Target record class with javadoc*/protected record TargetRecord(String data) {class LocalInnerInTarget {void process() {}}
public List<String> overloadedMethod() {return new ArrayList<>();}}
record SourceRecord(int id) {static class Nested1 {}static class Nested2 {}
@MethodAnnpublic List<String> overloadedMethod(TargetRecord target) {List rawList = new ArrayList();int var = 0;do {var++;if (var % 2 == 0) {continue;}rawList.add(var);} while (var < 5);
switch (target.data().length()) {case 1:var = 1;break;case 2:var = 2;break;default:throw new IllegalArgumentException("Invalid length");}
Object varCall = rawList.get(0);assert varCall instanceof Integer : "Invalid type";
TargetRecord.LocalInnerInTarget inner = new TargetRecord.LocalInnerInTarget();inner.process();
List<String> result = new ArrayList<>();result.add(target.data());return result;}}
import org.junit.Test;import static org.junit.Assert.*;
public class MoveMethodTest3011 {@Testpublic void testOverloadedMethod() {SourceRecord source = new SourceRecord(1);TargetRecord target = new TargetRecord("test");List<String> result = source.overloadedMethod(target);assertEquals(1, result.size());assertEquals("test", result.get(0));}}