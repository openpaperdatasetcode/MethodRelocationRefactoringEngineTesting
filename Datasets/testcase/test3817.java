package samepkg;
import java.util.ArrayList;import java.util.List;
public sealed class SourceClass permits SubSourceClass {static class StaticNested1 {}static class StaticNested2 {}
public List<String> varargsMethod(TargetClass... targets) {synchronized (this) {TargetClass varCall = targets[0];int fieldVal = varCall.staticNested.field;
SubSourceClass sub = new SubSourceClass();try {int result = sub.genericMethod(2, 2);} catch (Exception e) {}}
return new ArrayList<>();}
{List<String> list = OthersClass.instanceMethod(new TargetClass());}}
final class SubSourceClass extends SourceClass {private <T extends Number> int genericMethod(T a, T b) {return a.intValue() + b.intValue();}}
class OthersClass {default List<String> instanceMethod(TargetClass target) {return target.staticNested.getList();}}
public class TargetClass {static class TargetStaticNested {int field;
List<String> getList() {return new ArrayList<>();}}
TargetStaticNested staticNested = new TargetStaticNested();}