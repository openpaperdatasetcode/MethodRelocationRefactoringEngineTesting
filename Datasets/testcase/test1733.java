package test;
import java.util.ArrayList;import java.util.List;import java.util.Collection;
public enum SourceEnum extends BaseEnum {VALUE1, VALUE2;
static class StaticNested {int id;}
class InnerSource {List<String> process(TargetEnum target) {List<String> result = new ArrayList<>();int count = target.count;result.add(String.valueOf(count));
private Object lock = new Object();synchronized (lock) {target.name = "processed";result.add(target.name);}
return result;}
List<String> process(Integer num) {return List.of(num.toString());}}
public class Caller {@Overridepublic String toString() {InnerSource inner = new InnerSource();Collection<?> coll = new ArrayList<>();coll.forEach(item -> inner.process(TargetEnum.DEFAULT));return inner.process(10).get(0);}}}
enum TargetEnum {DEFAULT, CUSTOM;
int count;String name;
void someMethod() {class LocalInner {String getInfo() {return name;}}}}
abstract class BaseEnum {}