package sourcepackage;
import targetpackage.TargetClass;import java.util.List;
public sealed abstract class SourceClass permits ConcreteSource {static class SourceStaticNested {}
public void process(TargetClass.TargetInner inner) {// Local inner classclass InnerProcessor {void validate(TargetClass.TargetInner targetInner) {if (targetInner == null) {throw new NullPointerException("Inner cannot be null");}}}new InnerProcessor().validate(inner);
// Variable callObject varCall = inner.getName();
// Assert statementassert inner.getValues() != null : "Values list must not be null";
// Constructor invocationTargetClass target = new TargetClass("source_context");TargetClass.TargetInner newInner = target.new TargetInner("new_inner");
// Expression with inner class overloading methodsList<String> basicList = TargetClass.TargetInner.getDefaults();List<String> customList = TargetClass.TargetInner.getDefaults(3);
System.out.println("Basic defaults: " + basicList.size());System.out.println("Custom defaults: " + customList.size());}}
class ConcreteSource extends SourceClass {}
package targetpackage;
import java.util.ArrayList;import java.util.List;
public interface DataHolder {List<String> getValues();}
public class TargetClass implements DataHolder {private String context;
public TargetClass(String context) {this.context = context;}
public class TargetInner {private String name;
public TargetInner(String name) {this.name = name;}
public String getName() {return name;}
@Overridepublic List<String> getValues() {return new ArrayList<>(List.of(name, context));}
// Overloading methodspublic static List<String> getDefaults() {return new ArrayList<>(List.of("default1", "default2"));}
public static List<String> getDefaults(int count) {List<String> defaults = new ArrayList<>();for (int i = 0; i < count; i++) {defaults.add("default" + (i + 1));}return defaults;}}
@Overridepublic List<String> getValues() {return new ArrayList<>(List.of(context));}}
import org.junit.Test;import static org.junit.Assert.*;import sourcepackage.ConcreteSource;import targetpackage.TargetClass;
public class MoveMethodTest3173 {@Testpublic void testInstanceMethod() {SourceClass source = new ConcreteSource();TargetClass target = new TargetClass("test_context");TargetClass.TargetInner inner = target.new TargetInner("test_inner");
source.process(inner);assertNotNull(inner.getValues());assertEquals(2, inner.getValues().size());}}