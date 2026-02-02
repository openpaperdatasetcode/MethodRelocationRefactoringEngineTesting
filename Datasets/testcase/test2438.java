package test.refactoring.movemethod;
import java.sql.SQLException;import java.util.function.Consumer;
interface EnumExtension {}
sealed enum BaseEnum permits SourceEnum {}
public enum TargetEnum {VALUE1(10),VALUE2(20);
public final int field;
TargetEnum(int field) {this.field = field;}
public class TargetInner {private String data;
public TargetInner(String data) {this.data = data;}
public String getData() {return data;}
public void setData(String data) {this.data = data;}}}
non-sealed enum SourceEnum implements BaseEnum, EnumExtension {INSTANCE;
protected int outerProtected = 5;
public class SourceInner {public class NestedInner {public <T> void process() {// Local inner classclass LocalHandler {void handle() {System.out.println("Handling");}}new LocalHandler().handle();
TargetEnum target = TargetEnum.VALUE1;TargetEnum.TargetInner inner = target.new TargetInner("test");
// Variable callObject varCall = inner.getData();
// Access instance fieldint targetField = target.field;
// Access outer protectedint total = outerProtected + targetField;
// LabeledStatement featureprocessBlock: {if (target.field != 3) {break processBlock;}total += target.field;}
// Empty statement;
// Synchronized statementsynchronized (inner) {inner.setData("synchronized");}
// MethodInvocation (2) with protected modifierConsumer<String> consumer = this::protectedMethod1;consumer.accept(inner.getData());protectedMethod2(inner);
// SQLException handlingtry {if (inner.getData().isEmpty()) {throw new SQLException("Data is empty");}} catch (SQLException e) {// No new exception thrown}
// Override violationclass BadRunnable implements Runnable {public void run(int param) {} // Invalid override}}
protected void protectedMethod1(String s) {System.out.println("Method1: " + s);}
protected void protectedMethod2(TargetEnum.TargetInner inner) {System.out.println("Method2: " + inner.getData());}}}}
import org.junit.Test;
public class MoveMethodTest3123 {@Testpublic void testGenericMethod() {SourceEnum source = SourceEnum.INSTANCE;SourceEnum.SourceInner inner = source.new SourceInner();SourceEnum.SourceInner.NestedInner nested = inner.new NestedInner();nested.process();}}