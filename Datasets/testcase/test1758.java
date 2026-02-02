package test;
import java.lang.annotation.*;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface Processable {}
strictfp enum SourceEnum {INSTANCE;
class SourceInner {String data;}
@Processablepublic <T> Object process(TargetEnum target, List<T> items) {SourceInner inner = new SourceInner();inner.data = target.inner.field1;Object result = null;int count = 0;
while (count < target.inner.field2) {result = target.inner.field3;count++;}
for (T item : items) {target.inner.process(item);}
; // Empty statement
class LocalHandler {void update(TargetEnum t) {t.inner.field1 = "updated";}}new LocalHandler().update(target);
return result;}}
private enum TargetEnum {VALUE;
InnerClass inner = new InnerClass();
class InnerClass {String field1;int field2;Object field3;
<T> void process(T item) {field3 = item;}}}