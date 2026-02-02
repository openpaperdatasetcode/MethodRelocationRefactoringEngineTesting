import java.util.List;
interface Processable<T> {TargetClass<T> process(TargetClass<T> param);}
class SourceClass implements Processable<String> {class MemberInner {@Overridepublic TargetClass<String> process(TargetClass<String> param) {param.getNested1().setName("inner");param.getNested2().setId(100);param.getNested3().setActive(true);
TargetClass.StaticNested1 nested1 = param.new StaticNested1();nested1.init();
String result = (param.getNested1().getName() != null) ?this.getParentName(param) : this.getDefaultName();
param.getNested1().setName(result);return param;}
private void getParentName(TargetClass<String> target) {target.getNested1().setName(target.getData());}
private void getDefaultName() {// Default implementation}
private void setParentId(TargetClass<String> target) {target.getNested2().setId(200);}}
static class StaticNested {void helper(TargetClass<String> target) {new MemberInner().process(target);}}}
public class TargetClass<T> {private T data;
public TargetClass(T data) {super();this.data = data;}
public static class StaticNested1 {private String name;public void setName(String name) { this.name = name; }public String getName() { return name; }public void init() {}}
public static class StaticNested2 {private int id;public void setId(int id) { this.id = id; }public int getId() { return id; }}
public static class StaticNested3 {private boolean active;public void setActive(boolean active) { this.active = active; }public boolean isActive() { return active; }}
public StaticNested1 getNested1() { return new StaticNested1(); }public StaticNested2 getNested2() { return new StaticNested2(); }public StaticNested3 getNested3() { return new StaticNested3(); }public T getData() { return data; }}