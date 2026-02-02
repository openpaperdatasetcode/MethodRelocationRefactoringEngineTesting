package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Method;
@Retention(RetentionPolicy.RUNTIME)@interface Processable {}
record SourceRecord(int id, String name) {{new Runnable() {@Overridepublic void run() {new InnerProcessor().process(new TargetRecord(0, "init"));}}.run();
new Thread() {@Overridepublic void run() {new InnerProcessor().process(new TargetRecord(1, "thread"));}}.start();}
public class InnerProcessor implements Processor {@Override@Processablepublic void process(TargetRecord target) {transient class TargetHandler {private void handle(TargetRecord t) {System.out.println(t.value() + ": " + t.id());}}
try {synchronized (this) {TargetHandler handler = new TargetHandler();handler.handle(target);
TargetRecord.NestedUpdater updater = new TargetRecord.NestedUpdater();TargetRecord updated = updater.update(target, 3);handler.handle(updated);}
Method method = InnerProcessor.class.getMethod("process", TargetRecord.class);method.invoke(this, target);} catch (Exception e) {e.printStackTrace();}}}}
protected record TargetRecord(int id, String value) {static class NestedUpdater {public TargetRecord update(TargetRecord record, int increment) {return new TargetRecord(record.id() + increment, record.value());}}}
interface Processor {void process(TargetRecord target);}