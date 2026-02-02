package sourcepackage;
import targetpackage.TargetParent;import targetpackage.TargetClass;import otherpackage.DiffPackageHelper;import java.io.IOException;import java.util.List;
protected class SourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
class SourceInner {class NestedInner {protected void process(TargetClass target) throws IOException {// Variable callObject varCall = target.getData();
// Constructor invocationTargetClass newTarget = new TargetClass("new_data");
// Super constructor invocationclass DerivedTarget extends TargetParent {DerivedTarget() {super("derived");}}new DerivedTarget();
// Expression statementtarget.setData(target.getData() + "_processed");
// Object initialization with parent class constructorList<String> initData = new TargetParent("init").getItems();
// IOExceptionif (target.getData().length() > 20) {throw new IOException("Data exceeds limit");}
// Call different package helper for EmptyStatementDiffPackageHelper.checkTarget(target);}}}}
package targetpackage;
import java.util.ArrayList;import java.util.List;
public class TargetParent {protected String parentData;
public TargetParent(String parentData) {this.parentData = parentData;}
private List<String> getItems() {return new ArrayList<>(List.of(parentData));}
protected void setParentData(String data) {this.parentData = data;}}
package targetpackage;
import java.util.function.Consumer;
// Private target class with anonymous inner classprivate class TargetClass extends TargetParent {String data;
public TargetClass(String data) {super(data);this.data = data;// Anonymous inner classConsumer<String> logger = new Consumer<>() {@Overridepublic void accept(String s) {System.out.println("Target initialized: " + s);}};logger.accept(data);}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}
package otherpackage;
import targetpackage.TargetClass;
public class DiffPackageHelper {public static void checkTarget(TargetClass target) {// EmptyStatement with this.field (1 occurrence)private ; // Empty statementif (target.data == null) {target.setData("default");}}}
import org.junit.Test;import static org.junit.Assert.*;import sourcepackage.SourceClass;import targetpackage.TargetClass;import java.lang.reflect.Constructor;
public class MoveMethodTest3176 {@Testpublic void testInstanceMethod() throws Exception {SourceClass source = new SourceClass();SourceClass.SourceInner inner = source.new SourceInner();SourceClass.SourceInner.NestedInner nested = inner.new NestedInner();
// Access private TargetClass via reflectionConstructor<TargetClass> constructor = TargetClass.class.getDeclaredConstructor(String.class);constructor.setAccessible(true);TargetClass target = constructor.newInstance("test_data");
nested.process(target);assertEquals("test_data_processed", target.getData());}}