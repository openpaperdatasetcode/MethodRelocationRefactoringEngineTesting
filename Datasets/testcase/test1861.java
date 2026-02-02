// Source package: com.sourcepackage com.source;
import com.target.TargetRecord;import java.util.function.Consumer;
protected record SourceRecord(String data) {protected String outerProtected = "protected_data";
// Static nested classpublic static class SourceStaticNested {static int staticField = 5;}
// Member inner class with abstract methodpublic class SourceInner {public abstract void abstractMethod(TargetRecord target);}
// Anonymous inner class implementing abstract methodpublic SourceInner createInner() {return new SourceInner() {@Overridepublic void abstractMethod(TargetRecord target) {// Synchronized statementsynchronized (this) {// Variable callString value = target.content();System.out.println("Value: " + value);}
// Private SynchronizedStatement with ClassName.field (same package)class PrivateSyncProcessor {private void process() {synchronized (SourceStaticNested.class) {SourceStaticNested.staticField += target.content().length();}}}new PrivateSyncProcessor().process();
// Super keywordsif (this instanceof SourceInner) {System.out.println("Super class: " + super.getClass().getSimpleName());}
// Access outer protectedtarget.setTempData(outerProtected);
// Overload existsConsumer<String> consumer = this::handle;consumer.accept(target.content());handle(target.content(), "_suffix");}
// Overloaded methodsprivate void handle(String str) {System.out.println("Handled: " + str);}
private void handle(String str, String suffix) {System.out.println("Handled: " + str + suffix);}};}}
// Target package: com.targetpackage com.target;
public abstract record TargetRecord(String content) {public String tempData;
public TargetRecord {// Local inner classclass ContentValidator {boolean isValid(String str) {return str != null && !str.isEmpty();}}new ContentValidator().isValid(content);}
public void setTempData(String data) {this.tempData = data;}}