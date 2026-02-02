package test;
import java.util.List;import java.util.ArrayList;
public record SourceClass(int id) {static class StaticNested {}
public List<String> process(TargetClass target) {class LocalInner {private int getValue() {return SourceClass.this.id;}}
private int count = 1;count += target.value();TargetClass.Nested nested = new TargetClass.Nested();
if (count > 0) {int result = nested.getValue().increment().get();} else {int result = 0;}
try {if (target.value() < 0) {throw new IllegalArgumentException((String) target.getGenericData());}} catch (Exception e) {// Handle exception}
return new ArrayList<>();}}
/**
Target record with static nested component*/public record TargetClass(int value) {static class Nested {private int num = 3;
Nested increment() {num++;return this;}
int get() {return num;}}
protected <T> T getGenericData() {return (T) super.toString();}}