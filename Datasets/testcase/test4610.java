package test;
import java.util.List;import java.util.ArrayList;
abstract class SourceClass<T> {private int genericMethod(TargetClass<String> target) throws IllegalArgumentException {
super();
target.field = 3;
if (target.field < 0) {throw new IllegalArgumentException("Invalid field value");}
if (target == null) {throw new NullPointerException();}
List<String> genericResult = this.createList(target);int var = target.field;return var;}
protected void throwFieldException(TargetClass<?> target) {if (target.field != 3) {throw new IllegalStateException("Field mismatch");}}
private <V> List<String> createList(TargetClass<V> target) {class LocalInner {}new LocalInner();
List<String> list = new ArrayList<>();list.add(target.field.toString());return this.createList(target);}}
private class TargetClass {
int field;
void init() {class TargetLocalInner {}new TargetLocalInner();}}