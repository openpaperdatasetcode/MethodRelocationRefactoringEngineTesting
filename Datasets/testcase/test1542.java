package test;
import java.util.ArrayList;import java.util.List;import java.util.Collection;
public enum SourceEnum implements Collection<String> {INSTANCE;
protected int outerProtected;private static String staticField = "static";
public static class NestedStatic {int value;}
private List<String> process(TargetEnum<String> targetParam) {this(5);outerProtected++;
public class LocalType {int getValue() {return targetParam.nested.value + superOuter.field;}}LocalType local = new LocalType();
Runnable anon = new Runnable() {@Overridepublic void run() {outerProtected += 2;}};anon.run();
List<String> result = new ArrayList<>();result.add(staticField);result.add(targetParam.nestedRec.data);result.add(String.valueOf(local.getValue()));
return result;}
private SourceEnum(int i) {outerProtected = i;}
@Overridepublic int size() { return 0; }
@Overridepublic boolean isEmpty() { return false; }
@Overridepublic boolean contains(Object o) { return false; }
@Overridepublic java.util.Iterator<String> iterator() { return null; }
@Overridepublic Object[] toArray() { return new Object[0]; }
@Overridepublic <T> T[] toArray(T[] a) { return null; }
@Overridepublic boolean add(String e) { return false; }
@Overridepublic boolean remove(Object o) { return false; }
@Overridepublic boolean containsAll(Collection<?> c) { return false; }
@Overridepublic boolean addAll(Collection<? extends String> c) { return false; }
@Overridepublic boolean removeAll(Collection<?> c) { return false; }
@Overridepublic boolean retainAll(Collection<?> c) { return false; }
@Overridepublic void clear() {}}
class SuperOuter {int field = 2;}
private enum TargetEnum<T> {VALUE;
public NestedStatic nested = new NestedStatic();public NestedInnerRec nestedRec = new NestedInnerRec();
public static class NestedStatic {int value = 3;}
public class NestedInnerRec {T data;}}