package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Transactional {}
public sealed class Source implements Runnable permits SubSource {class SourceInner {String data;}
@Transactionalprotected Target process(Target target, int limit) {SourceInner inner = new SourceInner();inner.data = target.inner.getValue();
Target newTarget = new Target();int count = 0;
do {try {newTarget.inner.setValue(inner.data + count);count++;} catch (IllegalArgumentException e) {break;}} while (count < limit);
class LocalInner {void update(Target t) {t.inner.setValue("local");}}new LocalInner().update(newTarget);
return newTarget;}
@Overridepublic void run() {}}
final class SubSource extends Source {}
public class Target {TargetInner inner = new TargetInner();
class TargetInner {private String value;
String getValue() {return value;}
void setValue(String val) {if (val == null) {throw new IllegalArgumentException();}this.value = val;}}}