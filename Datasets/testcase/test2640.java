package test.same;
import java.io.IOException;import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
public enum SourceEnum {FIRST, SECOND;
class FirstInner {}class SecondInner {}
@FunctionalInterfaceprivate interface Processable {int process(TargetEnum target) throws IOException;}
private abstract int abstractMethod(TargetEnum target) throws IOException;
@Overridepublic String toString() {return name();}
public static List<String> staticMethod(TargetEnum target) {return new ArrayList<>(List.of(target.name()));}
static {Processable processor = SourceEnum::staticMethod;}
{TargetEnum local = TargetEnum.VALUE1;Object var = local.field;
switch (local) {case VALUE1:var = 1;break;case VALUE2:var = 2;break;}
try {Supplier<List<String>> supplier = TargetEnum::getValueList;List<String> list = supplier.get();} catch (Exception e) {throw new RuntimeException(e);}}}
protected enum TargetEnum {VALUE1, VALUE2;
Object field;
void operation() {class LocalInner {List<String> getList() {return List.of(VALUE1.name(), VALUE2.name());}}Object var = new LocalInner().getList();}
static List<String> getValueList() {return new ArrayList<>();}
void overloadMethod() {}void overloadMethod(int param) {}}
